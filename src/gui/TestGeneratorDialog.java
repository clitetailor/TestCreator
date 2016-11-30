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
public class TestGeneratorDialog extends VBox {

    public TestGeneratorDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestGeneratorDialog.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    private EventHandler<ActionEvent> addEventHandler = (ActionEvent event) -> {
    };
    private EventHandler<ActionEvent> cancelEventHandler = (ActionEvent event) -> {
    };

    public void setOnAddButtonClick(EventHandler<ActionEvent> handler) {
        this.addEventHandler = handler;
    }

    @FXML
    private void onAddButtonClick(ActionEvent event) {
        this.addEventHandler.handle(event);
    }

    public void setOnCancelButtonClick(EventHandler<ActionEvent> handler) {
        this.cancelEventHandler = handler;
    }

    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        this.cancelEventHandler.handle(event);
    }

    public void getQuestions() {

    }
}
