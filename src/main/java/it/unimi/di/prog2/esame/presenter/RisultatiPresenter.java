package it.unimi.di.prog2.esame.presenter;

import it.unimi.di.prog2.esame.Main;
import it.unimi.di.prog2.esame.model.Candidato;
import it.unimi.di.prog2.esame.model.Observable;
import it.unimi.di.prog2.esame.model.VotazioneModel;
import it.unimi.di.prog2.esame.view.CandidaView;
import it.unimi.di.prog2.esame.view.RisultatiView;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class RisultatiPresenter implements Presenter{

    RisultatiView view;
    VotazioneModel m;

    public RisultatiPresenter(@NotNull RisultatiView view, @NotNull VotazioneModel m) {
        this.view = view;
        this.m = m;
        m.addObserver(this);
    }


    @Override
    public void update(@NotNull Observable<List<Candidato>> subject, @NotNull List<Candidato> state) {
        int voti =0;
        for (Candidato cand : state) {
            voti = voti+cand.getVoti();
        }
        state.sort(Comparator.comparing(Candidato::getVoti).reversed());
        int i = 0;
        for (Candidato cand : state) {
            if (voti >= 3){
                view.set(i, cand.getNome() + " : " + cand.getVoti());
                i++;
            }

        }
        while (i <= Main.MAX_CANDIDATES) {
            view.set(i, " ");
            i++;
        }
    }

    @Override
    public void action(String text1, String text2) {
        //NULLA, SOLO DISPLAY DEI RISULTATI
    }
}
