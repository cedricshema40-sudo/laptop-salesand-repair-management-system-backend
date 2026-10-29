package rw.auca.laptop_salesand_repair_management_system.repository;

import java.util.List;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import
rw.auca.laptop_salesand_repair_management_system.Domain.Workbench;

public interface  WorkbenchRepository
      extends JpaRepository<Workbench, UUID> {
       
      @Query("SELECT  w  FROM Workbench w " +
            "WHERE NOT EXISTS (" +
            "SELECT t FROM Technician t " +
            "WHERE t.workbench = w) " +
            "ORDER BY w.benchNumber ASC")

            List<Workbench> findFreeWorkbenches();
         Optional<Workbench> findByBenchNumber (int benchNumber);

      }
