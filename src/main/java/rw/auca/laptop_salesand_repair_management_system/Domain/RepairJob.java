package rw.auca.laptop_salesand_repair_management_system.Domain;


import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "repair_job")
public class RepairJob {
 
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate receivedDate;

    @Enumerated(EnumType.STRING)
    private RepairStatus status;

    private String laptopModel;
    private String issue;

    @ManyToOne 
    @JoinColumn(name = "customer_id")
     private Customer customer;

    @ManyToOne 
    @JoinColumn(name = "technician_id")
    private Technician technician;

    public RepairJob() {
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public LocalDate getReceivedDate() {
        return receivedDate;
    }
   public void setReceivedDate(LocalDate receivedDate) {
    this.receivedDate = receivedDate;
   }
   public RepairStatus getStatus() {
    return status;
   }
   public void setStatus(RepairStatus status) {
    this.status = status;
   }
   public String getLaptopModel() {
    return laptopModel;
   }
   public void setLaptopModel(String laptopModel) {
    this.laptopModel = laptopModel;
   }
   public String getIssue() {
    return issue;
   }
   public void setIssue(String issue) {
    this.issue = issue;
   }
   public Customer getCustomer(){
    return customer;
   }
   public void setCustomer(Customer customer) {
    this.customer = customer;
   }
   public Technician getTechnician() {
    return technician;
   }
   public void setTechnician(Technician technician) {
    this.technician = technician;
   }
}
