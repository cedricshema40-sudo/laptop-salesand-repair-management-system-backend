package rw.auca.laptop_salesand_repair_management_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import
rw.auca.laptop_salesand_repair_management_system.Domain.Customer;
import rw.auca.laptop_salesand_repair_management_system.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

     private final CustomerService customerService;

     public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
     }
    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
    return ResponseEntity.status(HttpStatus.CREATED)
                  .body("Customer saved successfully");

    }
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

@GetMapping("/by-last-name")
public ResponseEntity<List<Customer>> getCustomersByLastName(
        @RequestParam String lastName) {
            return ResponseEntity.ok(
                customerService.getCustomersByLastName(lastName));

        }
@GetMapping("/{id}")
public ResponseEntity<?> getCustomerById(@PathVariable UUID id) {
     Customer customer = customerService.getCustomerById(id);

     if (customer == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("The customer with that id does not exist");

     }
     return ResponseEntity.ok("customer");
}

@PutMapping("/update/{id}")
public ResponseEntity<?> updateCustomer(
    @PathVariable UUID id,
    @RequestBody Customer updatedCustomer) {
        Customer customer =
                  customerService.updateCustomer(id, updatedCustomer);

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The customer with that id does not exist");
        }
        return ResponseEntity.ok("Customer updated successfully");
    }

@DeleteMapping("/delete/{id}")
public ResponseEntity<?> deleteCustomer(@PathVariable UUID id) {
    boolean deleted = customerService.deleteCustomer(id);

    if(!deleted) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("The customer with that id does not exist");
    }
    return ResponseEntity.ok("Customer deleted successfully");
}
@GetMapping("/of-technician/{technicianId}")
public ResponseEntity<?> getCustomersofTechnician(
      @PathVariable UUID technicianId) {

        if (!customerService.technicianExists(technicianId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                  .body("The technician with that id does not exist");
        }
        return ResponseEntity.ok(
            customerService.getCustomersByTechnicianId(technicianId));
        
      }

}
