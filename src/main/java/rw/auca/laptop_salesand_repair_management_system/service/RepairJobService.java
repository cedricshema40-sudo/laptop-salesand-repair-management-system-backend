package rw.auca.laptop_salesand_repair_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


import org.springframework.stereotype.Service;

import
rw.auca.laptop_salesand_repair_management_system.Domain.RepairJob;

import rw.auca.laptop_salesand_repair_management_system.repository.RepairJobRepository;
import 
rw.auca.laptop_salesand_repair_management_system.Domain.RepairStatus;

@Service
public class RepairJobService {

    private final RepairJobRepository repairJobRepository;

    public RepairJobService(RepairJobRepository repairJobRepository) {
        this.repairJobRepository = repairJobRepository;
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
  public List<RepairJob> getRepairJobsByStatus(
    rw.auca.laptop_salesand_repair_management_system.Domain.RepairStatus status) {
        return repairJobRepository 
                    .findByStatusOrderByReceivedDateAsc(status);
    }
  public List<RepairJob> getRepairJobsBetweenDates(
          LocalDate start, LocalDate end) {
            return repairJobRepository

        .findByReceivedDateBetweenOrderByReceivedDateAsc(start, end);
          }
    public RepairJob saveRepairJob(RepairJob repairJob) {
        repairJob.setStatus(RepairStatus.RECEIVED);
        return repairJobRepository.save(repairJob);
    }
    public String validateRepairJob(RepairJob repairJob) {
         
        if (repairJob.getReceivedDate() == null

                || repairJob.getReceivedDate().isBefore(LocalDate.now())) {
            return "Repair job date cannot be in the past";
        }

        if (repairJob.getTechnician().getId() == null
                 || repairJob.getTechnician().getId() == null) {
                    return "A technician must be selected";
                 } 
            
        
        boolean conflict = 

    repairJobRepository.existsByTechnician_IdAndReceivedDateAndStatusNot(
             repairJob.getTechnician().getId(),
             repairJob.getReceivedDate(),
             RepairStatus.ABANDONED);
    
    if (conflict) {
        return "Technician  already has a repair job on that date";
    }
   return null;
    }
  }

