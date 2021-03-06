/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class MainWindow extends HBox {
    
    public MainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        System.out.println("Whatever");
        
        loader.load();
        
        System.out.println("Something");
        
        try {
            QuestionForm questionForm = new QuestionForm();
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            WelcomePage welcomePage = new WelcomePage();
            welcomePage.setOnNewButtonClick((event) -> {
                try {
                    this.openTestEditor();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            welcomePage.setOnRepositoryButtonClick((event) -> {
                try {
                    this.openRepositoryEditor();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            
            HBox.setHgrow(welcomePage, Priority.ALWAYS);
            
            mainPane.getChildren().add(welcomePage);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    HBox mainPane;

    private void openTestEditor() throws IOException {
        TestEditor testEditor = new TestEditor();
        HBox.setHgrow(testEditor, Priority.ALWAYS);

        testEditor.setOnCloseButtonClick((event) -> {
            try {
                this.showWelcomePage();
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        mainPane.getChildren().clear();
        mainPane.getChildren().add(testEditor);
    }

    private void openRepositoryEditor() throws IOException {
        RepositoryEditor repositoryEditor = new RepositoryEditor();
        HBox.setHgrow(repositoryEditor, Priority.ALWAYS);

        repositoryEditor.setOnCloseButtonClick((event) -> {
            try {
                this.showWelcomePage();
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        mainPane.getChildren().clear();
        mainPane.getChildren().add(repositoryEditor);
    }

    private void showWelcomePage() throws IOException {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.setOnNewButtonClick((event) -> {
            try {
                this.openTestEditor();
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        welcomePage.setOnRepositoryButtonClick((event) -> {
            try {
                this.openRepositoryEditor();
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        mainPane.getChildren().clear();
        mainPane.getChildren().add(welcomePage);
    }
}
