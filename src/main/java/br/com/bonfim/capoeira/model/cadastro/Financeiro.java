package br.com.bonfim.capoeira.model.cadastro;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Financeiro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal vlMensalidade;

    private LocalDate dtVencimento;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="aluno_id", foreignKey = @ForeignKey(name = "FK_ALUNO"))
    private Aluno aluno;

    public Financeiro(){
    }

    public Financeiro(Aluno aluno, BigDecimal vlMensalidade, LocalDate dtVencimento ){
      this.setAluno(aluno);
      this.dtVencimento = dtVencimento;
      this.vlMensalidade = vlMensalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getVlMensalidade() {
        return vlMensalidade;
    }

    public void setVlMensalidade(BigDecimal vlMensalidade) {
        this.vlMensalidade = vlMensalidade;
    }

    public LocalDate getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(LocalDate dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

}
