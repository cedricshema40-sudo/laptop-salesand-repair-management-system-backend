package rw.auca.laptop_salesand_repair_management_system.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import
rw.auca.laptop_salesand_repair_management_system.Domain.Technician;

public interface  TechnicianRepository
      extends JpaRepository<Technician, UUID>{

             @Query("SELECT DISTINCT t  FROM Technician t " +
                  "JOIN t.repairSkills rs " +
                  "WHERE LOWER(rs.name) LIKE LOWER(CONCAT('%', :name,'%'))")
             List<Technician> findByRepairSkillName(@Param("name") String name);

      }
