package matias.controlvehicular.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VehicleDTO {

    private Long Id;
    private String licensePlate;
    private String brand;
    private String model;
    private int year;


}
