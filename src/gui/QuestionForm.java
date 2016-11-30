/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import objs.ChoiceAnswer;
import objs.ChoiceQuestion;
import objs.EssayQuestion;
import objs.Question;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class QuestionForm extends VBox {

    public QuestionForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionForm.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.load();

        this.setOnCancelButtonClick((event) -> {
        });
        this.setOnDoneButtonClick((event) -> {
        });
        
        this.levelChoiceBox.getItems().setAll(1, 2, 3, 4, 5);
        this.levelChoiceBox.setValue(1);
    }

    @FXML
    private Label subjectLabel;
    @FXML
    private TextField subjectTextField;
    @FXML
    private Label levelLabel;
    @FXML
    private ChoiceBox levelChoiceBox;
    
    public void removeTopPart() {
        this.subjectLabel.setVisible(false);
        this.subjectLabel.setManaged(false);
        this.subjectTextField.setVisible(false);
        this.subjectTextField.setManaged(false);
        this.levelLabel.setVisible(false);
        this.levelLabel.setManaged(false);
        this.levelChoiceBox.setVisible(false);
        this.levelChoiceBox.setManaged(false);
    }

    private final ObjectProperty<EventHandler<ActionEvent>> propertyOnDoneButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();
    private final ObjectProperty<EventHandler<ActionEvent>> propertyOnCancelButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();

    public void setOnDoneButtonClick(EventHandler<ActionEvent> handler) {
        propertyOnDoneButtonClick.set(handler);
    }

    public void setOnCancelButtonClick(EventHandler<ActionEvent> handler) {
        propertyOnCancelButtonClick.set(handler);
    }

    @FXML
    private void onDoneButtonClick(ActionEvent event) {
        this.propertyOnDoneButtonClick.get().handle(event);
        this.setVisible(false);
        this.setManaged(false);
    }

    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        this.propertyOnCancelButtonClick.get().handle(event);
        this.setVisible(false);
        this.setManaged(false);
    }

    @FXML
    private VBox choiceAnswerVBox;

    @FXML
    private void onAddButtonClick(ActionEvent event) throws IOException {
        ChoiceAnswerBox choiceAnswerBox = new ChoiceAnswerBox();

        VBox.setVgrow(choiceAnswerBox, Priority.ALWAYS);

        this.choiceAnswerVBox.getChildren().add(choiceAnswerBox);
        this.tabPane.getSelectionModel().select(0);
        this.tabPane.getSelectionModel().select(1);
    }

    @FXML
    private TabPane tabPane;

    @FXML
    private TextArea questionTextArea;
    @FXML
    private TextArea answerTextArea;
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    public Question getQuestion() {
        if (tabPane.getSelectionModel().getSelectedIndex() == 0) {
            EssayQuestion essayQuestion = new EssayQuestion(questionTextArea.getText(), answerTextArea.getText(), descriptionTextArea.getText());
            return essayQuestion;
        } else if (tabPane.getSelectionModel().getSelectedIndex() == 1) {
            ArrayList choiceAnswerArrayList = new ArrayList();

            for (Node node : this.choiceAnswerVBox.getChildren()) {
                ChoiceAnswerBox choiceAnswerBox = (ChoiceAnswerBox) node;
                choiceAnswerArrayList.add(choiceAnswerBox.getChoiceAnswer());
            }

            ChoiceQuestion choiceQuestion = new ChoiceQuestion(questionTextArea.getText(), choiceAnswerArrayList);

            return choiceQuestion;
        } else {
            return null;
        }
    }

    public void resetContent() {
        this.questionTextArea.setText("");
        this.answerTextArea.setText("");
        this.descriptionTextArea.setText("");
        this.choiceAnswerVBox.getChildren().clear();
    }

    public void setQuestion(Question question) throws IOException {
        this.resetContent();

        if (question instanceof EssayQuestion) {
            tabPane.getSelectionModel().select(0);

            EssayQuestion essayQuestion = (EssayQuestion) question;

            questionTextArea.setText(essayQuestion.getContent());
            answerTextArea.setText(essayQuestion.getAnswer());
            descriptionTextArea.setText(essayQuestion.getDescription());
        }

        if (question instanceof ChoiceQuestion) {
            tabPane.getSelectionModel().select(1);

            ChoiceQuestion choiceQuestion = (ChoiceQuestion) question;

            this.choiceAnswerVBox.getChildren().clear();

            questionTextArea.setText(choiceQuestion.getContent());

            for (ChoiceAnswer answer : choiceQuestion.getAnswers()) {
                ChoiceAnswerBox choiceAnswerBox = new ChoiceAnswerBox();
                choiceAnswerBox.setAnswer(answer);

                this.choiceAnswerVBox.getChildren().add(choiceAnswerBox);
            }
        }
    }
}
