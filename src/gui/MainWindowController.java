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
import javafx.scene.layout.Pane;

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
	mainPane.getChildren().clear();
	mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("TestEditor.fxml")));
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
	// TODO
    }
}
