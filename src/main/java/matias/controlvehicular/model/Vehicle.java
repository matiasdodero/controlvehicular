package matias.controlvehicular.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // La matrícula sigue siendo única
    private String licensePlate;

    private String brand;

    private String model;

    private int year;



}

