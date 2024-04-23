package com.example.spring_mvc_thylemeaf_hospital.security.rep;

import com.example.spring_mvc_thylemeaf_hospital.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
