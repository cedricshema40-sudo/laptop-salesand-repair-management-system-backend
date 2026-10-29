package rw.auca.laptop_salesand_repair_management_system.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


import org.springframework.http.HttpStatus;
import  org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import
rw.auca.laptop_salesand_repair_management_system.Domain.RepairJob;
import rw.auca.laptop_salesand_repair_management_system.service.RepairJobService;
import 
 rw.auca.laptop_salesand_repair_management_system.Domain.RepairStatus;

@RestController
@RequestMapping("/api/repair-jobs")
public class RepairJobController {

    private final RepairJobService repairJobService;

    public RepairJobController(RepairJobService repairJobService) {

        this.repairJobService = repairJobService;
    }
    
@GetMapping("/all")
public ResponseEntity<List<RepairJob>> getAllRepairJobs() {
    return ResponseEntity.ok(repairJobService.getAllRepairJobs());

}
@GetMapping("/{id}")
public ResponseEntity<?> getRepairJobById(@PathVariable UUID id){
    RepairJob repairJob = repairJobService.getRepairJobById(id);

    if (repairJob == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The repair job with that id does not exist");
    }
    return ResponseEntity.ok(repairJob);
}

@PutMapping("/update/{id}")
public ResponseEntity<?> updateRepairJob(
    @PathVariable UUID id,
    @RequestBody RepairJob updatedRepairJob) {
        RepairJob repairJob =
               repairJobService.updateRepairJob(id, updatedRepairJob);

               if (repairJob == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The repair job with that id does not exist");

               }
               return ResponseEntity.ok("Repair job updated successfully");
    }
@DeleteMapping("/delete/{id}")
public ResponseEntity<?> deleteRepairJob(@PathVariable UUID id) {
    boolean deleted = repairJobService.deleteRepairJob(id);

    if (!deleted) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The repair job with that id does not exist");
    }
    return ResponseEntity.ok("Repair job deleted successfully");
}


@GetMapping("/by-status")
public ResponseEntity<List<RepairJob>> getRepairJobsByStatus(
        @RequestParam RepairStatus status) {

            return ResponseEntity.ok(
                repairJobService.getRepairJobsByStatus(status));
        }
@GetMapping("/between")
public ResponseEntity<List<RepairJob>>
  getRepairJobsBetweenDates(
       @RequestParam String start,
       @RequestParam String end) {

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return ResponseEntity.ok(
               repairJobService.getRepairJobsBetweenDates(startDate, endDate));
       }
@PostMapping("/save")
public ResponseEntity<?> saveRepairJob(@RequestBody RepairJob repairJob) {
  String message =
   repairJobService.validateRepairJob(repairJob);

  if (message != null) {
    if (message.equals("Repair job date cannot be in the past")) {
        return ResponseEntity.status(
            HttpStatus.BAD_REQUEST).body(message);

    } else if (message.equals("Technician already has a repair job on that date")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);

    }else {
         return ResponseEntity.status(
            HttpStatus.BAD_REQUEST).body(message);
        
    }

  } else {
    repairJobService.saveRepairJob(repairJob);
      return ResponseEntity.status(HttpStatus.CREATED)
            .body("Repair job saved successfully");
  }
    
       
}
@PatchMapping("/{id}/status")
public ResponseEntity<?> updateRepairJobStatus(
     @PathVariable UUID id,
     @RequestParam RepairStatus status) {

        String message = repairJobService.updateRepairJobStatus(id, status);

        if (message.equals("The repair job with that id does not exist")) {
        
            return 
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.startsWith("Cannot change status from"))

   {
            return 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

   }else {
          return ResponseEntity.ok(message);
        }
     }


}
