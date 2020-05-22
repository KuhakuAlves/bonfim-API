package br.com.bonfim.capoeira.model.cadastro;

import br.com.bonfim.capoeira.model.cadastro.tipoEnum.EstadoCivil;
import br.com.bonfim.capoeira.model.cadastro.tipoEnum.OrientacaoSexual;
import br.com.bonfim.capoeira.model.cadastro.tipoEnum.Perfil;
import br.com.bonfim.capoeira.model.cadastro.tipoEnum.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String senha;

    private String apelido;

    private String foto;

    private boolean ativo;

    private String cpfOuCnpj;

    private Integer tipo;

    private LocalDateTime dtCad;

    private LocalDateTime dtAlte;

    private int estadoCivil;

    private int orientacaoSexual;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<EnderecoAluno> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE_ALUNO")
    private Set<String> telefones = new HashSet<>();

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    @OneToOne(mappedBy = "aluno")
    private Financeiro financeiro;

    public Aluno(){
        addPerfil(Perfil.ALUNO);
    }

    public Aluno(Long id, String nome, String email, String senha, String apelido, boolean ativo, String cpfOuCnpj, TipoCliente tipo, EstadoCivil estadoCivil, OrientacaoSexual orientacaoSexual) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.apelido = apelido;
        this.foto = foto;
        this.ativo = ativo;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo==null) ? null : tipo.getCod();
        this.estadoCivil = (estadoCivil==null)? null : estadoCivil.getCod();
        this.orientacaoSexual = (orientacaoSexual==null)? null : orientacaoSexual.getCod();
        addPerfil(Perfil.ALUNO);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }

    public LocalDateTime getDtCad() {
        return dtCad;
    }

    public void setDtCad(LocalDateTime dtCad) {
        this.dtCad = dtCad;
    }

    public LocalDateTime getDtAlte() {
        return dtAlte;
    }

    public void setDtAlte(LocalDateTime dtAlte) {
        this.dtAlte = dtAlte;
    }

    public EstadoCivil getEstadoCivil() {
        return EstadoCivil.toEnum(estadoCivil);
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil.getCod();
    }

    public OrientacaoSexual getOrientacaoSexual() {
        return OrientacaoSexual.toEnum(orientacaoSexual);
    }

    public void setOrientacaoSexual(OrientacaoSexual orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual.getCod();
    }

    public List<EnderecoAluno> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoAluno> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        this.perfis.add(perfil.getCod());
    }

    public Financeiro getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }

}
