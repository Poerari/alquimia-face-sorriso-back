package backend.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByDentistaIdAndDataInicioLessThanAndDataFimGreaterThan(
        Long dentistaId,
        LocalDateTime dataFim,
        LocalDateTime dataInicio
    );

}