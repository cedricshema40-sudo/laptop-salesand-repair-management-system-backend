package rw.auca.laptop_salesand_repair_management_system.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import rw.auca.laptop_salesand_repair_management_system.Domain.RepairStatus;
import rw.auca.laptop_salesand_repair_management_system.repository.CustomerRepository;
import rw.auca.laptop_salesand_repair_management_system.repository.RepairJobRepository;
import rw.auca.laptop_salesand_repair_management_system.repository.TechnicianRepository;
@Service 
public class DashboardService {



    private final CustomerRepository customerRepository;

    private final TechnicianRepository technicianRepository;

    private final RepairJobRepository repairJobRepository;

  
    public DashboardService(

        CustomerRepository customerRepository,
        TechnicianRepository technicianRepository,

        RepairJobRepository repairJobRepository) {

            this.customerRepository = customerRepository;
            this.technicianRepository = technicianRepository;
            this.repairJobRepository = repairJobRepository; 

            }

            public Map<String, Object> getDashboardSummary() {
                

                Map<String, Object>  summary = new LinkedHashMap<>();
                
                Map<String, Long> statusCounts = new LinkedHashMap<>();
                
                summary.put("totalCustomers", customerRepository.count());
                summary.put("totalTechnicians", technicianRepository.count());

                var repairJobs = repairJobRepository.findAll();

                summary.put("totalRepairJobs", repairJobs.size());

                
                for (RepairStatus status : RepairStatus.values()) {

                    long count = repairJobs.stream()
                           .filter(job -> job.getStatus() == status)
                           .count();

                           statusCounts.put(status.name(), count);
                }
                summary.put("repairJobsByStatus", statusCounts);

                return summary;
            
            }

    

}
