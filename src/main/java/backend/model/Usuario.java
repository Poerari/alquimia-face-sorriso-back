package backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) /*o banco crie um número automaticamente*/
    private Long id; /* Long - Tipo primitivo de dado com maior capacidade de armazenamento*/

    @Column(nullable = false) /* Obrigatorio o preenchimento do campo */
    private String nome; 

    @Column(unique = true, nullable = false) /* Não deixa repetir o mesmo CPF */
    private String cpf;

    @Column(nullable = false) /* Obrigatorio o preenchimento do campo */
    private String email;

    @Column(nullable = false) /* Obrigatorio o preenchimento do campo */
    private String senha;

    private LocalDateTime dataCriacao;

    private LocalDateTime ultimoLogin;
    
    @Column(nullable = false) /* Obrigatorio o preenchimento do campo */
    private String perfil;  

    private boolean ativo;

    
} 

