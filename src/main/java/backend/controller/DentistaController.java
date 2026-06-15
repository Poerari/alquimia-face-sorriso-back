package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.model.Dentista;
import backend.model.Especialidade;
import backend.repository.DentistaRepository;
import backend.repository.EspecialidadeRepository;

@RestController
@RequestMapping("/dentistas")
@CrossOrigin("*")
public class DentistaController {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private DentistaRepository repository;

    @GetMapping
    public List<Dentista> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Dentista buscarPorId(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dentista não encontrado."));
    }

    @PostMapping
    public Dentista salvar(@RequestBody Dentista dentista) {
        
        // VALIDAÇÕES DE DUPLICIDADE (Salvar Novo)
        if (repository.findByCpf(dentista.getCpf()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este CPF já está cadastrado.");
        }
        if (repository.findByEmail(dentista.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este e-mail já está cadastrado.");
        }
        if (repository.findByCro(dentista.getCro()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este CRO já está cadastrado.");
        }

        if (dentista.getEspecialidades() != null) {
            List<Especialidade> especialidades = dentista.getEspecialidades()
                    .stream()
                    .map(e -> especialidadeRepository.findById(e.getId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidade não encontrada.")))
                    .toList();
            dentista.setEspecialidades(especialidades);
        }

        return repository.save(dentista);
    }

    @PutMapping("/{id}")
    public Dentista atualizar(@PathVariable Long id, @RequestBody Dentista dentistaAtualizado) {
                    
        Dentista dentista = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dentista não encontrado."));

        // VALIDAÇÕES DE DUPLICIDADE (Edição - ignora se pertencer ao próprio ID sendo editado)
        repository.findByCpf(dentistaAtualizado.getCpf())
                .ifPresent(d -> {
                    if (!d.getId().equals(id)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este CPF já está sendo usado por outro dentista.");
                    }
                });

        repository.findByEmail(dentistaAtualizado.getEmail())
                .ifPresent(d -> {
                    if (!d.getId().equals(id)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este e-mail já está sendo usado por outro dentista.");
                    }
                });

        repository.findByCro(dentistaAtualizado.getCro())
                .ifPresent(d -> {
                    if (!d.getId().equals(id)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este CRO já está sendo usado por outro dentista.");
                    }
                });

        dentista.setNome(dentistaAtualizado.getNome());
        dentista.setCpf(dentistaAtualizado.getCpf());
        dentista.setEmail(dentistaAtualizado.getEmail());
        dentista.setCro(dentistaAtualizado.getCro());
        dentista.setAtivo(dentistaAtualizado.getAtivo());

        if (dentistaAtualizado.getEspecialidades() != null) {
            List<Especialidade> especialidadesNovas = dentistaAtualizado.getEspecialidades()
                    .stream()
                    .map(e -> especialidadeRepository.findById(e.getId()).orElseThrow())
                    .collect(java.util.stream.Collectors.toList());
            dentista.getEspecialidades().clear();
            dentista.getEspecialidades().addAll(especialidadesNovas);
        } else {
            dentista.getEspecialidades().clear();
        }

        return repository.save(dentista);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}