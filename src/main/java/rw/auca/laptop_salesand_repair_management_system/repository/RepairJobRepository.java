package rw.auca.laptop_salesand_repair_management_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
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
        @Query("SELECT r.status, COUNT(r) FROM RepairJob r GROUP BY r.status")
 

        List<Object[]> countRepairJobsByStatus();

        @Modifying 
        @Transactional 
        @Query ("UPDATE RepairJob r SET r.status = :newStatus " +
                "WHERE r.technician.id = :technicianId " +
                "AND r.receivedDate = :date " +
                "AND r.status <> :excludedStatus")

            int cancelRepairJobsForTechnician(

                @Param("technicianId") UUID technicianId,
                @Param("date") LocalDate date,
                @Param("newStatus") RepairStatus newStatus,
                @Param("excludedStatus") RepairStatus excludedStatus);
        
          }
