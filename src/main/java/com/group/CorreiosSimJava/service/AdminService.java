package com.group.CorreiosSimJava.service;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Admin;
import com.group.CorreiosSimJava.exceptions.DatabaseInvalidException;
import com.group.CorreiosSimJava.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> autenticar(String nome, String senha) {
        return adminRepository.findByNomeAndSenha(nome, senha);
    }

    public Admin salvarAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

}
