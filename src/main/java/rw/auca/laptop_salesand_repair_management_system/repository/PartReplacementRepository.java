package rw.auca.laptop_salesand_repair_management_system.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import 
rw.auca.laptop_salesand_repair_management_system.Domain.PartReplacement;

public interface  PartReplacementRepository 
     extends JpaRepository<PartReplacement,UUID> {

boolean existsByRepairJob_Id(UUID repairJobId);
          @Query(" SELECT p FROM PartReplacement p " + 
                  "WHERE p.repairJob.customer.phone = :phone " +
                  "ORDER BY p.replacedDate DESC")
          
     List<PartReplacement> findByCustomerPhone(@Param("phone") String phone);
     }


