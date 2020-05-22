package br.com.bonfim.capoeira.model.DTO;

import br.com.bonfim.capoeira.model.cadastro.Aluno;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FinanceiroDTO {

    @NotNull(message = "Preenchimento obrigatório")
    private Long alunoId;

    @NotNull(message = "Preenchimento obrigatório")
    private BigDecimal vlMensalidade;

    @NotNull(message = "Preenchimento obrigatório")
    private LocalDate dtVencimento;

    public FinanceiroDTO(){

    }

    public FinanceiroDTO( Long alunoId, BigDecimal vlMensalidade, LocalDate dtVencimento) {
        this.alunoId = alunoId;
        this.vlMensalidade = vlMensalidade;
        this.dtVencimento = dtVencimento;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
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
}
