package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Dentista;
import backend.repository.DentistaRepository;

@RestController
@RequestMapping("/dentistas")
@CrossOrigin("*")
public class DentistaController {

    @Autowired
    private DentistaRepository repository;

    @GetMapping
    public List<Dentista> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Dentista salvar(@RequestBody Dentista dentista) {
        return repository.save(dentista);
    }
}
