package backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column; /* Vira tabela no banco */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany; /* Muitos para Muitos */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Especialidade {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
    this.id = id;
}

    @Column(nullable = false)
    private String nome;

   
    @JsonIgnoreProperties("especialidades")
    @ManyToMany(mappedBy = "especialidades")
    private List<Dentista> dentistas;

}
