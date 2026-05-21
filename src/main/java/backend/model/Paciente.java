package backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/*isso vira uma tabela no banco*/
@Entity

public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;


    @Column(unique = true)
    private String email;
    
    private String telefone;

    private LocalDateTime dataCriacao;
   

    public Long getId(){
        return id;
    }

    public Paciente() {
        this.dataCriacao = LocalDateTime.now();
    }
    
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
    
    public void setEmail (String email){
        this.email = email;
    } 

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone (String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }

    public void setDataCriacao (LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
