package br.com.meujardim.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Planta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planta_id")
    private long plantaId;

    @NotBlank
    private String nome;

    @Column(name = "nome_cientifico")
    private String nomeCientifico;

    @NotBlank
    @Column(name = "tipo_de_planta")
    private String tipoDePlanta;

    @Column(name = "imagem_referencia")
    private String imagemReferencia;

    private String descricao;


    public Planta(String nome, String nomeCientifico, String tipoDePlanta, String imagemReferencia, String descricao) {
        this.nome = nome;
        this.nomeCientifico = nomeCientifico;
        this.tipoDePlanta = tipoDePlanta;
        this.imagemReferencia = imagemReferencia;
        this.descricao = descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public void setTipoDePlanta(String tipoDePlanta) {
        this.tipoDePlanta = tipoDePlanta;
    }

    public void setImagemReferencia(String imagemReferencia) {
        this.imagemReferencia = imagemReferencia;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
