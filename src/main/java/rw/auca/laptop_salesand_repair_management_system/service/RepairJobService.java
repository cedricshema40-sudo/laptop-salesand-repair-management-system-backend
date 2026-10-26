package rw.auca.laptop_salesand_repair_management_system.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import
rw.auca.laptop_salesand_repair_management_system.Domain.RepairJob;
import rw.auca.laptop_salesand_repair_management_system.repository.RepairJobRepository;


@Service
public class RepairJobService {

    private final RepairJobRepository repairJobRepository;

    public RepairJobService(RepairJobRepository repairJobRepository) {
        this.repairJobRepository = repairJobRepository;
    }
    public RepairJob saveRepairJob(RepairJob repairJob) {
        return repairJobRepository.save(repairJob);
    }
    public List<RepairJob> getAllRepairJobs() {
        return repairJobRepository.findAll();
    }
    public RepairJob getRepairJobById(UUID id) {
        return repairJobRepository.findById(id) .orElse(null);
    }
    public RepairJob updateRepairJob(UUID id, RepairJob updatedRepairJob) {
        RepairJob existingRepairJob = repairJobRepository.findById(id)
                      .orElse(null);
        if(existingRepairJob == null) {
            return null;
        }
        existingRepairJob.setReceivedDate(updatedRepairJob.getReceivedDate());
        existingRepairJob.setLaptopModel(updatedRepairJob.getLaptopModel());
        existingRepairJob.setIssue(updatedRepairJob.getIssue());
        existingRepairJob.setTechnician(updatedRepairJob.getTechnician());

        return repairJobRepository.save(existingRepairJob);
    }
 public boolean deleteRepairJob(UUID id) {
    RepairJob existingRepairJob = repairJobRepository.findById(id)
         .orElse(null);

         if (existingRepairJob == null){
            return false;
         }
         repairJobRepository.delete(existingRepairJob) ;
          return true;
 }
}
