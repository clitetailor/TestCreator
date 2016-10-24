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
import objs.EssayQuestion;
import objs.Question;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class TestEditor extends HBox
{
    
    public TestEditor() throws IOException
    {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("TestEditor.fxml"));
	loader.setRoot(this);
	loader.setController(this);
	
	loader.load();
    }
    
    @FXML
    private Button addButton;

    @FXML
    private VBox questionVBox;

    @FXML
    private void onAddButtonClick() throws IOException
    {
	Question question = new EssayQuestion("What is this?", "This is a cat!", "None");
	QuestionForm questionForm = new QuestionForm(question);
	
	questionForm.setOnDeleteButtonClick((event) ->
	{
	    questionVBox.getChildren().remove(questionForm);
	});
	
	questionForm.setOnEditButtonClick((event) ->
	{
	    
	});
	
	questionVBox.getChildren().add(questionForm);
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
