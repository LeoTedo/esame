package it.unimi.di.prog2.esame.view;

import it.unimi.di.prog2.esame.presenter.Presenter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class CandidaView extends Region {
  @NotNull private final Button button;
  @NotNull private final TextField name;

  public CandidaView() {
    name = new TextField();
    button = new Button("Candida");

    setBackground(new Background(
        new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), Insets.EMPTY)));
    setBorder(new Border(
        new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));

    grid.add(name, 0, 0);
    grid.add(button, 1, 0);

    this.getChildren().add(grid);
  }

  public void addHandlers(@NotNull Presenter presenter) {
    button.setOnAction(eh -> presenter.action(name.getText(), null)); // pressione bottone
    name.setOnAction(eh -> presenter.action(name.getText(), null));   // tasto invio nella casella di testo
  }

  public void success() {
    setBackground(new Background(
        new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(5.0), Insets.EMPTY)));
  }

  public void fail() {
    setBackground(new Background(
        new BackgroundFill(Color.INDIANRED, new CornerRadii(5.0), Insets.EMPTY)));
  }
}
