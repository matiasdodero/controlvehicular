package matias.controlvehicular.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import matias.controlvehicular.dto.ServiceModelDTO;
import matias.controlvehicular.model.ServiceModel;
import matias.controlvehicular.repository.ServiceModelRepository;
import matias.controlvehicular.service.ServiceModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServiceModelServiceTests {

    @Mock
    private ServiceModelRepository serviceModelRepository;

    @InjectMocks
    private ServiceModelService serviceModelService;

    private ServiceModelDTO serviceModelDTO;
    private ServiceModel serviceModel;

    @BeforeEach
    void setUp() {
        // mock servicemodel
        serviceModelDTO = new ServiceModelDTO();
        serviceModelDTO.setName("Cambio de aceite");
        serviceModelDTO.setDescription("Cambio completo de aceite y filtro del motor.");
        serviceModelDTO.setPrice(new BigDecimal("59.99"));

        serviceModel = new ServiceModel();
        serviceModel.setId(1L);
        serviceModel.setName(serviceModelDTO.getName());
        serviceModel.setDescription(serviceModelDTO.getDescription());
        serviceModel.setPrice(serviceModelDTO.getPrice());
    }

    @Test
    void testCreateServiceModel() {
        when(serviceModelRepository.save(any(ServiceModel.class))).thenReturn(serviceModel);
        ServiceModel createdServiceModelDTO = serviceModelService.createServiceModel(serviceModelDTO);

        assertNotNull(createdServiceModelDTO);
        assertEquals(serviceModelDTO.getName(), createdServiceModelDTO.getName());
        assertEquals(serviceModelDTO.getDescription(), createdServiceModelDTO.getDescription());
        assertEquals(0, serviceModelDTO.getPrice().compareTo(createdServiceModelDTO.getPrice()));
    }

    @Test
    void testGetServiceModelById() {
        when(serviceModelRepository.findById(anyLong())).thenReturn(Optional.of(serviceModel));
        ServiceModelDTO foundServiceModelDTO = serviceModelService.getServiceModelById(1L)
                .map(serviceModelService::convertToDTO)
                .orElse(null);

        assertNotNull(foundServiceModelDTO);
        assertEquals(serviceModel.getId(), foundServiceModelDTO.getId());
    }


}
