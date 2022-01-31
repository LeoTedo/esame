package it.unimi.di.prog2.esame.presenter;


import it.unimi.di.prog2.esame.Main;
import it.unimi.di.prog2.esame.model.Candidato;
import it.unimi.di.prog2.esame.model.Observable;
import it.unimi.di.prog2.esame.model.VotazioneModel;
import it.unimi.di.prog2.esame.view.CandidaView;
import it.unimi.di.prog2.esame.view.RisultatiView;
import it.unimi.di.prog2.esame.view.VotaView;
import org.jetbrains.annotations.NotNull;

import java.security.DomainCombiner;
import java.util.ArrayList;
import java.util.List;

public class VotaPresenter implements Presenter {

private VotazioneModel m;
private VotaView view;
private List<String> votanti = new ArrayList<>();
  public VotaPresenter(@NotNull VotaView view, @NotNull VotazioneModel m) {
    this.view = view;
    this.m = m;
    view.addHandlers(this);
    m.addObserver(this);
  }

  @Override
  public final void action(String text1, String text2) {
    //System.err.printf("{%s} [%s]\n", text1, text2);
    if(Integer.parseInt(text1)>=1000 && Integer.parseInt(text1)<=4000 && !votanti.contains(text1) && !text1.equals("")){
      for (int i = 0; i < view.lista().length; i++) {
        if (view.lista()[i].getText().equals("scheda bianca")){
          m.vote(i);
          votanti.add(text1);
         // break;
        } else if (view.lista()[i].getText().equals(text2)){
          m.vote(i);
          votanti.add(text1);
        }
      }

      view.success();
    } else{
      view.fail();
    }
  }

  @Override
  public void update(@NotNull Observable<List<Candidato>> subject, @NotNull List<Candidato> state) {
    int i = 0;
    for (Candidato cand : state) {
      view.set(i, cand.getNome());
      i++;
    }
    while (i < Main.MAX_CANDIDATES) {
      view.set(i, " ");
      i++;
    }
  }
}
