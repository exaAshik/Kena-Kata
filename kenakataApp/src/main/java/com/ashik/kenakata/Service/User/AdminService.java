package com.ashik.kenakata.Service.User;

import com.ashik.kenakata.Entity.Admin;

import java.util.List;

public interface AdminService {

    Admin saveAdmin(Admin admin);

    Admin findAdminById(Long id);

    List<Admin> findAllAdmins();

    Admin updateAdmin(Long id, Admin admin);

    void deleteAdmin(Long id);

}
