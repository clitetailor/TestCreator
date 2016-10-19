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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
public class TestEditor extends HBox
{
    public class TestQuestionForm extends VBox
    {
	private Question question;
	
	TestQuestionForm()
	{
	    super();
	    this.setMaxWidth(450);
	    this.getStyleClass().add("questionForm");
	}
	
	public void setQuestion(Question question)
	{
	    this.question = question;
	    
	    System.out.println(question instanceof ChoiceQuestion);
	    System.out.println(question instanceof EssayQuestion);
	    
	    if (question instanceof ChoiceQuestion)
	    {
		ChoiceQuestion choiceQuestion = (ChoiceQuestion) question;
		
		this.getChildren().clear();
		
		Label questionTitle = new Label("Question");
		questionTitle.getStyleClass().add("title");
		
		Label questionContent = new Label(choiceQuestion.content);
		questionContent.getStyleClass().add("content");
		
		this.getChildren().addAll(questionTitle, questionContent);
		
		Label answerTitle = new Label("Answers");
		answerTitle.getStyleClass().add("title");
		
		this.getChildren().add(answerTitle);
		
		for (ChoiceAnswer answer: choiceQuestion.answers)
		{
		    Label answerContent = new Label(answer.content);
		    answerContent.getStyleClass().add("content");
		    
		    this.getChildren().add(answerContent);
		}
	    }
	    
	    if (question instanceof EssayQuestion)
	    {
		EssayQuestion essayQuestion = (EssayQuestion) question;
		
		this.getChildren().clear();
		
		Label questionTitle = new Label("Question");
		questionTitle.getStyleClass().add("title");
		
		Label questionContent = new Label(essayQuestion.content);
		questionContent.getStyleClass().add("content");
		
		Label descriptionTitle = new Label("Description");
		descriptionTitle.getStyleClass().add("title");
		
		Label descriptionContent = new Label(essayQuestion.description);
		descriptionContent.getStyleClass().add("content");
		
		Label answerTitle = new Label("Answer");
		answerTitle.getStyleClass().add("title");
		
		Label answerContent = new Label(essayQuestion.answer);
		answerContent.getStyleClass().add("content");
		
		this.getChildren().addAll(questionTitle, questionContent,
			descriptionTitle, descriptionContent,
			answerTitle, answerContent);
	    }
	}
    }
    
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
    private VBox questionList;

    @FXML
    private void onAddButtonClick()
    {
	TestQuestionForm testQuestionForm = new TestQuestionForm();
	testQuestionForm.setQuestion(new EssayQuestion("What is this?", "This is a cat!", "None"));
	questionList.getChildren().add(testQuestionForm);
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
