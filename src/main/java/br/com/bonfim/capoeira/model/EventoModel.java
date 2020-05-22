package br.com.bonfim.capoeira.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@Data
@Entity
@Table(name = "evento")
public class EventoModel {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private LocalDate dt_ocorrencia;

    private String hr_ocorrencia;

    private String responsavel;

    private String nome_evento;

    private String foto;

    private String endereco;

    private String bairro;

    private String cidade;

    private String estado;

    private String observacao;

    public EventoModel(){ }

    public EventoModel(Long id, LocalDate dt_ocorrencia, String hr_ocorrencia, String responsavel, String nome_evento, String foto, String endereco, String bairro, String cidade, String estado, String observacao) {
        this.id = id;
        this.dt_ocorrencia = dt_ocorrencia;
        this.hr_ocorrencia = hr_ocorrencia;
        this.responsavel = responsavel;
        this.nome_evento = nome_evento;
        this.foto = foto;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.observacao = observacao;
    }
}
