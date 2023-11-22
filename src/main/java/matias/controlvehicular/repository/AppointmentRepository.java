package matias.controlvehicular.repository;

import matias.controlvehicular.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
    List<Appointment> findByAppointmentDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    Optional<Appointment> findByAppointmentDate(LocalDateTime dateTime);


}


