package matias.controlvehicular.service;

import matias.controlvehicular.dto.VehicleCheckDto;
import matias.controlvehicular.model.VehicleEvaluation;
import matias.controlvehicular.repository.VehicleEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleEvaluationService {

    private final VehicleEvaluationRepository vehicleEvaluationRepository;

    @Autowired
    public VehicleEvaluationService(VehicleEvaluationRepository vehicleEvaluationRepository) {
        this.vehicleEvaluationRepository = vehicleEvaluationRepository;
    }

    public VehicleEvaluation createVehicleEvaluation(VehicleEvaluation vehicleEvaluation) {

        return vehicleEvaluationRepository.save(vehicleEvaluation);
    }

    public Optional<VehicleEvaluation> getVehicleEvaluationById(Long id) {
        return vehicleEvaluationRepository.findById(id);
    }

    public List<VehicleEvaluation> getAllVehicleEvaluations() {
        return vehicleEvaluationRepository.findAll();
    }

    public VehicleEvaluation updateVehicleEvaluation(Long id, VehicleEvaluation updatedVehicleEvaluation) {
        return vehicleEvaluationRepository.findById(id)
                .map(vehicleEvaluation -> {
                    vehicleEvaluation.setBrakesScore(updatedVehicleEvaluation.getBrakesScore());
                    vehicleEvaluation.setLightsScore(updatedVehicleEvaluation.getLightsScore());
                    vehicleEvaluation.setOverallScore(updatedVehicleEvaluation.getOverallScore());
                    vehicleEvaluation.setComments(updatedVehicleEvaluation.getComments());
                    vehicleEvaluation.setEvaluationDate(updatedVehicleEvaluation.getEvaluationDate());

                    return vehicleEvaluationRepository.save(vehicleEvaluation);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle Evaluation not found with id " + id));
    }

    public void deleteVehicleEvaluation(Long id) {
        vehicleEvaluationRepository.deleteById(id);
    }


}
