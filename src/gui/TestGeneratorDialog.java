/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class TestGeneratorDialog extends VBox {
    public TestGeneratorDialog() throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("TestGeneratorDialog.fxml"));
	loader.setRoot(this);
	loader.setController(this);
        loader.load();
    }
    
    EventHandler<ActionEvent> addEventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            
        }
    };
    
    public void setOnAddButtonClick(EventHandler<ActionEvent> handler) {
        this.addEventHandler = handler;
    }
    
    private void onAddButtonClick(ActionEvent event) {
        this.addEventHandler.handle(event);
    }
}
