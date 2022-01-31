package it.unimi.di.prog2.esame.presenter;

import it.unimi.di.prog2.esame.model.Candidato;

import java.util.List;

public interface Presenter extends Observer<List<Candidato>> {
  void action(String text1, String text2);
}
