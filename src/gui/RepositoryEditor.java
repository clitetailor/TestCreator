/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class RepositoryEditor extends HBox
{
    
    public RepositoryEditor() throws IOException
    {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("RepositoryEditor.fxml"));
	
	loader.setRoot(this);
	loader.setController(this);
	loader.load();
    }
    
}
