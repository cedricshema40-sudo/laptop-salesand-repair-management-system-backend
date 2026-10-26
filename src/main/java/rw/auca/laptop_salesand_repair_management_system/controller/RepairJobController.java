package rw.auca.laptop_salesand_repair_management_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import  org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import
rw.auca.laptop_salesand_repair_management_system.Domain.RepairJob;
import rw.auca.laptop_salesand_repair_management_system.service.RepairJobService;


@RestController
@RequestMapping("/api/repair-jobs")
public class RepairJobController {

    private final RepairJobService repairJobService;

    public RepairJobController(RepairJobService repairJobService) {

        this.repairJobService = repairJobService;
    }
    @PostMapping("/save")
     public ResponseEntity<?> saveRepairJob(@RequestBody RepairJob repairJob) {
        repairJobService.saveRepairJob(repairJob);

        return ResponseEntity.status(HttpStatus.CREATED)
                 .body("Repair job saved successfully");
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


}
