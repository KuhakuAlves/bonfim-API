package br.com.bonfim.capoeira.model.cadastro;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "graduacao")
public class Graduacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;


    private String categoria;

    private String nome_corda;

    private String desc_corda;

    private String significado;

    @Lob
    private String observacao;

    public Graduacao(){

    }

    public Graduacao(Long id, String categoria, String nome_corda, String desc_corda, String significado, String observacao) {
        this.id = id;
        this.categoria = categoria;
        this.nome_corda = nome_corda;
        this.desc_corda = desc_corda;
        this.significado = significado;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome_corda() {
        return nome_corda;
    }

    public void setNome_corda(String nome_corda) {
        this.nome_corda = nome_corda;
    }

    public String getDesc_corda() {
        return desc_corda;
    }

    public void setDesc_corda(String desc_corda) {
        this.desc_corda = desc_corda;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}


