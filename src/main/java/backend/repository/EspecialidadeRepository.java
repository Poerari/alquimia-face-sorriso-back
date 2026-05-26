package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long>{
    
}
