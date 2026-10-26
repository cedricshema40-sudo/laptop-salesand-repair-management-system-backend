package rw.auca.laptop_salesand_repair_management_system.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import
rw.auca.laptop_salesand_repair_management_system.Domain.Workbench;
import rw.auca.laptop_salesand_repair_management_system.repository.WorkbenchRepository;

@Service
public class WorkbenchService {

     private final rw.auca.laptop_salesand_repair_management_system.repository.WorkbenchRepository workbenchRepository;

     public WorkbenchService(WorkbenchRepository workbenchRepository) {
        this.workbenchRepository = workbenchRepository;
     }
     public Workbench saveWorkbench(Workbench workbench){
         return workbenchRepository.save(workbench);
     }
     public List<Workbench> getAllWorkbenches() {
        return workbenchRepository.findAll();
     }
     public Workbench getWorkbenchById(UUID id) {
        return workbenchRepository.findById(id) .orElse(null);
     }
     public Workbench updateWorkbench(UUID id, Workbench updateWorkbench){
        Workbench existingWorkbench = workbenchRepository.findById(id)

              .orElse(null);
              if (existingWorkbench == null) {
                return null;
              }

              existingWorkbench.setBenchNumber(
                updateWorkbench.getBenchNumber());
              existingWorkbench.setName(updateWorkbench.getName());

              return workbenchRepository.save(existingWorkbench);

}

public boolean deleteWorkbench(UUID id) {
    Workbench existingWorkbench = workbenchRepository.findById(id)
           .orElse(null);

           if (existingWorkbench == null) {
            return false;
           }
           workbenchRepository.delete(existingWorkbench);
              return true ;
}
}
