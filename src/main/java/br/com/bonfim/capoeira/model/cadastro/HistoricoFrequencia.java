package br.com.bonfim.capoeira.model.cadastro;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class HistoricoFrequencia implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private LocalDate dtOcorrencia;

    private boolean ativo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "aluno_id", foreignKey = @ForeignKey(name = "FK_ALUNO"))
    private Aluno aluno;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "aula_id", foreignKey = @ForeignKey(name = "FK_AULA"))
    private Aula aula;

    public HistoricoFrequencia(){}

    public HistoricoFrequencia(Aluno aluno, Aula aula, LocalDate dtOcorrencia, boolean ativo) {
        super();
        this.setAluno(aluno);
        this.setAula(aula);
        this.dtOcorrencia = dtOcorrencia;
        this.ativo = ativo;
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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }
}
