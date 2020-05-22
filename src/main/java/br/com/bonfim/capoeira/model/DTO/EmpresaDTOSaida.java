package br.com.bonfim.capoeira.model.DTO;

import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.Empresa;

import java.io.Serializable;

public class EmpresaDTOSaida implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nomeFantasia;

    private String cnpj;

    public EmpresaDTOSaida(){

    }

    public EmpresaDTOSaida(Empresa empresa){
        id = empresa.getId();
        nomeFantasia = empresa.getNomeFantasia();
        cnpj = empresa.getCnpj();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
