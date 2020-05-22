package br.com.bonfim.capoeira.model.DTO;

import br.com.bonfim.capoeira.model.cadastro.HistoricoFrequencia;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class HistoricoFrequenciaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long Id;

    @NotNull(message = "Preenchimento obrigatório")
    private LocalDate dtOcorrencia;

    @NotNull(message = "Preenchimento obrigatório")
    private boolean ativo;

    @NotNull(message = "Preenchimento obrigatório")
    private Long alunoId;

    @NotNull(message = "Preenchimento obrigatório")
    private Long aulaId;

    public HistoricoFrequenciaDTO(){

    }

    public HistoricoFrequenciaDTO(HistoricoFrequencia obj) {
        Id = obj.getId();
        dtOcorrencia = obj.getDtOcorrencia();
        ativo = obj.isAtivo();
        alunoId = obj.getAluno().getId();
        aulaId = obj.getAula().getId();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getDtOcorrencia() {
        return dtOcorrencia;
    }

    public void setDtOcorrencia(LocalDate dtOcorrencia) {
        this.dtOcorrencia = dtOcorrencia;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getAulaId() {
        return aulaId;
    }

    public void setAulaId(Long aulaId) {
        this.aulaId = aulaId;
    }
}
