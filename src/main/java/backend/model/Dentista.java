package backend.model;

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
    private String cro;
    private String especialidade;
    private String telefone;
    private String email;

    public Long getId() {
        return id;
    }

    public String getNome (){
        return nome;
    }

    public void getNome (String nome){
        this.nome = nome;
    }

    public String getCro (){
        return cro;
    }

    public void setCro (String cro){
        this.cro = cro;
    }

    public String getEspecialidade (){
        return especialidade;
    }

    public void setEspecialidade(String especialidade){
        this.especialidade = especialidade;
    }

    public String getTelefone (){
        return telefone;
    }

    public void setTelefone (String telefone){
        this.telefone = telefone;
    }

    public String getemail (){
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }
}
