/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.Database;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class AuthenticateDialog extends HBox {

    public AuthenticateDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AuthenticateDialog.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }
    
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    
    @FXML
    private Label warning;
    
    @FXML
    private void onDoneButtonClick() throws IOException {
        this.authenticate();
    }
    
    @FXML
    private void onSkipButtonClick() throws IOException {
        this.skip();
    }
    
    public void authenticate() throws IOException {
        boolean successful = false;
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (username != null && password != null) {
            successful = Database.initialize(username, password);
        }

        
        if (successful) {
            this.startProgram();
        } else {
            this.warning.setVisible(true);
        }
    }
    
    public void skip() throws IOException {
        this.startProgram();
    }
    
    private void startProgram() throws IOException {
        MainWindow mainWindow = new MainWindow();
        HBox.setHgrow(mainWindow, Priority.ALWAYS);

        Stage stage = new Stage();

        stage.setTitle("Test Creator");
        stage.getIcons().add(new Image("gui/assets/icon.png"));
        stage.setScene(new Scene(mainWindow));
        stage.show();
        
        Stage originStage = (Stage) this.getScene().getWindow();
        originStage.close();
    }
}
