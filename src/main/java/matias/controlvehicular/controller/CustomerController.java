package matias.controlvehicular.controller;

import matias.controlvehicular.dto.CustomerDTO;
import matias.controlvehicular.model.Customer;
import matias.controlvehicular.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.findCustomerById(id)
                .map(customerFromDb -> {

                    customerFromDb.setFirstName(customerDTO.getFirstName());
                    customerFromDb.setLastName(customerDTO.getLastName());
                    customerFromDb.setEmail(customerDTO.getEmail());
                    customerFromDb.setPhoneNumber(customerDTO.getPhoneNumber());
                    Customer updatedCustomer = customerService.saveCustomer(customerFromDb);
                    return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return customerService.findCustomerById(id)
                .map(customer -> {
                    customerService.deleteCustomer(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }





}
