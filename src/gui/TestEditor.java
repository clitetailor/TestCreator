/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	
	this.setOnCloseButtonClick((event) -> {});
	
	this.questionForm.setOnDoneButtonClick((event) ->
		{
		    Question question = this.questionForm.getQuestion();
		    QuestionBox questionBox;
		    
		    try
		    {
			questionBox = new QuestionBox(question);
			
		        this.questionVBox.getChildren().add(questionBox);
		    } catch (IOException ex)
		    {
			Logger.getLogger(TestEditor.class.getName()).log(Level.SEVERE, null, ex);
		    }
		});
    }
    
    @FXML private Button addButton;

    @FXML private VBox questionVBox;
    @FXML private QuestionForm questionForm;
    @FXML private ScrollPane scrollPane;

    @FXML
    private void onAddButtonClick() throws IOException
    {
	this.questionForm.setVisible(true);
	this.questionForm.setManaged(true);
	this.scrollPane.setVvalue(0.1);
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
