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

import
rw.auca.laptop_salesand_repair_management_system.Domain.Workbench;
import rw.auca.laptop_salesand_repair_management_system.service.WorkbenchService;

@RestController
@RequestMapping("/api/workbenches")
public class WorkbenchController {
 private final WorkbenchService workbenchService;

      public WorkbenchController(WorkbenchService workbenchService) {
        this.workbenchService = workbenchService;
      }
@PostMapping("/save")
 public ResponseEntity<?> saveWorkbench(@RequestBody Workbench  workbench) {

       workbenchService.saveWorkbench(workbench);

       return ResponseEntity.status(HttpStatus.CREATED)
                .body("Workbench saved successfully ");



    }
    @GetMapping("/all")
    public ResponseEntity<List<Workbench>> getAllWorkbenches() {
        return ResponseEntity.ok(
            workbenchService.getAllWorkbenches());

    }
     @GetMapping("/{id}")
     public ResponseEntity<?> getWorkbenchById(@PathVariable UUID id) {
          Workbench workbench = workbenchService.getWorkbenchById(id);
        if (workbench == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The workbench with that id does not exist");
        }
        return ResponseEntity.ok(workbench);
     }
     @PutMapping("/update/{id}")
     public ResponseEntity<?> updatedWorkbench(
        @PathVariable UUID id,
        @RequestBody Workbench updatedWorkbench) {

            Workbench workbench =
                   workbenchService.updateWorkbench(id, updatedWorkbench);

                   if(workbench == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                               .body("The workbench with that id does not exist");
                   }
                   return ResponseEntity.ok(workbench);
        }
     @DeleteMapping ("/delete/{id}")
       public ResponseEntity<?> deleteWorkbench(@PathVariable UUID id) {
         boolean deleted = workbenchService.deleteWorkbench(id);

         if(!deleted) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The workbench with that id does not exist");
         }
         return ResponseEntity.ok("Workbench deleted successfully");
       }

}
