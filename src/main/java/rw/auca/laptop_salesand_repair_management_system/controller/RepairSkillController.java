package rw.auca.laptop_salesand_repair_management_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.laptop_salesand_repair_management_system.Domain.RepairSkill;
import rw.auca.laptop_salesand_repair_management_system.service.RepairSkillService;

@RestController
@RequestMapping("/api/repair-skills")
public class RepairSkillController {

     private final RepairSkillService repairSkillService;
       public RepairSkillController(RepairSkillService repairSkillService) {
        this.repairSkillService = repairSkillService;
       }

    @PostMapping("/save")
    public ResponseEntity<?> saveRepairSkill(
        @RequestBody RepairSkill repairSkill) {

      RepairSkill savedSkill =
                 repairSkillService.saveRepairSkill(repairSkill);

                 return ResponseEntity.status(HttpStatus.CREATED)
                              .body(savedSkill);

        }

@GetMapping("/all")
public ResponseEntity<List<RepairSkill>> getAllRepairSkills() {
    return ResponseEntity.ok(
        repairSkillService.getAllRepairSkills());
}
@GetMapping("/{id}")
public ResponseEntity<?> getRepairSkillsById(
     @PathVariable UUID id) {
    RepairSkill repairSkill=
           repairSkillService.getRepairSkillById(id);

           if(repairSkill == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                  .body("The repair skill with that id does not exist");

           }
           return ResponseEntity.ok(repairSkill);
     }
     @PutMapping("/update/{id}")
      public ResponseEntity<?> updateRepairSKill(
         @PathVariable UUID id,
         @RequestBody RepairSkill updatedSkill) {
            RepairSkill repairSkill =
                    repairSkillService.updateRepairSkill(id, updatedSkill);

                    if (repairSkill == null) {
                          return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                       .body("The repair skill with that id does not exist");
                    }
                return ResponseEntity.ok(repairSkill);

         }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRepairSkill(
          @PathVariable UUID id) {

            boolean deleted =  repairSkillService.deleteRepairSkill(id);

            if(!deleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("The repair skill with that id does not exist") ;
            }
            return ResponseEntity.ok("Repair skill deleted successfully");
     }
   @GetMapping("/unused")
   public ResponseEntity<List<RepairSkill>>
   getUnusedRepairSkills() {
    return ResponseEntity.ok(
        repairSkillService.getUnusedRepairSkills());
    
   }
}
