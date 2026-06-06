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

import backend.model.Consulta;
import backend.model.Dentista;
import backend.model.Paciente;
import backend.repository.ConsultaRepository;
import backend.repository.DentistaRepository;
import backend.repository.PacienteRepository;

@RestController
@RequestMapping("/consultas")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public List<Consulta> listar() {
        return consultaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Consulta buscarPorId(@PathVariable Long id) {
        return consultaRepository.findById(id)
            .orElseThrow();
}


    @PutMapping("/{id}")
    public Consulta atualizar(@PathVariable Long id,
                          @RequestBody Consulta consultaAtualizada) {

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow();

        consulta.setDescricao(consultaAtualizada.getDescricao());
        consulta.setStatus(consultaAtualizada.getStatus());
        consulta.setDataInicio(consultaAtualizada.getDataInicio());
        consulta.setDataFim(consultaAtualizada.getDataFim());
        consulta.setMotivoCancelamento(
                consultaAtualizada.getMotivoCancelamento());

        return consultaRepository.save(consulta);
}

    @PostMapping
    public Consulta salvar(@RequestBody Consulta consulta) {
        Dentista dentista = dentistaRepository
            .findById(consulta.getDentista().getId())
            .orElseThrow();

        Paciente paciente = pacienteRepository
            .findById(consulta.getPaciente().getId())
            .orElseThrow();

            consulta.setDentista(dentista);
            consulta.setPaciente(paciente);

            return consultaRepository.save(consulta);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
    consultaRepository.deleteById(id);
}

    
}