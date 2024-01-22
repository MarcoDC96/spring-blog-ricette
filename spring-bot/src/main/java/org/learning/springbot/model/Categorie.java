package org.learning.springbot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "categorie")
    private List<Ricette> ricette;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ricette> getRicette() {
        return ricette;
    }

    public void setRicette(List<Ricette> ricette) {
        this.ricette = ricette;
    }
}
