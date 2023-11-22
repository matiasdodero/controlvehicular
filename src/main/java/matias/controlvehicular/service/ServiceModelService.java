package matias.controlvehicular.service;

import matias.controlvehicular.dto.ServiceModelDTO;
import matias.controlvehicular.model.ServiceModel;
import matias.controlvehicular.repository.ServiceModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceModelService {

    private final ServiceModelRepository serviceModelRepository;

    @Autowired
    public ServiceModelService(ServiceModelRepository serviceModelRepository) {
        this.serviceModelRepository = serviceModelRepository;
    }

    public ServiceModel createServiceModel(ServiceModelDTO serviceModelDTO) {
        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setName(serviceModelDTO.getName());
        serviceModel.setDescription(serviceModelDTO.getDescription());
        serviceModel.setPrice(serviceModelDTO.getPrice());
        return serviceModelRepository.save(serviceModel);
    }

    public Optional<ServiceModel> updateServiceModel(Long id, ServiceModelDTO serviceModelDTO) {
        return serviceModelRepository.findById(id)
                .map(serviceModel -> {
                    serviceModel.setName(serviceModelDTO.getName());
                    serviceModel.setDescription(serviceModelDTO.getDescription());
                    serviceModel.setPrice(serviceModelDTO.getPrice());
                    return serviceModelRepository.save(serviceModel);
                });
    }

    public boolean deleteServiceModel(Long id) {
        if (serviceModelRepository.existsById(id)) {
            serviceModelRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public List<ServiceModel> findAllServiceModels() {
        return serviceModelRepository.findAll();
    }

    public Optional<ServiceModel> findServiceModelById(Long id) {
        return serviceModelRepository.findById(id);
    }

    public Optional<ServiceModel> getServiceModelById(Long id) {
        return serviceModelRepository.findById(id);
    }

    public ServiceModelDTO convertToDTO(ServiceModel serviceModel) {
        ServiceModelDTO dto = new ServiceModelDTO();
        dto.setId(serviceModel.getId());
        dto.setName(serviceModel.getName());
        dto.setDescription(serviceModel.getDescription());
        dto.setPrice(serviceModel.getPrice());
        return dto;
    }

    private ServiceModel convertToEntity(ServiceModelDTO serviceModelDTO) {
        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setId(serviceModelDTO.getId()); // Solo para actualización, no para creación
        serviceModel.setName(serviceModelDTO.getName());
        serviceModel.setDescription(serviceModelDTO.getDescription());
        serviceModel.setPrice(serviceModelDTO.getPrice());
        return serviceModel;
    }

}
