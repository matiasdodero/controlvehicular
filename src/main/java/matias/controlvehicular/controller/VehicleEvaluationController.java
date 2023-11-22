package matias.controlvehicular.controller;

import matias.controlvehicular.dto.VehicleEvaluationDTO;
import matias.controlvehicular.model.VehicleEvaluation;
import matias.controlvehicular.service.VehicleEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//request /vehicles
//post /evaluate

@RestController
@RequestMapping("/vehicles")
public class VehicleEvaluationController {

    private final VehicleEvaluationService vehicleEvaluationService;

    @Autowired
    public VehicleEvaluationController(VehicleEvaluationService vehicleEvaluationService) {
        this.vehicleEvaluationService = vehicleEvaluationService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<VehicleEvaluation> createVehicleEvaluation(@RequestBody VehicleEvaluation vehicleEvaluation) {
        VehicleEvaluation newEvaluation = vehicleEvaluationService.createVehicleEvaluation(vehicleEvaluation);
        return new ResponseEntity<>(newEvaluation, HttpStatus.CREATED);
    }



}