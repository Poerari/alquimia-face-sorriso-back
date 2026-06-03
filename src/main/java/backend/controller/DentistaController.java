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

    @GetMapping("/{id}")
    public Dentista buscarPorId(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow();
    }

    @PostMapping
    public Dentista salvar(@RequestBody Dentista dentista) {
        return repository.save(dentista);
    }

    @PutMapping("/{id}")
    public Dentista atualizar(@PathVariable Long id,
                          @RequestBody Dentista dentistaAtualizado) {

        Dentista dentista = repository.findById(id)
                .orElseThrow();

        dentista.setNome(dentistaAtualizado.getNome());
        dentista.setCpf(dentistaAtualizado.getCpf());
        dentista.setEmail(dentistaAtualizado.getEmail());
        dentista.setCro(dentistaAtualizado.getCro());
        dentista.setAtivo(dentistaAtualizado.getAtivo());

        return repository.save(dentista);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
    repository.deleteById(id);
    }
}
