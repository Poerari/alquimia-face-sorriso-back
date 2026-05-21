package backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Dentista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
   
    
    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String cro;
    
    private Boolean ativo;

    private LocalDateTime dataCriacao;

    public Long getId() {
        return id;
    }


    public Dentista() {
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
    }

    public String getNome (){
        return nome;
    }

    public void setNome (String nome){
        this.nome = nome;
    }

    public String setCpf (){
        return cpf;
    }

    public void getCpf (String cpf){
        this.cpf = cpf;
    }

    public String getEmail (){
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public String getCro (){
        return cro;
    }

    public void setCro (String cro){
        this.cro = cro;
    }

    public Boolean getAtivo (){
        return ativo;
    }

    public void setAtivo (Boolean ativo){
        this.ativo = ativo;
    }

   public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    

    
}
