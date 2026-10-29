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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

import
rw.auca.laptop_salesand_repair_management_system.Domain.Technician;
import rw.auca.laptop_salesand_repair_management_system.service.TechnicianService;

@RestController
@RequestMapping("/api/technicians")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveTechnician(
      @RequestBody Technician technician) {
        technicianService.saveTechnician(technician);

        return ResponseEntity.status(HttpStatus.CREATED)
                     .body("Technician saved successfully");
      }
      @GetMapping("/all")
      public ResponseEntity<List<Technician>> getAllTechnicians(){
        return ResponseEntity.ok(technicianService.getAllTechnicians());
      }

      @GetMapping("/{id}")
       public ResponseEntity<?> getTechnicianById(
            @PathVariable UUID id) {
      Technician technician = technicianService.getTechnicianById(id);

      if (technician == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                 .body("The technican with that id does not exist");
      }
      return ResponseEntity.ok(technician);
            }

@PutMapping("/update/{id}")
public ResponseEntity<?> updateTechnician(
      @PathVariable UUID id,
      @RequestBody Technician updatedTechnician) {

        Technician technician = technicianService.updateTechnician(id, updatedTechnician);

        if(technician == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("The technician with that id does not exist");
        }
        return ResponseEntity.ok("Technician updated successfully");
      }
@DeleteMapping("/delete/{id}")
public ResponseEntity<?> deleteTechnician(
       @PathVariable UUID id) {

        boolean deleted = technicianService.deleteTechnician(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("The technician with that id does not exist");
        }
        return ResponseEntity.ok("Technician deleted successfully");
       }
 @GetMapping("/by-repair-skill")
 public ResponseEntity<List<Technician>>
 getTechniciansByRepairSkill(
       @RequestParam String name) {
        
        return ResponseEntity.ok(
          technicianService.getTechniciansByRepairSkill(name));
        
       }
 
@PutMapping("/{technicianId}/repair-skills/{repairSkillId}")
public ResponseEntity<?> addRepairSkillToTechnician(
  @PathVariable UUID technicianId,
  @PathVariable UUID repairSkillId) {
    String message = technicianService.addRepairSkillToTechnician(
      technicianId, repairSkillId);

      if (message.equals("The technician with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

      } else if (message.equals("The repair skill with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
      } else if (message.equals("The technician already has that repair skill")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);

      } else {
        return ResponseEntity.ok(message);
      }
  }
@PatchMapping("/{technicianId}/workbench")
public ResponseEntity<?> changeTechnicianWorkbench(
    @PathVariable UUID technicianId,
    @RequestParam int benchNumber) {

      String message =
      technicianService.changeTechnicianWorkbench(
        technicianId,benchNumber);
      
        if (message.equals("The technician with that id does not exist")) {
          return 
          ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.equals(
                  "The workbench with that bench number does not exist")) {
                    return 
                ResponseEntity.status(HttpStatus.CONFLICT).body(message);
                  } else {
                    return ResponseEntity.ok(message);
                  }
    }

}
