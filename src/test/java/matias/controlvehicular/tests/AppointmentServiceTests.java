package matias.controlvehicular.tests;

import matias.controlvehicular.dto.AppointmentDTO;
import matias.controlvehicular.model.Appointment;
import matias.controlvehicular.model.Customer;
import matias.controlvehicular.model.ServiceModel;
import matias.controlvehicular.model.Vehicle;
import matias.controlvehicular.repository.AppointmentRepository;
import matias.controlvehicular.repository.CustomerRepository;
import matias.controlvehicular.repository.ServiceModelRepository;
import matias.controlvehicular.repository.VehicleRepository;
import matias.controlvehicular.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class AppointmentServiceTests {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ServiceModelRepository serviceModelRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    private Customer customer;
    private Vehicle vehicle;
    private ServiceModel serviceModel;
    private Appointment appointment;

    @BeforeEach
    void setUp() {

        customer = new Customer();
        customer.setId(1L);

        vehicle = new Vehicle();
        vehicle.setId(1L);

        serviceModel = new ServiceModel();
        serviceModel.setId(1L);

        // seteo appointment
        appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setVehicle(vehicle);
        appointment.setServiceModel(serviceModel);
        appointment.setAppointmentDate(LocalDateTime.of(2023, 1, 1, 10, 0));

        // Mock comportamiento repositorios
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(customer));
        when(vehicleRepository.findById(any(Long.class))).thenReturn(Optional.of(vehicle));
        when(serviceModelRepository.findById(any(Long.class))).thenReturn(Optional.of(serviceModel));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);
    }

    @Test
    void testCreateAppointment() {
        // creo appointment dto
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setCustomerId(1L);
        appointmentDTO.setVehicleId(1L);
        appointmentDTO.setServiceModelId(1L);
        appointmentDTO.setAppointmentDate(LocalDateTime.of(2023, 1, 1, 10, 0));

        // llamo al metodo a testear
        AppointmentDTO createdAppointmentDTO = appointmentService.createAppointment(appointmentDTO);


        assertNotNull(createdAppointmentDTO);
        assertEquals(appointmentDTO.getCustomerId(), createdAppointmentDTO.getCustomerId());
        assertEquals(appointmentDTO.getVehicleId(), createdAppointmentDTO.getVehicleId());
        assertEquals(appointmentDTO.getServiceModelId(), createdAppointmentDTO.getServiceModelId());
        assertEquals(appointmentDTO.getAppointmentDate(), createdAppointmentDTO.getAppointmentDate());
    }
}

