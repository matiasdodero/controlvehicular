package matias.controlvehicular.service;

import matias.controlvehicular.Exception.EntityNotFoundException;
import matias.controlvehicular.dto.AppointmentDTO;
import matias.controlvehicular.model.Appointment;
import matias.controlvehicular.model.Customer;
import matias.controlvehicular.model.ServiceModel;
import matias.controlvehicular.model.Vehicle;
import matias.controlvehicular.repository.AppointmentRepository;
import matias.controlvehicular.repository.CustomerRepository;
import matias.controlvehicular.repository.ServiceModelRepository;
import matias.controlvehicular.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final ServiceModelRepository serviceModelRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              CustomerRepository customerRepository,
                              VehicleRepository vehicleRepository,
                              ServiceModelRepository serviceModelRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.serviceModelRepository = serviceModelRepository;
    }

    @Transactional
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = convertToEntity(appointmentDTO);
        appointment = appointmentRepository.save(appointment);
        return convertToDTO(appointment);
    }

    @Transactional(readOnly = true)
    public Optional<AppointmentDTO> getAppointmentById(Long id) {
        return appointmentRepository.findById(id).map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public List<AppointmentDTO> findAllAppointments() {
        return appointmentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public Optional<AppointmentDTO> updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    updateEntity(appointment, appointmentDTO);
                    appointment = appointmentRepository.save(appointment);
                    return convertToDTO(appointment);
                });
    }

    @Transactional
    public boolean deleteAppointment(Long id) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointmentRepository.delete(appointment);
                    return true;
                }).orElse(false);
    }

    @Transactional(readOnly = true)
    public List<LocalDateTime> getAvailableAppointmentTimes(LocalDate date) {
        LocalTime openingTime = LocalTime.of(9, 0); // Horario de apertura, 9:00 AM
        LocalTime closingTime = LocalTime.of(18, 0); // Horario de cierre, 6:00 PM
        int appointmentDurationInMinutes = 15; // Duración de cada cita, en minutos

        List<LocalDateTime> allPossibleTimes = new ArrayList<>();
        LocalTime currentTime = openingTime;

        // genero todos los posibles horarios de citas para el día
        while (currentTime.isBefore(closingTime)) {
            allPossibleTimes.add(LocalDateTime.of(date, currentTime));
            currentTime = currentTime.plusMinutes(appointmentDurationInMinutes);
        }

        // traigo citas existentes para esa fecha
        List<Appointment> existingAppointments = appointmentRepository.findByAppointmentDateBetween(
                LocalDateTime.of(date, openingTime),
                LocalDateTime.of(date, closingTime)
        );

        // filtro los horarios que ya están ocupados
        List<LocalDateTime> occupiedTimes = existingAppointments.stream()
                .map(Appointment::getAppointmentDate)
                .collect(Collectors.toList());

        // devuelvo solo los horarios disponibles
        return allPossibleTimes.stream()
                .filter(time -> !occupiedTimes.contains(time))
                .collect(Collectors.toList());
    }

    private Appointment convertToEntity(AppointmentDTO appointmentDTO) {
        Customer customer = customerRepository.findById(appointmentDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        Vehicle vehicle = vehicleRepository.findById(appointmentDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        ServiceModel serviceModel = serviceModelRepository.findById(appointmentDTO.getServiceModelId())
                .orElseThrow(() -> new EntityNotFoundException("ServiceModel not found"));

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setVehicle(vehicle);
        appointment.setServiceModel(serviceModel);
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        return appointment;
    }

    private AppointmentDTO convertToDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setCustomerId(appointment.getCustomer().getId());
        dto.setVehicleId(appointment.getVehicle().getId());
        dto.setServiceModelId(appointment.getServiceModel().getId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        return dto;
    }

    private void updateEntity(Appointment appointment, AppointmentDTO appointmentDTO) {
        if (appointmentDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(appointmentDTO.getCustomerId())
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
            appointment.setCustomer(customer);
        }
        if (appointmentDTO.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepository.findById(appointmentDTO.getVehicleId())
                    .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
            appointment.setVehicle(vehicle);
        }
        if (appointmentDTO.getServiceModelId() != null) {
            ServiceModel serviceModel = serviceModelRepository.findById(appointmentDTO.getServiceModelId())
                    .orElseThrow(() -> new EntityNotFoundException("ServiceModel not found"));
            appointment.setServiceModel(serviceModel);
        }
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
    }
}
