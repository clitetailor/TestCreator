/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 *
 * @author ducnh
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AuthenticateDialog auth = new AuthenticateDialog();
        HBox.setHgrow(auth, Priority.ALWAYS);
        
        stage.setTitle("Test Creator");
        stage.getIcons().add(new Image("gui/assets/icon.png"));
        stage.setScene(new Scene(auth));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
