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
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class RepositoryEditor extends HBox
{
    
    public RepositoryEditor() throws IOException
    {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("RepositoryEditor.fxml"));
	
	loader.setRoot(this);
	loader.setController(this);
	loader.load();
    }
    
    
    @FXML
    VBox questionAnchorPane;
    
    
    
    @FXML
    private void onAddButtonClick(ActionEvent event)
    {
	
    }
    
    
    
    public final ObjectProperty<EventHandler<ActionEvent>> propertyOnCloseButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();
    
    public void setOnCloseButtonClick(EventHandler<ActionEvent> handler)
    {
	propertyOnCloseButtonClick.set(handler);
    }
    
    @FXML
    private void onCloseButtonClick(ActionEvent event)
    {
	propertyOnCloseButtonClick.get().handle(event);
    }
}
