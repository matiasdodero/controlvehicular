package matias.controlvehicular.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import matias.controlvehicular.dto.CustomerDTO;
import matias.controlvehicular.model.Customer;
import matias.controlvehicular.repository.CustomerRepository;
import matias.controlvehicular.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private CustomerDTO customerDTO;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Ana");
        customerDTO.setLastName("Ramirez");
        customerDTO.setEmail("ana.ramirez@example.com");
        customerDTO.setPhoneNumber("555-6789");

        customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer createdCustomer = customerService.createCustomer(customerDTO);

        assertNotNull(createdCustomer);
        assertEquals(customer.getFirstName(), createdCustomer.getFirstName());
        assertEquals(customer.getLastName(), createdCustomer.getLastName());
        assertEquals(customer.getEmail(), createdCustomer.getEmail());
        assertEquals(customer.getPhoneNumber(), createdCustomer.getPhoneNumber());
    }

    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        Optional<Customer> foundCustomer = customerService.getCustomerById(1L);

        assertTrue(foundCustomer.isPresent());
        assertEquals(customer.getId(), foundCustomer.get().getId());
    }

    @Test
    void testUpdateCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO updateDTO = new CustomerDTO();
        updateDTO.setFirstName("Ana Maria");
        updateDTO.setLastName("Ramirez");
        updateDTO.setEmail("ana.ramirez@example.com");
        updateDTO.setPhoneNumber("555-6789");

        CustomerDTO updatedCustomer = customerService.updateCustomer(1L, updateDTO);

        assertNotNull(updatedCustomer);
        assertEquals(updateDTO.getFirstName(), updatedCustomer.getFirstName());
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> customerService.deleteCustomer(1L));
    }
}

