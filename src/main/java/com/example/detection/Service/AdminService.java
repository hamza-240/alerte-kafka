package com.example.detection.Service;

import com.example.detection.Entity.Admin;
import com.example.detection.jpa.AdminRepo;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepo adminRepo;

    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin exist(String user , String pass){
        if (user !=null && pass != null){
            Admin admin = adminRepo.findByUsernameAndPassword(user, pass);
            return admin;
        }
        return null;
    }
}
