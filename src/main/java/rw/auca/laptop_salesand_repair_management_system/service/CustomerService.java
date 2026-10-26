package rw.auca.laptop_salesand_repair_management_system.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.laptop_salesand_repair_management_system.Domain.Customer;
import rw.auca.laptop_salesand_repair_management_system.repository.CustomerRepository;

@Service

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save (customer);
    }
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
    public Customer getCustomerById(UUID  id) {
        return customerRepository.findById(id) .orElse(null);
    }
    public Customer updateCustomer(UUID id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id)
          .orElse(null);

          if (existingCustomer == null) {
            return null;
          }

          existingCustomer.setFirstName(updatedCustomer.getFirstName());
          existingCustomer.setLastName(updatedCustomer.getLastName());
          existingCustomer.setPhone(updatedCustomer.getPhone());
          existingCustomer.setEmail(updatedCustomer.getEmail());

          return customerRepository.save(existingCustomer);
    }
     public boolean deleteCustomer(UUID id) {
        Customer existingCustomer = customerRepository.findById(id)
          .orElse(null);

          if (existingCustomer == null) {
            return false;
          }
          customerRepository.delete(existingCustomer);
            return true;
     }
      public List<Customer> getCustomersByLastName(String lastName) {
        return customerRepository
              .findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
      }
}
