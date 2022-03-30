package com.example.demo.Repositries;

import com.example.demo.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long>
{
    public AppUser findByUsername(String username);
}