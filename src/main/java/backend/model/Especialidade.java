package backend.model;

import java.util.List;

import jakarta.persistence.Column; /* Vira tabela no banco */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;/* Coluna de ligação */
import jakarta.persistence.JoinTable; /* Tabela Intermediária */
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

    @Column(nullable=false)
    private String nome;


    @ManyToMany /* Muitos para Muitos */
    @JoinTable(
        name = "dentista_especialidade", /* Cria dentista_especialidade */
        joinColumns= @JoinColumn(name = "dentista_id"),  /* Define quem é o dono da tabela (dentista_id) */
        inverseJoinColumns = @JoinColumn(name = "especialidade_id") /*Quem é o outro lado (especialidade_id)*/
    )
    
    private List<Especialidade> especialidade; /* Essa variável guarda várias especialidades." */

}
