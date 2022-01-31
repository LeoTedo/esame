package it.unimi.di.prog2.esame.model;

import org.jetbrains.annotations.NotNull;

public class Candidato {

    private String nome;
    private int voti;

    public Candidato(@NotNull String nome, @NotNull int voti) {
        assert voti >=0;
        this.nome = nome;
        this.voti = voti;
    }

    public Candidato addVoto() {
        return new Candidato(nome, voti+1);
    }

    public String getNome() {
        return nome;
    }

    public int getVoti() {
        return voti;
    }


}
