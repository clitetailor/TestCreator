/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class TestEditorController implements Initializable
{
//    public TestEditorController() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TestEditor.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//
//        fxmlLoader.load();
//    }
    
    @FXML
    Button addButton;

    @FXML
    VBox questionList;

    @FXML
    void onAddButtonClick()
    {
	GridPane questionForm = new GridPane();

	questionForm.setAlignment(Pos.CENTER);
	questionForm.setMaxWidth(450);

	Label questionLabel = new Label("Question");
	TextArea questionInput = new TextArea();
	questionInput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	questionInput.setPrefHeight(50);
	questionInput.setMinHeight(Control.USE_PREF_SIZE);
	
	Label answerLabel = new Label("Answers");
	TextArea answerInput = new TextArea();
	answerInput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	answerInput.setPrefHeight(80);
	answerInput.setMinHeight(Control.USE_PREF_SIZE);

	Label descriptionLabel = new Label("Description");
	TextArea descriptionInput = new TextArea();
	descriptionInput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	descriptionInput.setPrefHeight(50);
	descriptionInput.setMinHeight(Control.USE_PREF_SIZE);

	questionForm.add(questionLabel, 0, 0);
	questionForm.add(questionInput, 0, 1);
	questionForm.add(answerLabel, 0, 2);
	questionForm.add(answerInput, 0, 3);
	questionForm.add(descriptionLabel, 0, 4);
	questionForm.add(descriptionInput, 0, 5);
	
	questionForm.getStyleClass().add("questionForm");
	
	questionList.getChildren().add(questionForm);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
	// TODO
    }
}
