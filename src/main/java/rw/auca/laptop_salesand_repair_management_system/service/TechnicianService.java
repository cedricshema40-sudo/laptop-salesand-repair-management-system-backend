package rw.auca.laptop_salesand_repair_management_system.service;

import java.util.List;

import java.util.UUID;
import org.springframework.stereotype.Service;

import
 rw.auca.laptop_salesand_repair_management_system.Domain.Technician;
 import rw.auca.laptop_salesand_repair_management_system.repository.TechnicianRepository;
import 
rw.auca.laptop_salesand_repair_management_system.repository.RepairSkillRepository;
import 
rw.auca.laptop_salesand_repair_management_system.Domain.RepairSkill;
import 
rw.auca.laptop_salesand_repair_management_system.Domain.Workbench;
import 
rw.auca.laptop_salesand_repair_management_system.repository.WorkbenchRepository;

@Service
public class TechnicianService {


      private final TechnicianRepository technicianRepository;
      
      private final RepairSkillRepository repairSkillRepository;

      private final WorkbenchRepository workbenchRepository;


      public TechnicianService(
         TechnicianRepository technicianRepository,
         RepairSkillRepository repairSkillRepository,
         WorkbenchRepository workbenchRepository) {

            this.technicianRepository = technicianRepository;
            this.repairSkillRepository = repairSkillRepository;
            this.workbenchRepository = workbenchRepository;
        
      }
     public Technician saveTechnician(Technician technician){
        return technicianRepository.save(technician);
     }
     public List<Technician> getAllTechnicians() {
          return technicianRepository.findAll();
     }
     public Technician getTechnicianById(UUID id) {
        return technicianRepository.findById(id).orElse(null);
     }
     public Technician updateTechnician(UUID id, Technician updatedTechnician) {
        Technician existingTechnician = technicianRepository.findById(id)
            .orElse(null);

            if (existingTechnician == null) {
                return null;
            }
            existingTechnician.setFirstName(updatedTechnician.getFirstName());
            existingTechnician.setLastName(updatedTechnician.getLastName());
            existingTechnician.setPhone(updatedTechnician.getPhone());
            existingTechnician.setWorkbench(updatedTechnician.getWorkbench());
            existingTechnician.setRepairSkills(updatedTechnician.getRepairSkills());

            return technicianRepository.save(existingTechnician);
     }
      public boolean deleteTechnician(UUID id) {
        Technician existingTechnician = technicianRepository.findById(id)
             .orElse(null);

             if(existingTechnician == null) {
                return false;
             }
             technicianRepository.delete(existingTechnician);
              return true;
      }

      public List<Technician> getTechniciansByRepairSkill(String name) {
         return technicianRepository.findByRepairSkillName(name);
      }
   public String addRepairSkillToTechnician(UUID technicianId, UUID repairSkillId) {

      Technician technician =
      technicianRepository.findById(technicianId)
                 .orElse(null);

                 if (technician == null) {
                  return "The technician with that id does not exist";
                 }

         RepairSkill repairSkill = 
         repairSkillRepository.findById(repairSkillId)
                     .orElse(null);

      if (repairSkill == null) {
         return "The repair skill with that id does not exist";
      }
      if (technician.getRepairSkills().contains(repairSkill)) {
         return "The technician already has that repair skill";
      }
      technician.getRepairSkills().add(repairSkill);
      technicianRepository.save(technician);
      return "Repair skill added to the technician";
   }
   public String changeTechnicianWorkbench(UUID technicianId, int benchNumber) {

      Technician technician = 
       technicianRepository.findById(technicianId)
              .orElse(null);

         if (technician == null) {
            return "The technician with that id does not exist";
         }
      Workbench workbench =
       workbenchRepository.findByBenchNumber(benchNumber) 
            .orElse(null);

         if (workbench == null) {
            return "The workbench with that bench number does not exist";
         }
         for (Technician otherTechnician :
            technicianRepository.findAll()) {
               if (!otherTechnician.getId().equals(technicianId)
                    && otherTechnician.getWorkbench() != null
                    && otherTechnician.getWorkbench().getId()
                           .equals(workbench.getId())) {

                              return "That workbench is taken by another technician";
                           }
            }
            technician.setWorkbench(workbench);
            technicianRepository.save(technician);

            return "Workbench changed successfully";
         
   }
}
