package backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Dentista;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    // Métodos para buscar registros duplicados
    Optional<Dentista> findByCpf(String cpf);
    Optional<Dentista> findByEmail(String email);
    Optional<Dentista> findByCro(String cro);
}