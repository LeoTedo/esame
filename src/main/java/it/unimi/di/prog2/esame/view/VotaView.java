package it.unimi.di.prog2.esame.view;

import it.unimi.di.prog2.esame.presenter.Presenter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

public class VotaView extends Region {

  @NotNull private final TextField codeId;
  @NotNull private final Label[] candidateNames;
  @NotNull private final Button[] voteButtons;


  public VotaView(int numRows, @NotNull String title) {

    candidateNames = new Label[numRows];
    voteButtons = new Button[numRows];
    codeId = new TextField();

    setBackground(new Background(
        new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
    setBorder(new Border(
        new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    Label titleLabel = new Label(title);
    titleLabel.setFont(Font.font("sans", 20));
    grid.add(titleLabel, 0, 0);
    grid.add(codeId, 0, 1);
    for (int i = 0; i < numRows; i++) {
      candidateNames[i] = new Label("");
      candidateNames[i].setFont(Font.font("monospace", 14));
      candidateNames[i].setPadding(new Insets(10, 10, 10, 10));
      voteButtons[i] = new Button("Vota");
      grid.add(candidateNames[i], 0, 2 + i);
      grid.add(voteButtons[i], 1, 2 + i);
    }
    this.getChildren().add(grid);

  }


  public void addHandlers(@NotNull Presenter presenter) {
    for (int i = 0; i < voteButtons.length; i++) {
      final int id = i;
      voteButtons[i].setOnAction(eh -> presenter.action(codeId.getText(), candidateNames[id].getText()));
    }
  }

  public void set(int i, @NotNull String s) {
    candidateNames[i].setText(s);
  }

  public void success() {
    setBackground(new Background(
        new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(5.0), Insets.EMPTY)));
  }

  public void fail() {
    setBackground(new Background(
        new BackgroundFill(Color.INDIANRED, new CornerRadii(5.0), Insets.EMPTY)));
  }


  public @NotNull Label[] lista() {
    return candidateNames;
  }
  public @NotNull int size() {
    return voteButtons.length;
  }
}
