/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class WelcomePage extends HBox {

    public WelcomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomePage.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        propertyOnNewButtonClick.set((event) -> {
        });
        propertyOnRepositoryButtonClick.set((event) -> {
        });
    }

    private final ObjectProperty<EventHandler<ActionEvent>> propertyOnNewButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();
    private final ObjectProperty<EventHandler<ActionEvent>> propertyOnRepositoryButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();

    @FXML
    private void onNewButtonClick(ActionEvent event) {
        propertyOnNewButtonClick.get().handle(event);
    }

    @FXML
    private void onRepositoryButtonClick(ActionEvent event) {
        propertyOnRepositoryButtonClick.get().handle(event);
    }

    public void setOnNewButtonClick(EventHandler<ActionEvent> handler) {
        propertyOnNewButtonClick.set(handler);
    }

    public void setOnRepositoryButtonClick(EventHandler<ActionEvent> handler) {
        propertyOnRepositoryButtonClick.set(handler);
    }
}
