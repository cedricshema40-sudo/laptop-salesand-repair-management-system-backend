package rw.auca.laptop_salesand_repair_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.laptop_salesand_repair_management_system.Domain.PartReplacement;
import 
rw.auca.laptop_salesand_repair_management_system.Domain.RepairStatus;

import rw.auca.laptop_salesand_repair_management_system.Domain.RepairJob;

import rw.auca.laptop_salesand_repair_management_system.repository.PartReplacementRepository;
import rw.auca.laptop_salesand_repair_management_system.repository.RepairJobRepository;

@Service
public class PartReplacementService {

    private final PartReplacementRepository partReplacementRepository;

    private final RepairJobRepository repairJobRepository;

    public PartReplacementService(
        PartReplacementRepository partReplacementRepository,
        RepairJobRepository repairJobRepository) {
            this.partReplacementRepository = partReplacementRepository;
            this.repairJobRepository = repairJobRepository;
        }
    public String savePartReplacement(
        UUID repairJobId, PartReplacement partReplacement) {

            RepairJob repairJob = 
            repairJobRepository.findById(repairJobId)
                    .orElse(null);

            if (repairJob == null) {

                return "The repair job with that id does not exist";
            }

            if (repairJob.getStatus() != RepairStatus.REPAIRED) {
                return "Part replacements can only be recorded for repaired jobs";
            }

            partReplacement.setRepairJob(repairJob);
            partReplacement.setReplacedDate(LocalDate.now());

            partReplacementRepository.save(partReplacement);


            return "Part replacement saved successfully";
        }
    public List<PartReplacement> getPartReplacementsByCustomerPhone(String phone) {

        return partReplacementRepository.findByCustomerPhone(phone);
    }
    public String deletePartReplacement(UUID id) {

        PartReplacement partReplacement = partReplacementRepository
                 .findById(id)
                 .orElse(null);

                 if (partReplacement == null) {
                    return "Part replacement with that id does not exist";
                 }
        partReplacementRepository.delete(partReplacement);

        return "Part replacement deleted successfully";
    }
}

