package it.unimi.di.prog2.esame.model;

import it.unimi.di.prog2.esame.presenter.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotazioneModel implements Observable<List<Candidato>>{
    private final List<Observer<List<Candidato>>> observers = new ArrayList<>();
    private final Map<Integer, Candidato> candidati = new HashMap<>();

    public void addCandidato(@NotNull String s) {
        assert !s.trim().isEmpty();
        assert s.trim().length() >= 3;


        if ( candidatoIsPresent(s.trim())) {
            System.err.println("Il candidato è già registrato!");
        }else{
            candidati.put(candidati.size(), new Candidato(s.trim(),0) );
            notifyObservers();
        }
    }

    public boolean candidatoIsPresent(String s) {
        for (Candidato cand : candidati.values()) {
            if ( s.equalsIgnoreCase(cand.getNome())) {
                return true;
            }
        }
        return false;
    }

    public void vote(int i) {
        assert i > 0 && i < 6;
        candidati.put(i, candidati.get(i).addVoto());
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (Observer<List<Candidato>> observer : observers) {
            observer.update(this, new ArrayList<>(candidati.values()));
        }
    }

    @Override
    public void addObserver(@NotNull Observer<List<Candidato>> observer) {
        observers.add(observer);
    }

    @Override
    public @NotNull List<Candidato> getState() {
        return new ArrayList<Candidato>();
    }


}
