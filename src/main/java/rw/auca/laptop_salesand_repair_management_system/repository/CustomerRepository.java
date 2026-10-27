package rw.auca.laptop_salesand_repair_management_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import
rw.auca.laptop_salesand_repair_management_system.Domain.Customer;
public interface  CustomerRepository
      extends JpaRepository<Customer, UUID> {

         List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(
                               String lastName);

        @Query("SELECT DISTINCT r.customer FROM RepairJob r " +
            "WHERE r.technician.id = :technicianId")

      List<Customer> findCustomersByTechnicianId(
            @Param("technicianId") UUID technicianId);
        
      }
