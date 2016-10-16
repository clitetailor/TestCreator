/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class MainWindowController implements Initializable
{
    @FXML
    Pane mainPane;
    
    @FXML
    void onNewButtonClick() throws IOException
    {
	TestEditor testEditor = new TestEditor();
	HBox.setHgrow(testEditor, Priority.ALWAYS);
	
	mainPane.getChildren().clear();
	mainPane.getChildren().add(testEditor);
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
	try
	{
	    mainPane.getChildren().add(new WelcomePage());
	} catch (IOException ex)
	{
	    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
