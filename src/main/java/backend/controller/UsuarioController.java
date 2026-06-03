package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.model.Usuario;
import backend.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();

    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
    return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Usuário não encontrado"
        ));
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id,
                         @RequestBody Usuario usuarioAtualizado) {

    Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow();
            

    usuario.setNome(usuarioAtualizado.getNome());
    usuario.setCpf(usuarioAtualizado.getCpf());
    usuario.setEmail(usuarioAtualizado.getEmail());
    usuario.setSenha(usuarioAtualizado.getSenha());
    usuario.setPerfil(usuarioAtualizado.getPerfil());

    return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
    usuarioRepository.deleteById(id);
    }

    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario) {

        return usuarioRepository.save(usuario);
}
    
}