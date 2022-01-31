package it.unimi.di.prog2.esame;

import it.unimi.di.prog2.esame.model.VotazioneModel;
import it.unimi.di.prog2.esame.presenter.CandidaPresenter;
import it.unimi.di.prog2.esame.presenter.RisultatiPresenter;
import it.unimi.di.prog2.esame.presenter.VotaPresenter;
import it.unimi.di.prog2.esame.view.CandidaView;
import it.unimi.di.prog2.esame.view.RisultatiView;
import it.unimi.di.prog2.esame.view.VotaView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
  final public static int MAX_CANDIDATES = 5;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Elezioni Presidente Bocciofila 2022");

    CandidaView proposalView = new CandidaView();

    // MAX_CANDIDATES + riga SCHEDE BIANCHE
    VotaView seggioSede = new VotaView(MAX_CANDIDATES + 1, "Sede Bocciofila:");
    VotaView seggioParcheggioCOVID = new VotaView(MAX_CANDIDATES + 1, "DriveThrough:");

    GridPane gridPane = new GridPane();
    gridPane.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    gridPane.setPadding(new Insets(10, 10, 10, 10));

    gridPane.add(seggioSede, 0, 1);
    gridPane.add(seggioParcheggioCOVID, 1, 1);

    gridPane.add(proposalView, 0, 0);
    GridPane.setColumnSpan(proposalView, GridPane.REMAINING);

    // MAX_CANDIDATES + riga votanti + riga SCHEDE BIANCHE
    RisultatiView results = new RisultatiView(1 + MAX_CANDIDATES + 1, "Risultati elezione:");
    gridPane.add(results, 0, 2);
    results.set(0, "Numero Votanti:      0");
    GridPane.setColumnSpan(results, GridPane.REMAINING);


    VotazioneModel model = new VotazioneModel();
    model.addCandidato("scheda bianca");
    new VotaPresenter(seggioSede, model);
    new VotaPresenter(seggioParcheggioCOVID,model);
    new CandidaPresenter(proposalView,model);
    new RisultatiPresenter(results,model);

    Scene scene = new Scene(gridPane);
    primaryStage.setScene(scene);
    primaryStage.show();

    //HINT: utile dopo aver definito model per inizializzare viste
    model.notifyObservers();
  }
}
