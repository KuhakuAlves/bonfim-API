package br.com.bonfim.capoeira.model.DTO;

import br.com.bonfim.capoeira.model.cadastro.HistoricoFinanceiro;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class HistoricoFinanceiroDTOSaida implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Preenchimento obrigatório")
    private LocalDate dtPagto;

    @NotNull(message = "Preenchimento obrigatório")
    private BigDecimal vlPagto;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer statusFunanceiro;

    @NotNull(message = "Preenchimento obrigatório")
    private Long alunoId;

    @NotNull(message = "Preenchimento obrigatório")
    private Long financeiroId;

    public HistoricoFinanceiroDTOSaida() {

    }

    public HistoricoFinanceiroDTOSaida(HistoricoFinanceiro obj) {
      this.id = obj.getId();
      this.dtPagto = obj.getDtPagto();
      this.vlPagto = obj.getVlPagto();
      this.statusFunanceiro = obj.getStatusFunanceiro().getCod();
      this.alunoId = obj.getAluno().getId();
      this.financeiroId = obj.getFinanceiro().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getStatusFunanceiro() {
        return statusFunanceiro;
    }

    public void setStatusFunanceiro(Integer statusFunanceiro) {
        this.statusFunanceiro = statusFunanceiro;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getFinanceiroId() {
        return financeiroId;
    }

    public void setFinanceiroId(Long financeiroId) {
        this.financeiroId = financeiroId;
    }
}
