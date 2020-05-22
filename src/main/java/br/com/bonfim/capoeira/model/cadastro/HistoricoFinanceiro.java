package br.com.bonfim.capoeira.model.cadastro;

import br.com.bonfim.capoeira.model.cadastro.tipoEnum.StatusFinanceiro;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class HistoricoFinanceiro implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dtPagto;

    private BigDecimal vlPagto;

    private Integer statusFunanceiro;

    @ManyToOne
    @JoinColumn(name = "financeiro_id", foreignKey = @ForeignKey(name = "FK_FINANCEIRO"))
    private Financeiro financeiro;

    @ManyToOne()
    @JoinColumn(name = "aluno_id", foreignKey = @ForeignKey(name = "FK_ALUNO"))
    private Aluno aluno;


    public HistoricoFinanceiro(){

    }

    public HistoricoFinanceiro(Financeiro financeiro, Aluno aluno, LocalDate dtPagto, BigDecimal vlPagto, StatusFinanceiro statusFunanceiro) {
        this.setFinanceiro(financeiro);
        this.setAluno(aluno);
        this.dtPagto = dtPagto;
        this.vlPagto = vlPagto;
        this.statusFunanceiro = statusFunanceiro.getCod();
    }

    public LocalDate getDtPagto() {
        return dtPagto;
    }

    public void setDtPagto(LocalDate dtPagto) {
        this.dtPagto = dtPagto;
    }

    public BigDecimal getVlPagto() {
        return vlPagto;
    }

    public void setVlPagto(BigDecimal vlPagto) {
        this.vlPagto = vlPagto;
    }

    public StatusFinanceiro getStatusFunanceiro() {
        return StatusFinanceiro.toEnum(statusFunanceiro);
    }

    public void setStatusFunanceiro(StatusFinanceiro statusFunanceiro) {
        this.statusFunanceiro = statusFunanceiro.getCod();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Financeiro getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
