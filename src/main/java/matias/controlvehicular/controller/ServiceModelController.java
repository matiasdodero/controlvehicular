package matias.controlvehicular.controller;

import matias.controlvehicular.dto.ServiceModelDTO;
import matias.controlvehicular.model.ServiceModel;
import matias.controlvehicular.service.ServiceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/serviceModels")
public class ServiceModelController {

    private final ServiceModelService serviceModelService;

    @Autowired
    public ServiceModelController(ServiceModelService serviceModelService) {
        this.serviceModelService = serviceModelService;
    }

    @PostMapping("/")
    public ResponseEntity<ServiceModel> createServiceModel(@RequestBody ServiceModelDTO serviceModelDTO) {
        ServiceModel serviceModel = serviceModelService.createServiceModel(serviceModelDTO);
        return new ResponseEntity<>(serviceModel, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ServiceModelDTO>> getAllServiceModels() {
        List<ServiceModel> serviceModels = serviceModelService.findAllServiceModels();
        List<ServiceModelDTO> serviceModelDTOs = serviceModels.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(serviceModelDTOs, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ServiceModelDTO> getServiceModelById(@PathVariable Long id) {
        return serviceModelService.findServiceModelById(id)
                .map(serviceModel -> ResponseEntity.ok(convertToDto(serviceModel)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceModelDTO> updateServiceModel(@PathVariable Long id, @RequestBody ServiceModelDTO serviceModelDTO) {
        return serviceModelService.updateServiceModel(id, serviceModelDTO)
                .map(updatedServiceModel -> new ResponseEntity<>(convertToDto(updatedServiceModel), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceModel(@PathVariable Long id) {
        boolean wasDeleted = serviceModelService.deleteServiceModel(id);
        if (wasDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    private ServiceModelDTO convertToDto(ServiceModel serviceModel) {

        ServiceModelDTO dto = new ServiceModelDTO();
        dto.setId(serviceModel.getId());
        dto.setName(serviceModel.getName());
        dto.setDescription(serviceModel.getDescription());
        dto.setPrice(serviceModel.getPrice());
        return dto;
    }




}
