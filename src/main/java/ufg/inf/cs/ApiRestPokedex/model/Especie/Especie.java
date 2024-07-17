package ufg.inf.cs.ApiRestPokedex.model.Especie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String types;
    private int height;
    private int weight;
    private int baseExperience;
    private String variation;
    private boolean genero;
    private String attack;

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getTypes() {
        return types;
    }

    public String getVariation() {
        return variation;
    }

    public String getAttack() {
        return attack;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypes(String types) {
        return this.types;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }
}
