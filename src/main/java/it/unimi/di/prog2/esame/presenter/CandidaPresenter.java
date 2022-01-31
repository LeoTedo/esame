package it.unimi.di.prog2.esame.presenter;

import it.unimi.di.prog2.esame.model.Candidato;
import it.unimi.di.prog2.esame.model.Observable;
import it.unimi.di.prog2.esame.model.VotazioneModel;
import it.unimi.di.prog2.esame.view.CandidaView;
import it.unimi.di.prog2.esame.view.VotaView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CandidaPresenter implements Presenter{

    CandidaView view;
    VotazioneModel m;

    public CandidaPresenter(@NotNull CandidaView view, @NotNull VotazioneModel m) {
        this.view = view;
        this.m = m;
        view.addHandlers(this);
        m.addObserver(this);
    }

    @Override
    public void update(@NotNull Observable<List<Candidato>> subject, @NotNull List<Candidato> state) {
        //view.success();
    }

    @Override
    public void action(String text1, String text2) {
        //System.err.println("voto per " + text1);
        if (!text1.equals("schede bianche")){
            m.addCandidato(text1);
        }

    }
}
