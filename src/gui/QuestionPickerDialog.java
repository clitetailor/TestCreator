/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

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
    }
    
    private EventHandler<ActionEvent> searchEventHandler = (ActionEvent event) -> { };
    
    public void setOnSearchButtonClick(EventHandler<ActionEvent> handler) {
        this.searchEventHandler = handler;
    }
    
    @FXML
    private void onSearchButtonClick(ActionEvent event) {
        searchEventHandler.handle(event);
    }
    
    public void getQuestion() {
        
    }
}
