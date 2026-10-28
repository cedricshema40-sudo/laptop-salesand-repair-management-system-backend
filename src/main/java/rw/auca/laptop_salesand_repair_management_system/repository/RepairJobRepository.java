package rw.auca.laptop_salesand_repair_management_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import
rw.auca.laptop_salesand_repair_management_system.Domain.RepairJob;
import rw.auca.laptop_salesand_repair_management_system.Domain.RepairStatus;

public interface  RepairJobRepository
          extends JpaRepository<RepairJob, UUID> {

             List<RepairJob> findByStatusOrderByReceivedDateAsc(
                      RepairStatus status);

             List<RepairJob>
               findByReceivedDateBetweenOrderByReceivedDateAsc(
                   LocalDate start, LocalDate end);
        boolean existsByTechnician_IdAndReceivedDateAndStatusNot(
              UUID technicianId, LocalDate receivedDate,
              RepairStatus status);
        

          }
