package com.group.CorreiosSimJava.entities.UsuarioImpl;

import com.group.CorreiosSimJava.entities.Estado;
import com.group.CorreiosSimJava.entities.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_admin")
public class Admin extends Usuario {

    private Integer faixaControle;

    public Admin() {
    }

    public Admin(Long id, String nome, String senha, int valor) {
        super(id,nome, senha);
        this.faixaControle = valor;
    }

    public Integer getFaixaControle() {
        return faixaControle;
    }

    public void setFaixaControle(Integer faixaControle) {
        this.faixaControle = faixaControle;
    }

}
