package br.com.bonfim.capoeira.model.DTO;

import br.com.bonfim.capoeira.model.cadastro.Empresa;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmpresaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String razaoSocial;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nomeFantasia;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cnpj;

    private String inscrEstadual;

    private String inscrMuniciapl;

    private String cnae;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cep;

    private String telefone1;

    private String telefone2;

    private String telefone3;

    private Long cidadeId;


    public EmpresaDTO(){

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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }
}
