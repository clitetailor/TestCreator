/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import objs.ChoiceAnswer;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class ChoiceAnswerBox extends VBox {

    public ChoiceAnswerBox() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoiceAnswerBox.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.load();

        this.setOnDeleteButtonClick((event) -> {
        });
    }

    @FXML
    private Button trueFalseButton;
    @FXML
    private TextArea answerTextArea;

    public ChoiceAnswer getChoiceAnswer() {
        ChoiceAnswer choiceAnswer;

        if (this.trueFalseButton.getText().equals("true")) {
            choiceAnswer = new ChoiceAnswer(this.answerTextArea.getText(), true);
        } else {
            choiceAnswer = new ChoiceAnswer(this.answerTextArea.getText(), false);
        }

        return choiceAnswer;
    }

    private final ObjectProperty<EventHandler<ActionEvent>> propertyOnDeleteButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();

    public void setOnDeleteButtonClick(EventHandler<ActionEvent> handler) {
        this.propertyOnDeleteButtonClick.set(handler);
    }

    @FXML
    private void onDeleteButtonClick(ActionEvent event) {
        this.propertyOnDeleteButtonClick.get().handle(event);
    }

    @FXML
    private void onTagButtonClick(ActionEvent event) {
        if (this.trueFalseButton.getText().equals("true")) {
            this.trueFalseButton.setText("false");
        } else {
            this.trueFalseButton.setText("true");
        }
    }

    public void setAnswer(ChoiceAnswer answer) {
        this.trueFalseButton.setText(answer.isTrue() ? "true" : "false");
        this.answerTextArea.setText(answer.getContent());
    }
}
