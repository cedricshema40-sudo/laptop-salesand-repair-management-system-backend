package rw.auca.laptop_salesand_repair_management_system.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

import
 rw.auca.laptop_salesand_repair_management_system.Domain.Technician;
 import rw.auca.laptop_salesand_repair_management_system.repository.TechnicianRepository;

@Service
public class TechnicianService {


      private final TechnicianRepository technicianRepository;

      public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
      }
     public Technician saveTechnician(Technician technician){
        return technicianRepository.save(technician);
     }
     public List<Technician> getAllTechnicians() {
          return technicianRepository.findAll();
     }
     public Technician getTechnicianById(UUID id) {
        return technicianRepository.findById(id).orElse(null);
     }
     public Technician updateTechnician(UUID id, Technician updatedTechnician) {
        Technician existingTechnician = technicianRepository.findById(id)
            .orElse(null);

            if (existingTechnician == null) {
                return null;
            }
            existingTechnician.setFirstName(updatedTechnician.getFirstName());
            existingTechnician.setLastName(updatedTechnician.getLastName());
            existingTechnician.setPhone(updatedTechnician.getPhone());
            existingTechnician.setWorkbench(updatedTechnician.getWorkbench());
            existingTechnician.setRepairSkills(updatedTechnician.getRepairSkills());

            return technicianRepository.save(existingTechnician);
     }
      public boolean deleteTechnician(UUID id) {
        Technician existingTechnician = technicianRepository.findById(id)
             .orElse(null);

             if(existingTechnician == null) {
                return false;
             }
             technicianRepository.delete(existingTechnician);
              return true;
      }
}
