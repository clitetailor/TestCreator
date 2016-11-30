/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import objs.Question;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class TestEditor extends HBox {

    public TestEditor() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestEditor.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        loader.load();

        this.setOnCloseButtonClick((event) -> {
        });

        this.questionForm.setOnDoneButtonClick((event) -> {
            Question question = this.questionForm.getQuestion();
            QuestionBox questionBox;

            try {
                questionBox = new QuestionBox(question);

                questionBox.setOnDeleteButtonClick((subevent) -> {
                    this.questionVBox.getChildren().remove(questionBox);
                });

                questionBox.setOnEditButtonClick((subevent) -> {
                    try {
                        this.questionEditForm.setVisible(true);
                        this.questionEditForm.setManaged(true);

                        this.scrollPane.setVvalue(0.0);

                        this.questionEditForm.setQuestion(question);

                        this.questionEditForm.setOnDoneButtonClick((subsubevent) -> {
                            questionBox.setQuestion(this.questionEditForm.getQuestion());
                        });

                        this.questionEditForm.setOnCancelButtonClick((subsubevent) -> {
                            this.questionEditForm.setVisible(false);
                            this.questionEditForm.setManaged(false);
                        });
                    } catch (IOException ex) {
                        Logger.getLogger(TestEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                this.questionVBox.getChildren().add(questionBox);

                this.questionForm.resetContent();
            } catch (IOException ex) {
                Logger.getLogger(TestEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.questionEditForm.removeTopPart();
        this.questionEditForm.setManaged(false);
        
        this.questionForm.removeTopPart();
        this.questionForm.setManaged(false);
    }

    @FXML
    private Button addButton;

    @FXML
    private VBox questionVBox;
    @FXML
    private QuestionForm questionForm;
    @FXML
    private QuestionForm questionEditForm;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox questionFormVBox;

    public final ObjectProperty<EventHandler<ActionEvent>> propertyOnCloseButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();

    public void setOnCloseButtonClick(EventHandler<ActionEvent> handler) {
        propertyOnCloseButtonClick.set(handler);
    }

    @FXML
    private void onCloseButtonClick(ActionEvent event) {
        propertyOnCloseButtonClick.get().handle(event);
    }

    @FXML
    private void onAddButtonClick() throws IOException {
        this.questionForm.setVisible(true);
        this.questionForm.setManaged(true);

        this.scrollPane.setVvalue(0.0);
    }

    @FXML
    private void onPrintButtonClick() {

    }

    @FXML
    private void onSearchButtonClick() throws IOException {
        Window currentWindow = this.getScene().getWindow();
        Stage stage = new Stage();

        QuestionPickerDialog questionPickerDialog = new QuestionPickerDialog();

        stage.setTitle("Search Question");
        stage.setScene(new Scene(questionPickerDialog));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(currentWindow);
        stage.showAndWait();
    }

    @FXML
    private void onGenerateButtonClick(ActionEvent event) throws IOException {
        Window currentWindow = this.getScene().getWindow();

        TestGeneratorDialog testGeneratorDialog = new TestGeneratorDialog();
        Stage stage = new Stage();

        testGeneratorDialog.setOnCancelButtonClick((ActionEvent subEvent) -> {
            stage.close();
        });

        stage.setTitle("Generate Test");
        stage.setScene(new Scene(testGeneratorDialog));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(currentWindow);
        stage.showAndWait();
    }

    @FXML
    private void onSaveButtonClick() {

    }
}
