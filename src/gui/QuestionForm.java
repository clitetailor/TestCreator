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
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
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
public class QuestionForm extends VBox
{
    private Question question;
    
    @FXML
    VBox answers;
    
    @FXML
    VBox description;

    private ObjectProperty<EventHandler<ActionEvent>> propertyOnDeleteButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();
    private ObjectProperty<EventHandler<ActionEvent>> propertyOnEditButtonClick = new SimpleObjectProperty<EventHandler<ActionEvent>>();
    
    QuestionForm(Question question) throws IOException
    {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionForm.fxml"));
	loader.setRoot(this);
	loader.setController(this);

	this.propertyOnDeleteButtonClick.set((ActionEvent event) -> {});
	this.propertyOnEditButtonClick.set((ActionEvent event) -> {});
	loader.load();

	answers.managedProperty().bind(answers.visibleProperty());
	description.managedProperty().bind(description.visibleProperty());
	
	this.setQuestion(question);
	
    }

    public void setQuestion(Question question)
    {
	this.question = question;

	
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

	    ButtonBar buttonBar = new ButtonBar();

	    Button editButton = new Button("Edit");
	    editButton.getStyleClass().addAll("materialButton", "primary");
	    editButton.setOnAction((event) -> propertyOnEditButtonClick.get().handle((event)));

	    Button deleteButton = new Button("Delete");
	    deleteButton.getStyleClass().addAll("materialButton", "intense");
	    deleteButton.setOnAction((event) -> propertyOnDeleteButtonClick.get().handle(event));

	    buttonBar.getButtons().addAll(editButton, deleteButton);

	    this.getChildren().addAll(buttonBar);
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

	    ButtonBar buttonBar = new ButtonBar();

	    Button editButton = new Button("Edit");
	    editButton.getStyleClass().addAll("materialButton", "primary");
	    editButton.setOnAction((event) -> propertyOnEditButtonClick.get().handle((event)));

	    Button deleteButton = new Button("Delete");
	    deleteButton.getStyleClass().addAll("materialButton", "intense");
	    deleteButton.setOnAction((event) -> propertyOnDeleteButtonClick.get().handle(event));

	    buttonBar.getButtons().addAll(editButton, deleteButton);

	    this.getChildren().addAll(buttonBar);
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
}
