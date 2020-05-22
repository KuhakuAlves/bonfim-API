package br.com.bonfim.capoeira.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Resultado {
    private String resultado;

    public Resultado(String resultado){
        this.resultado = resultado;
    }
}
