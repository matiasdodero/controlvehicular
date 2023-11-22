package matias.controlvehicular.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class VehicleEvaluationDTO {
    private Long vehicleId;
    private Integer brakesScore;
    private Integer lightsScore;
    private String comments;
    private LocalDate evaluationDate;
    private Integer overallScore;

}
