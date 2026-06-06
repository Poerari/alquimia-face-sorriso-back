package backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Especialidade;
import backend.repository.EspecialidadeRepository;


@RestController /* essa classe vai responder requisições HTTP */
@RequestMapping("/especialidades") /* Cria a URL base */
@CrossOrigin("*")
public class EspecialidadeController {  /*Spring, injeta o repository aqui automaticamente*/
    

    private final EspecialidadeRepository repository;

    public EspecialidadeController(EspecialidadeRepository repository){
        this.repository = repository;
    }

    @GetMapping  /* Buscar todos*/
    public List<Especialidade> listar(){  /*Lista de especialidade*/
        return repository.findAll();  /* SELECT FROM especialidade */
    }

    @PostMapping /* POST = enviar dados. */
    public Especialidade salvar(@RequestBody  Especialidade especialidade){ /* Transforma em objeto */
        return repository.save(especialidade); /* INSERT INTO especialidade */
    }

    @GetMapping ("/{id}")/*Id na URL = local../especialidades/1 */
    public Especialidade buscarPorId(@PathVariable Long id){ /* Pega o valor da URL Long id = 7 */
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
    }

    @PutMapping("/{id}")
    public Especialidade atualizar(
            @PathVariable Long id,
            @RequestBody Especialidade especialidadeAtualizada) {

        Especialidade especialidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));

        especialidade.setNome(especialidadeAtualizada.getNome());

        return repository.save(especialidade);
    }

    @DeleteMapping("/{id}") /* DELETE /especialidades/1 */
    public void deletar(@PathVariable Long id){ /* Não retorna nada */
        repository.deleteById(id);
    }
    
}
