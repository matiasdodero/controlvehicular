package matias.controlvehicular.tests;

import matias.controlvehicular.dto.VehicleDTO;
import matias.controlvehicular.model.Vehicle;
import matias.controlvehicular.repository.VehicleRepository;
import matias.controlvehicular.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleService.class)
public class VehicleServiceTests {

    @MockBean
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void testCreateVehicle() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setLicensePlate("ABC123");
        vehicleDTO.setBrand("Honda");
        vehicleDTO.setModel("Civic");
        vehicleDTO.setYear(2020);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setYear(vehicleDTO.getYear());

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        Vehicle created = vehicleService.createVehicle(vehicleDTO);

        assertNotNull(created);
        assertEquals(vehicle.getLicensePlate(), created.getLicensePlate());
    }


}
