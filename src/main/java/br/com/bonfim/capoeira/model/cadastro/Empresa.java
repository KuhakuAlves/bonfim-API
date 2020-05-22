package br.com.bonfim.capoeira.model.cadastro;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razaoSocial;

    private String nomeFantasia;

    private String cnpj;

    private String inscrEstadual;

    private String inscrMuniciapl;

    private String cnae;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<EnderecoEmpresa> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE_EMPRESA")
    private Set<String> telefones = new HashSet<>();

    public Empresa (){

    }

    public Empresa(Long id, String razaoSocial, String nomeFantasia, String cnpj, String inscrEstadual, String inscrMuniciapl, String cnae) {
        this.id    = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.inscrEstadual = inscrEstadual;
        this.inscrMuniciapl = inscrMuniciapl;
        this.cnae = cnae;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscrEstadual() {
        return inscrEstadual;
    }

    public void setInscrEstadual(String inscrEstadual) {
        this.inscrEstadual = inscrEstadual;
    }

    public String getInscrMuniciapl() {
        return inscrMuniciapl;
    }

    public void setInscrMuniciapl(String inscrMuniciapl) {
        this.inscrMuniciapl = inscrMuniciapl;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public List<EnderecoEmpresa> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoEmpresa> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }
}
