package rw.auca.laptop_salesand_repair_management_system.Domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity 
@Table(name = "part_replacement")

public class PartReplacement {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String partName;

    private String notes;

    private int quantity;

    private LocalDate replacedDate;

    @ManyToOne 
    @JoinColumn(name = "repair_job_id")
    private RepairJob repairJob;

    

    public PartReplacement() {
        
    }

    public UUID getId() {
        return id;

    }
 public void setId(UUID id) {
    this.id = id;
 }
 public String getPartName(){
    return partName;
 }

 public void setPartName(String partName) {
    this.partName = partName;
 }

 public String getNotes() {
    return notes;
 }
 public void setNotes(String notes) {
    this.notes = notes;
 }
 public int getQuantity() {
    return quantity;
 }
 public void setQuantity(int quantity) {
    this.quantity = quantity;
 }
 public LocalDate getReplacedDate() {
    return replacedDate;
 }
 public void setReplacedDate(LocalDate replacedDate) {
    this.replacedDate = replacedDate;
 }
 public RepairJob getRepairJob() {
    return repairJob;
 }
 public void setRepairJob(RepairJob repairJob) {
    this.repairJob = repairJob;
 }
}
