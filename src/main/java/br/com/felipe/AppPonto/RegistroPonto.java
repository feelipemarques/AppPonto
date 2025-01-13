package br.com.felipe.AppPonto;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "ponto")
@IdClass(ChaveComposta.class)
public class RegistroPonto {

    @Id
    private String funcional;

    @Id
    private String dia;

    private String hora_entrada;

    private String hora_saida;

    @Transient
    private boolean entrada = false;

    public RegistroPonto(String funcional, String dia, String hora_saida, boolean entrada){
        this.funcional = funcional;
        this.dia = dia;
        this.hora_saida = hora_saida;
        this.entrada = entrada;
    }

    public RegistroPonto(){

    }

    public RegistroPonto(String funcional, String dia, String hora_entrada) {
        this.funcional = funcional;
        this.dia = dia;
        this.hora_entrada = hora_entrada;
    }

    public String getFuncional() {
        return funcional;
    }

    public String getDia() {
        return dia;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public String getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(String hora_saida) {
        this.hora_saida = hora_saida;
    }
}

class ChaveComposta implements Serializable {

    private String funcional;
    private String dia;

    public ChaveComposta(){

    }

    public ChaveComposta(String funcional, String dia) {
        this.funcional = funcional;
        this.dia = dia;
    }

}
