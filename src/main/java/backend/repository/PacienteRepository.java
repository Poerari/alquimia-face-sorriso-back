package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

}