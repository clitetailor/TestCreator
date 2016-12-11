/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.Database;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import objs.Question;
import org.json.simple.parser.ParseException;
import utils.FileSaver;

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
            this.addQuestion(question);
        });
        
        this.questionEditForm.removeTopPart();
        this.questionEditForm.setManaged(false);
        
        this.questionForm.removeTopPart();
        this.questionForm.setManaged(false);
        
        Database.initialize();
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
        questionPickerDialog.setSubjects(Database.getAllSubjects());
        questionPickerDialog.setOnSearchButtonClick((event) -> {
            ArrayList<Question> questions = new ArrayList<>();
            
            if ("EssayQuestion".equals(questionPickerDialog.getQuestionType())) {
                questions.addAll(Database.getEssayQuestionsBySubject(questionPickerDialog.getSubject(), questionPickerDialog.getLevel()));
            } else if ("ChoiceQuestion".equals(questionPickerDialog.getQuestionType())) {
                questions.addAll(Database.getChoiceQuestionsBySubject(questionPickerDialog.getSubject(), questionPickerDialog.getLevel()));
            } else {
                return;
            }
            
            try {
                questionPickerDialog.setQuestions(questions);
            } catch (IOException ex) {
                Logger.getLogger(TestEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        questionPickerDialog.setAddQuestionFunc((question) -> {
            this.addQuestion(question);
        });

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

        testGeneratorDialog.setSubjects(Database.getAllSubjects());
        
        testGeneratorDialog.setOnCancelButtonClick((ActionEvent subEvent) -> {
            stage.close();
        });
        
        testGeneratorDialog.setOnAddButtonClick((ActionEvent subEvent) -> {
            ArrayList<Question> questions = new ArrayList<>();
            String subject = testGeneratorDialog.getSubject();
            
            if (subject == null) {
                return;
            }
            
            ArrayList<Integer> choiceNumberByLevel = testGeneratorDialog.getChoiceNumberList();
            for (int i = 0; i < 5; ++i) {
                questions.addAll(Database.getRandomChoiceQuestion(subject, i, choiceNumberByLevel.get(i)));
            }
            
            ArrayList<Integer> essayNumberByLevel = testGeneratorDialog.getEssayNumberList();
            for (int i = 0; i < 5; ++i) {
                questions.addAll(Database.getRandomEssayQuestion(subject, i, essayNumberByLevel.get(i)));
            }
            
            Collections.shuffle(questions);
            
            for (Question question: questions) {
                this.addQuestion(question);
            }
        });

        stage.setTitle("Generate Test");
        stage.setScene(new Scene(testGeneratorDialog));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(currentWindow);
        stage.showAndWait();
    }

    @FXML
    private void onSaveButtonClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        ExtensionFilter extFilter = new ExtensionFilter("JSON files", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showSaveDialog(this.getScene().getWindow());
        
        if (file == null) {
            return;
        }
        
        FileSaver fileSaver = new FileSaver();
        fileSaver.saveQuestion(file.toPath().toString(), this.getQuestions());
    }
    
    private ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList();
        
        for (Node node: this.questionVBox.getChildren()) {
            QuestionBox questionBox = (QuestionBox) node;
            questions.add(questionBox.getQuestion());
        }
        
        return questions;
    }
    
    @FXML
    private void onImportButtonClick() throws IOException, FileNotFoundException, ParseException {
        FileChooser fileChooser = new FileChooser();
        ExtensionFilter extFilter = new ExtensionFilter("JSON files", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog(this.getScene().getWindow());
        
        if (file == null) {
            return;
        }
        
        FileSaver fileSaver = new FileSaver();
        ArrayList<Question> questions = fileSaver.readFile(file.toPath().toString());
        
        for (Question question: questions) {
            this.addQuestion(question);
        }
    }
    
    private void addQuestion(Question question) {
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
    }
}
