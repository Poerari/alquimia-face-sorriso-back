package backend.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;/* Coluna de ligação */
import jakarta.persistence.Entity; /* Tabela Intermediária */
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;




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

    public String getCpf(){
    return cpf;
    }

    public void setCpf(String cpf){
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

    public List<Especialidade> getEspecialidades() {
    return especialidades;
}

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    @JsonIgnoreProperties("dentistas")
    @ManyToMany
    @JoinTable(
        name = "dentista_especialidade",
        joinColumns = @JoinColumn(name = "dentista_id"),
        inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    private List<Especialidade> especialidades;
    
}
