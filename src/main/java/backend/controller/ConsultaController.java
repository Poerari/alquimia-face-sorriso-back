package backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Consulta;
import backend.repository.ConsultaRepository;

@RestController
@RequestMapping("/consultas")

public class ConsultaController{

    private final ConsultaRepository repository;

    public ConsultaController(ConsultaRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Consulta> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Consulta salvar(@RequestBody Consulta consulta) {
        return repository.save(consulta);
    }
}