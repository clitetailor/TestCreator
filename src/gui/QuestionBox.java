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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import objs.ChoiceAnswer;
import objs.ChoiceQuestion;
import objs.EssayQuestion;
import objs.Question;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class QuestionBox extends VBox
{
    
    public QuestionBox(Question question) throws IOException
    {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionBox.fxml"));
	
	loader.setRoot(this);
	loader.setController(this);	
	
	this.propertyOnDeleteButtonClick.set((ActionEvent event) -> {});
	this.propertyOnEditButtonClick.set((ActionEvent event) -> {});
	loader.load();
	
	this.setQuestion(question);
    }
    
    
    private Question question;
    
    @FXML private Label questionContentLabel;
    
    @FXML private AnchorPane multichoicePane;
    @FXML private AnchorPane essayPane;
    
    @FXML private VBox answerVBox;
    
    @FXML private Label answerContentLabel;
    @FXML private Label descriptionContentLabel;

    
    
    private final ObjectProperty<EventHandler<ActionEvent>> propertyOnDeleteButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();
    private final ObjectProperty<EventHandler<ActionEvent>> propertyOnEditButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();

    QuestionBox()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void setQuestion(Question question)
    {
	this.question = question;
	
	questionContentLabel.setText(question.content);
	
	if (question instanceof EssayQuestion)
	{
	    EssayQuestion essayQuestion = (EssayQuestion) question;
	    
	    this.essayPane.setVisible(true);
	    this.essayPane.setManaged(true);
	    this.multichoicePane.setVisible(false);
	    this.multichoicePane.setManaged(false);
	    	    
	    this.answerContentLabel.setText(essayQuestion.answer);
	    this.descriptionContentLabel.setText(essayQuestion.description);
	}
	
	if (question instanceof ChoiceQuestion)
	{
	    ChoiceQuestion choiceQuestion = (ChoiceQuestion) question;
	    
	    this.answerVBox.getChildren().clear();
	    
	    this.essayPane.setVisible(false);
	    this.essayPane.setManaged(false);
	    this.multichoicePane.setVisible(true);
	    this.multichoicePane.setManaged(true);
	    
	    for (ChoiceAnswer answer: choiceQuestion.answers)
	    {
		AnchorPane anchorPane = new AnchorPane();
		Label answerLabel = new Label(answer.content);
		
		System.out.println(answer.content);
		
		AnchorPane.setLeftAnchor(answerLabel, 0.0);
		AnchorPane.setRightAnchor(answerLabel, 0.0);
		AnchorPane.setTopAnchor(answerLabel, 0.0);
		
		answerLabel.getStyleClass().add("content");
		
		anchorPane.getChildren().add(answerLabel);
		
		this.answerVBox.getChildren().add(anchorPane);
	    }
	}
    }

    public void setOnDeleteButtonClick(EventHandler<ActionEvent> handler)
    {
	propertyOnDeleteButtonClick.set(handler);
    }

    public void setOnEditButtonClick(EventHandler<ActionEvent> handler)
    {
	propertyOnEditButtonClick.set(handler);
    }
    
    
    @FXML
    private void onDeleteButtonClick(ActionEvent event)
    {
	propertyOnDeleteButtonClick.get().handle(event);
    }
    
    @FXML
    private void onEditButtonClick(ActionEvent event)
    {
	propertyOnEditButtonClick.get().handle(event);
    }
}
