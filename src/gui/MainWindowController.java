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
    
    private void createNewTest() throws IOException
    {
	TestEditor testEditor = new TestEditor();
	HBox.setHgrow(testEditor, Priority.ALWAYS);
	
	testEditor.setOnCloseButtonClick((event) ->
	{
	    try
	    {
		this.returnToWelcomePage();
	    } catch (IOException ex)
	    {
		Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
	    }
	});
	
	mainPane.getChildren().clear();
	mainPane.getChildren().add(testEditor);
    }
    
    private void returnToWelcomePage() throws IOException
    {
	WelcomePage welcomePage = new WelcomePage();
	welcomePage.setOnNewButtonClick((event) ->
	{
	    try
	    {
		this.createNewTest();
	    } catch (IOException ex)
	    {
		Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
	    }
	});
	
	mainPane.getChildren().clear();
	mainPane.getChildren().add(welcomePage);
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
	    WelcomePage welcomePage = new WelcomePage();
	    welcomePage.setOnNewButtonClick((event) -> {
		try
		{
		    this.createNewTest();
		} catch (IOException ex)
		{
		    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
		}
	    });
	    
	    mainPane.getChildren().add(welcomePage);
	} catch (IOException ex)
	{
	    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
