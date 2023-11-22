package matias.controlvehicular.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
public class AppointmentDTO {
    private Long id;
    private LocalDateTime appointmentDate;
    private Long customerId;
    private Long vehicleId;
    private Long serviceModelId;





    public AppointmentDTO() {}

    public AppointmentDTO(Long id, LocalDateTime appointmentDate, Long customerId, Long vehicleId, Long serviceModelId) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.serviceModelId = serviceModelId;
    }



}


