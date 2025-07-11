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
    private final UsuariosService usuariosService;

    @Autowired
    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastroUsuario(@RequestBody @Valid Usuarios usuarios) {
        return usuariosService.cadastro(usuarios);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuarios usuarios) throws Exception {
        return usuariosService.login(usuarios);
    }


}
