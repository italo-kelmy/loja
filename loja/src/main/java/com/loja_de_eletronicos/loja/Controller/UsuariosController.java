package com.loja_de_eletronicos.loja.Controller;

import com.loja_de_eletronicos.loja.Entity.Usuarios;
import com.loja_de_eletronicos.loja.Service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuariosController {

    private final UsuariosService service;

    @Autowired
    public UsuariosController(UsuariosService service) {
        this.service = service;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastro_de_usuario(@RequestBody @Valid Usuarios usuarios) {
        return service.cadastro(usuarios);
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuarios usuarios) throws Exception {
        return service.login(usuarios);
    }


}
