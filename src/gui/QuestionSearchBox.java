/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class QuestionSearchBox extends VBox {
    public QuestionSearchBox() {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionSearchBox.fxml"));
	loader.setRoot(this);
	loader.setController(this);
    }
}
