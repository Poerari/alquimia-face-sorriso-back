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

    List<Consulta> consultas =
        consultaRepository.findAll();

    for (Consulta consulta : consultas) {

        if (
            "AGENDADA".equals(consulta.getStatus())
            &&
            consulta.getDataInicio()
                .isBefore(java.time.LocalDateTime.now())
        ) {

            consulta.setStatus("ATRASADA");

            consultaRepository.save(consulta);

        }

    }

    return consultas;

}

    @GetMapping("/{id}")
    public Consulta buscarPorId(@PathVariable Long id) {
        return consultaRepository.findById(id)
            .orElseThrow();
}

    @PutMapping("/{id}")
public Consulta atualizar(
        @PathVariable Long id,
        @RequestBody Consulta consultaAtualizada) {

    Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Consulta não encontrada"));

    consulta.setDescricao(
        consultaAtualizada.getDescricao()
    );

    consulta.setStatus(
        consultaAtualizada.getStatus()
    );

    consulta.setDataInicio(
        consultaAtualizada.getDataInicio()
    );

    // Toda consulta dura 15 minutos
    consulta.setDataFim(
        consultaAtualizada.getDataInicio()
            .plusMinutes(15)
    );

    consulta.setMotivoCancelamento(
        consultaAtualizada.getMotivoCancelamento()
    );

    // NÃO PERMITE DATAS PASSADAS
    if (
        consultaAtualizada.getDataInicio()
            .toLocalDate()
            .isBefore(java.time.LocalDate.now())
    ) {

        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Não é possível agendar consultas em datas passadas."
        );

    }

    // OBRIGA MOTIVO AO CANCELAR
    if (
        "CANCELADA".equals(
            consultaAtualizada.getStatus()
        )
        &&
        (
            consultaAtualizada.getMotivoCancelamento() == null
            ||
            consultaAtualizada.getMotivoCancelamento()
                .trim()
                .isEmpty()
        )
    ) {

        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Informe o motivo do cancelamento."
        );

    }

    // NÃO PERMITE CONCLUIR CONSULTA FUTURA
    if (
        "CONCLUIDA".equals(
            consultaAtualizada.getStatus()
        )
        &&
        consulta.getDataInicio()
            .isAfter(
                java.time.LocalDateTime.now()
            )
    ) {

        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Não é possível concluir uma consulta futura."
        );

    }

    if (
        consultaAtualizada.getDentista() != null
        &&
        consultaAtualizada.getDentista().getId() != null
    ) {

        Dentista dentista =
            dentistaRepository
                .findById(
                    consultaAtualizada
                        .getDentista()
                        .getId()
                )
                .orElseThrow(() ->
                    new RuntimeException(
                        "Dentista não encontrado"
                    )
                );

        consulta.setDentista(dentista);
    }

    if (
        consultaAtualizada.getPaciente() != null
        &&
        consultaAtualizada.getPaciente().getId() != null
    ) {

        Paciente paciente =
            pacienteRepository
                .findById(
                    consultaAtualizada
                        .getPaciente()
                        .getId()
                )
                .orElseThrow(() ->
                    new RuntimeException(
                        "Paciente não encontrado"
                    )
                );

        consulta.setPaciente(paciente);
    }

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

    // NÃO PERMITE DATAS PASSADAS
    if (consulta.getDataInicio().toLocalDate()
            .isBefore(java.time.LocalDate.now())) {

        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Não é possível agendar consultas em datas passadas."
        );
    }

    // TODA CONSULTA DURA 15 MINUTOS
    consulta.setDataFim(
        consulta.getDataInicio().plusMinutes(15)
    );

    // NÃO PERMITE CONFLITO DE HORÁRIOS
    boolean horarioOcupado =
        consultaRepository
            .existsByDentistaIdAndDataInicioLessThanAndDataFimGreaterThan(
                dentista.getId(),
                consulta.getDataFim(),
                consulta.getDataInicio()
            );

    if (horarioOcupado) {

        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Este dentista já possui uma consulta neste horário."
        );
    }

    consulta.setDentista(dentista);
    consulta.setPaciente(paciente);

    return consultaRepository.save(consulta);
}

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
    consultaRepository.deleteById(id);
}

    
}