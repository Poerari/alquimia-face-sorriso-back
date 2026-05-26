package backend.model;

import java.time.LocalDateTime;

import java.util.List;
import jakarta.persistence.JoinColumn;/* Coluna de ligação */
import jakarta.persistence.JoinTable; /* Tabela Intermediária */
import jakarta.persistence.ManyToMany;

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


    @ManyToMany /* Muitos para muitos  */
    @JoinTable( /* Intermediária - banco não sabe ligar ManyToMany sozinho  */
       name = "dentista_especialidade",  /* Nome tabela intermediária */
    joinColumns = @JoinColumn(name = "dentista_id"),  /* Cria a coluna dentista_id */
    inverseJoinColumns = @JoinColumn(name = "especialidade_id") /* Cria a coluna especialidade_id */
)
    private List<Especialidade> especialidades;
    
}
