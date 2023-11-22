package matias.controlvehicular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import matias.controlvehicular.model.VehicleEvaluation;

@Repository
public interface VehicleEvaluationRepository extends JpaRepository<VehicleEvaluation, Long> {

}
