package com.example.spring_mvc_thylemeaf_hospital.security.service;

import com.example.spring_mvc_thylemeaf_hospital.security.entities.AppRole;
import com.example.spring_mvc_thylemeaf_hospital.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username,String password,String email,String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username , String role);
    void removeRoleFromUser(String username,String role);
    AppUser loadUserByUsername(String username);





}
