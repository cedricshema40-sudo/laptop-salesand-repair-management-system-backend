package rw.auca.laptop_salesand_repair_management_system.service;



import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import rw.auca.laptop_salesand_repair_management_system.Domain.RepairSkill;

import rw.auca.laptop_salesand_repair_management_system.repository.RepairSkillRepository;

@Service
public class RepairSkillService {

    private final RepairSkillRepository repairSkillRepository;

    public RepairSkillService(RepairSkillRepository repairSkillRepository) {
        this.repairSkillRepository = repairSkillRepository;
    }
    public RepairSkill saveRepairSkill(RepairSkill repairSkill) {
        return repairSkillRepository.save(repairSkill);

    }
    public List<RepairSkill> getAllRepairSkills() {
        return repairSkillRepository.findAll();
    }
    public RepairSkill getRepairSkillById(UUID id) {
        return repairSkillRepository.findById(id).orElse(null);
}
  public RepairSkill updateRepairSkill(UUID id, RepairSkill updatedSkill) {
        RepairSkill existingSkill = repairSkillRepository.findById(id)

         .orElse (null);

         if (existingSkill == null) {
               return null;
         }
    existingSkill.setName(updatedSkill.getName());
     return repairSkillRepository.save(existingSkill);
  }
   public boolean deleteRepairSkill(UUID id) {
    RepairSkill existingSkill = repairSkillRepository.findById(id)

          .orElse(null);

          if (existingSkill == null) {
            return false;
          }
        repairSkillRepository.delete(existingSkill);
        return true;
   }

}
