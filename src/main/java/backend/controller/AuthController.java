package backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Usuario;
import backend.repository.UsuarioRepository;
import backend.request.LoginRequest;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public Usuario login(
        @RequestBody LoginRequest request
    ) {

        Optional<Usuario> usuario =
            usuarioRepository.findByEmail(
                request.getEmail()
            );

        if (
            usuario.isPresent() &&
            usuario.get().getSenha().equals(
                request.getSenha()
            )
        ) {

            return usuario.get();

        }

        throw new RuntimeException(
            "Email ou senha inválidos"
        );

    }
}