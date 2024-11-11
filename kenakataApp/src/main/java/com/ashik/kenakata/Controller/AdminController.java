package com.ashik.kenakata.Controller;

import com.ashik.kenakata.Dto.User.AdminDto;
import com.ashik.kenakata.Entity.Admin;
import com.ashik.kenakata.Exception.ResourceNotFoundException;
import com.ashik.kenakata.Mapper.UserMapper.AdminMapper;
import com.ashik.kenakata.Service.User.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody AdminDto adminDto) {

        Admin admin = AdminMapper.INSTANCE.adminDtoToadmin(adminDto);
        AdminDto adminDto1 = AdminMapper.INSTANCE.adminToAdminDto(
                adminService.saveAdmin(admin)
        );

        return new ResponseEntity<>(adminDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        try {
            AdminDto adminDto1 = AdminMapper.INSTANCE.adminToAdminDto(
                    adminService.findAdminById(id)
            );
            return new ResponseEntity<>(adminDto1, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        try {
            List<Admin> admins = adminService.findAllAdmins();
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody AdminDto adminDto) {
        try {
            Admin admin = AdminMapper.INSTANCE.adminDtoToadmin(adminDto);
            Admin updatedAdmin = adminService.updateAdmin(id, admin);
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        try {
            adminService.deleteAdmin(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }   

}
