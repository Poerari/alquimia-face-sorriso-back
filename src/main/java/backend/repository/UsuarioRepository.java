package backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
    
}
