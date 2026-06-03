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
    
    public Usuario() {
    this.dataCriacao = LocalDateTime.now();
    this.ativo = true;
}
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) /*o banco crie um número automaticamente*/
    private Long id; /* Long - Tipo primitivo de dado com maior capacidade de armazenamento*/

    public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getCpf() {
    return cpf;
}

public void setCpf(String cpf) {
    this.cpf = cpf;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getSenha() {
    return senha;
}

public void setSenha(String senha) {
    this.senha = senha;
}

public String getPerfil() {
    return perfil;
}

public void setPerfil(String perfil) {
    this.perfil = perfil;
}

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

