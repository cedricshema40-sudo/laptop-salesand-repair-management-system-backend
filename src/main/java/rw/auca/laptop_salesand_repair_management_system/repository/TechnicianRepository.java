package rw.auca.laptop_salesand_repair_management_system.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import
rw.auca.laptop_salesand_repair_management_system.Domain.Technician;

public interface  TechnicianRepository
      extends JpaRepository<Technician, UUID>{

      }
