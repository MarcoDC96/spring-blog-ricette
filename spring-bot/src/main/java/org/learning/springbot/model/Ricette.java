package org.learning.springbot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "ricette")
public class Ricette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotEmpty
    private String titolo;
    @Lob
    @Column(nullable = false)
    @NotEmpty
    private String ingredienti;
    private String foto;
    @Column(nullable = false)
    @NotNull
    private BigDecimal tempoPreparazione;
    private Integer numeroPorzioni;
    @Lob
    private String testo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public BigDecimal getTempoPreparazione() {
        return tempoPreparazione;
    }

    public void setTempoPreparazione(BigDecimal tempoPreparazione) {
        this.tempoPreparazione = tempoPreparazione;
    }

    public Integer getNumeroPorzioni() {
        return numeroPorzioni;
    }

    public void setNumeroPorzioni(Integer numeroPorzioni) {
        this.numeroPorzioni = numeroPorzioni;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
