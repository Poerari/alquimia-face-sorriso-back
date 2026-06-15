package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Paciente;
import backend.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin("*")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @GetMapping
    public List<Paciente> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Paciente buscarPorId(@PathVariable Long id) {
    return repository.findById(id)
            .orElseThrow();
    }

    @PutMapping("/{id}")
    public Paciente atualizar(@PathVariable Long id,
                          @RequestBody Paciente pacienteAtualizado) {

    Paciente paciente = repository.findById(id)
            .orElseThrow();

    paciente.setNome(pacienteAtualizado.getNome());
    paciente.setCpf(pacienteAtualizado.getCpf());
    paciente.setEmail(pacienteAtualizado.getEmail());
    paciente.setTelefone(pacienteAtualizado.getTelefone());

    return repository.save(paciente);
    }
    
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
    repository.deleteById(id);
    }

    @PostMapping
    public Paciente salvar(@RequestBody Paciente paciente) {

    if (repository.existsByCpf(paciente.getCpf())) {

        throw new org.springframework.web.server.ResponseStatusException(
            org.springframework.http.HttpStatus.BAD_REQUEST,
            "Já existe um paciente cadastrado com este CPF."
        );

    }

    if (repository.existsByEmail(paciente.getEmail())) {

        throw new org.springframework.web.server.ResponseStatusException(
            org.springframework.http.HttpStatus.BAD_REQUEST,
            "Já existe um paciente cadastrado com este e-mail."
        );

    }

    if (
    paciente.getCpf() == null
    ||
    !paciente.getCpf().matches("\\d{11}")
) {

    throw new org.springframework.web.server.ResponseStatusException(
        org.springframework.http.HttpStatus.BAD_REQUEST,
        "CPF inválido. Informe 11 números."
    );

}

    if (
        paciente.getEmail() == null
        ||
        !paciente.getEmail().matches(
            "^[A-Za-z0-9+_.-]+@(.+)$"
        )
    ) {

        throw new org.springframework.web.server.ResponseStatusException(
            org.springframework.http.HttpStatus.BAD_REQUEST,
            "E-mail inválido."
        );

    }

    if (
    paciente.getTelefone() == null
    ||
    !paciente.getTelefone().matches("\\d{10,11}")
) {

    throw new org.springframework.web.server.ResponseStatusException(
        org.springframework.http.HttpStatus.BAD_REQUEST,
        "Telefone inválido."
    );

}

    return repository.save(paciente);
}
}