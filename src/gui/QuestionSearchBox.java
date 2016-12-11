/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class QuestionSearchBox extends VBox {
    public QuestionSearchBox() throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionSearchBox.fxml"));
	loader.setRoot(this);
	loader.setController(this);
        loader.load();
        
        this.levelComboBox.getItems().addAll(1, 2, 3, 4, 5);
        this.typeComboBox.getItems().addAll("EssayQuestion", "ChoiceQuestion");
    }
    
    @FXML
    ComboBox typeComboBox;
    
    private EventHandler<ActionEvent> searchEventHandler = (ActionEvent event) -> { };
    
    public void setOnSearchButtonClick(EventHandler<ActionEvent> handler) {
        this.searchEventHandler = handler;
    }
    
    @FXML
    private void onSearchButtonClick(ActionEvent event) {
        this.searchEventHandler.handle(event);
    }
    
    private EventHandler<ActionEvent> clearEventHandler = (ActionEvent event) -> { };
    
    public void setOnClearButtonClick(EventHandler<ActionEvent> handler) {
        this.clearEventHandler = handler;
    }
    
    @FXML
    private void onClearButtonClick(ActionEvent event) {
        this.clearEventHandler.handle(event);
    }
    
    @FXML
    private ComboBox subjectComboBox;
    @FXML
    private ComboBox levelComboBox;
    
    public void setSubjects(ArrayList<String> subjects) {
        this.subjectComboBox.getItems().clear();
        this.subjectComboBox.getItems().addAll(subjects);
    }
    
    public String getSubject() {
        if (this.subjectComboBox.getValue() == null) {
            return null;
        }
        return this.subjectComboBox.getValue().toString();
    }
    
    public Integer getLevel() {
        if (this.levelComboBox.getValue() == null) {
            return null;
        }
        return Integer.parseInt(this.levelComboBox.getValue().toString());
    }
    
    public String getQuestionType() {
        if (this.typeComboBox.getValue() == null) {
            return null;
        }
        return this.typeComboBox.getValue().toString();
    }
    
    public void setSubject(String subject) {
        this.subjectComboBox.setValue(subject);
    }
}
