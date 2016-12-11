/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import objs.Question;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class QuestionPickerDialog extends VBox {
    public QuestionPickerDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionPickerDialog.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
        this.questionSearchBox.setOnClearButtonClick((event) -> {
            this.clearQuestion();
        });
        
        this.questionSearchBox.setOnSearchButtonClick((event) -> {
            this.searchEventHandler.handle(event);
        });
    }
    
    @FXML
    private QuestionSearchBox questionSearchBox;
    
    @FXML
    private VBox questionFormVBox;
    
    public void setSubjects(ArrayList<String> subjects) {
        this.questionSearchBox.setSubjects(subjects);
    }
    
    public String getSubject() {
        return this.questionSearchBox.getSubject();
    }
    
    public int getLevel() {
        return this.questionSearchBox.getLevel();
    }
    
    public void setQuestions(ArrayList<Question> questions) throws IOException {
        for (Question question: questions) {
            QuestionBox questionBox = new QuestionBox(question);
            questionBox.toggleAddButtonBar();
            questionBox.setOnAddButtonClick((event) -> {
                this.addQuestionFunc.accept(question);
            });
            this.questionFormVBox.getChildren().add(questionBox);
        }  
    }
    
    private Consumer<Question> addQuestionFunc = (Question question) -> {  };
    
    public void setAddQuestionFunc(Consumer<Question> addQuestionFunc) {
        this.addQuestionFunc = addQuestionFunc;
    }
    
    EventHandler<ActionEvent> searchEventHandler = (event) -> { }; 
    
    public void setOnSearchButtonClick(EventHandler<ActionEvent> handler) {
        this.searchEventHandler = handler;
    }
    
    private void clearQuestion() {
        this.questionFormVBox.getChildren().clear();
    }
    
    public String getQuestionType() {
        return this.questionSearchBox.getQuestionType();
    }
}
