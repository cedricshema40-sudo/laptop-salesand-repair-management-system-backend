package rw.auca.laptop_salesand_repair_management_system.Domain;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "technician")
public class Technician {

     @Id 
     @GeneratedValue(strategy = GenerationType.UUID)
     private UUID id;

     private String firstName;
     private String lastName;
     private String phone;

     @ManyToOne 
     @JoinColumn(name = "workbench_id", nullable = true)
     private Workbench workbench;

@ManyToMany 
@JoinTable(
     name = "technician_repair_skill",
     joinColumns = @JoinColumn(name = "technician_id"),
     inverseJoinColumns = @JoinColumn(name = "repair_skill_id")
)
private List<RepairSkill> repairSkills = new ArrayList<>();

 public Technician() {
 }
 public UUID getId() {
    return id;
 }
 public void setId(UUID id) {
    this.id = id;
 }
 public String getFirstName() {
    return firstName;
 }
 public void setFirstName(String firstName) {
    this.firstName = firstName;
 }
 public  String getLastName() {
    return lastName;
 }

 public void setLastName(String lastName) {
    this.lastName = lastName;
 }
 public String getPhone() {
    return phone;
 }
 public void setPhone(String phone) {
    this.phone = phone;
 }
 public Workbench getWorkbench() {
    return workbench;
 }
 public void setWorkbench(Workbench workbench) {
    this.workbench = workbench;
 }
 
 public List<RepairSkill> getRepairSkills() {
    return repairSkills;
 }
 public void setRepairSkills(List<RepairSkill> repairSkills) {
    this.repairSkills = repairSkills;
 }
}
