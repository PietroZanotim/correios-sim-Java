package com.group.CorreiosSimJava.service;

import com.group.CorreiosSimJava.repository.AdminRepository;
import com.group.CorreiosSimJava.repository.FreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreteService {

    @Autowired
    private FreteRepository freteRepository;

}
