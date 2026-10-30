package rw.auca.laptop_salesand_repair_management_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import 
rw.auca.laptop_salesand_repair_management_system.Domain.PartReplacement;

import rw.auca.laptop_salesand_repair_management_system.service.PartReplacementService;

@RestController 
@RequestMapping("/api/part-replacements")

public class PartReplacementController {

    private final PartReplacementService partReplacementService;

    public PartReplacementController(
        PartReplacementService partReplacementService) {
            this.partReplacementService = partReplacementService;
        }
    @PostMapping("/save")
    public  ResponseEntity<String> savePartReplacement(
            @RequestParam UUID repairJobId,
            @RequestBody PartReplacement partReplacement) {

                String message = 
                partReplacementService.savePartReplacement(repairJobId, partReplacement);
                    
                    if (message.equals("The repair job with that id does not exist")) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

                    } else if (message.equals(
                           "Part replacements can only be recorded for repaired jobs")) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

                           } else {
                            return ResponseEntity.status(HttpStatus.CREATED).body(message);
                           }
                    
                }
                @GetMapping("/customer/{phone}")
                public ResponseEntity<List<PartReplacement>>
                  getPartReplacementsByCustomerPhone(@PathVariable String phone) {
                    return ResponseEntity.ok(
                        partReplacementService.getPartReplacementsByCustomerPhone(phone));
                    
                  }
@DeleteMapping("/delete/{id}")
            public ResponseEntity<String> deletePartReplacement(
                @PathVariable UUID id) {

                    String message = 
                    partReplacementService.deletePartReplacement(id);

                    if (message.equals("Part replacement wth that id does not exist")) {
                        return 
                        ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
                    }
                    return ResponseEntity.ok(message);
                }
            
            }
    



