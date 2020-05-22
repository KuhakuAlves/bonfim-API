package br.com.bonfim.capoeira.model.DTO;

import br.com.bonfim.capoeira.model.cadastro.HistoricoGraduacao;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class HistoricoGraduacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Preenchimento Obrigatório")
    private boolean ativa;

    @NotNull(message = "Preenchimento Obrigatório")
    private LocalDate dtGraduacao;

    @NotNull(message = "Preenchimento Obrigatório")
    private Long alunoId;

    @NotNull(message = "Preenchimento Obrigatório")
    private Long graduacaoId;

    public HistoricoGraduacaoDTO(){

    }

    public HistoricoGraduacaoDTO(HistoricoGraduacao obj) {
        this.id = obj.getId();
        this.ativa = obj.isAtiva();
        this.dtGraduacao = obj.getDtGraduacao();
        this.alunoId = obj.getAluno().getId();
        this.graduacaoId = obj.getGraduacao().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public LocalDate getDtGraduacao() {
        return dtGraduacao;
    }

    public void setDtGraduacao(LocalDate dtGraduacao) {
        this.dtGraduacao = dtGraduacao;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getGraduacaoId() {
        return graduacaoId;
    }

    public void setGraduacaoId(Long graduacaoId) {
        this.graduacaoId = graduacaoId;
    }
}
