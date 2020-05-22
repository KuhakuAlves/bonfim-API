package br.com.bonfim.capoeira.model.cadastro;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class HistoricoGraduacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean ativa;

    private LocalDate dtGraduacao;

    @ManyToOne()
    @JoinColumn(name = "aluno_id", foreignKey = @ForeignKey(name = "FK_ALUNO"))
    private Aluno aluno;

    @ManyToOne()
    @JoinColumn(name = "graduacao_id", foreignKey = @ForeignKey(name = "FK_GRADUACAO"))
    private Graduacao graduacao;

    public HistoricoGraduacao(){

    }

    public HistoricoGraduacao(Aluno aluno, Graduacao graduacao, boolean ativa, LocalDate dtGraduacao) {
        this.setAluno(aluno);
        this.setGraduacao(graduacao);
        this.ativa = ativa;
        this.dtGraduacao = dtGraduacao;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Graduacao getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(Graduacao graduacao) {
        this.graduacao = graduacao;
    }
}
