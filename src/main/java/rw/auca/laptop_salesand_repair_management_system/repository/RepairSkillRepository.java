package rw.auca.laptop_salesand_repair_management_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.laptop_salesand_repair_management_system.Domain.RepairSkill;


public interface  RepairSkillRepository
       extends JpaRepository<RepairSkill, UUID> {

       }
