package com.example.detection.controller;

import com.example.detection.Entity.Admin;
import com.example.detection.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login/Admin")
    public Admin authentification(@RequestBody Admin admin) {
        try {
            return adminService.exist(admin.getUsername(), admin.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
