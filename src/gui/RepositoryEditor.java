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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class RepositoryEditor extends HBox {
    public RepositoryEditor() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RepositoryEditor.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
	this.questionForm.setManaged(false);
    }

    @FXML
    private VBox questionForm;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void onAddButtonClick(ActionEvent event) {
        this.questionForm.setVisible(true);
	this.questionForm.setManaged(true);
        
        this.scrollPane.setVvalue(0.0);
    }

    public EventHandler<ActionEvent> closeEventHandler = (ActionEvent event) -> { };

    public void setOnCloseButtonClick(EventHandler<ActionEvent> handler) {
        this.closeEventHandler = handler;
    }

    @FXML
    private void onCloseButtonClick(ActionEvent event) {
        this.closeEventHandler.handle(event);
    }
}
