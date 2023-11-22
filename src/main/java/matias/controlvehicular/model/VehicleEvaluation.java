package matias.controlvehicular.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
@Entity
@Table(name = "vehicle_evaluation")
public class VehicleEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId;
    private int lightsScore;
    private int brakesScore;
    private int overallScore;
    private LocalDate evaluationDate;
    private String comments;


}

