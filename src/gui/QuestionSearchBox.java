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
public class QuestionSearchBox extends VBox {
    public QuestionSearchBox() throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionSearchBox.fxml"));
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
}
