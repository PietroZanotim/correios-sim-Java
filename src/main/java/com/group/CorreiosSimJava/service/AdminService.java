package com.group.CorreiosSimJava.service;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Admin;
import com.group.CorreiosSimJava.exceptions.DatabaseInvalidException;
import com.group.CorreiosSimJava.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin findById(int id) {
        Optional<Admin> obj = adminRepository.findById(Long.valueOf(id));
        return obj.orElseThrow(() -> new DatabaseInvalidException("Usuario não encontrado"));
    }

}
