package rw.auca.laptop_salesand_repair_management_system.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.laptop_salesand_repair_management_system.service.DashboardService;

@RestController 
@RequestMapping ("/api/dashboard")
public class DashboardController {


     private final DashboardService dashboardService;

     public DashboardController(DashboardService dashboardService) {
         this.dashboardService = dashboardService;
     }
     @GetMapping("/summary")

     public Map<String, Object> getDashboardSummary() {
        return dashboardService.getDashboardSummary();
     }
}
