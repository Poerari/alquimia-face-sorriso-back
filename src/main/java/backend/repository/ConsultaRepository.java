package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}