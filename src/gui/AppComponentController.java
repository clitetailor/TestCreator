/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.scene.AccessibleAttribute.MAX_VALUE;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class AppComponentController implements Initializable
{
    @FXML
    Button addButton;
    
    @FXML
    VBox questionList;
    
    @FXML
    void onAddButtonClick()
    {
	GridPane questionForm = new GridPane();
	
	questionForm.setAlignment(Pos.CENTER);
	questionForm.setVgap(8);
	
	Label questionLabel = new Label("Question");
	TextArea questionInput = new TextArea();
	
	Label answerLabel = new Label("Answers");
	TextArea answerInput = new TextArea();
	
	Label descriptionLabel = new Label("Description");
	TextArea descriptionInput = new TextArea();
	
	questionForm.add(questionLabel, 0, 0);
	questionForm.add(questionInput, 0, 1);
	questionForm.add(answerLabel, 0, 2);
	questionForm.add(answerInput, 0, 3);
	questionForm.add(descriptionLabel, 0, 4);
	questionForm.add(descriptionInput, 0, 5);
	questionList.getChildren().add(questionForm);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
	// TODO
    }    
    
}
