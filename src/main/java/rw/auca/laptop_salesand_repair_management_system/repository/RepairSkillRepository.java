package rw.auca.laptop_salesand_repair_management_system.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rw.auca.laptop_salesand_repair_management_system.Domain.RepairSkill;


public interface  RepairSkillRepository
       extends JpaRepository<RepairSkill, UUID> {

           @Query("SELECT rs FROM RepairSkill rs WHERE rs.technicians IS EMPTY")

           List<RepairSkill> findUnusedRepairSkills();
       
       }
