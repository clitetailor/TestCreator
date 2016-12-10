/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class TestGeneratorDialog extends VBox {

    public TestGeneratorDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestGeneratorDialog.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    private EventHandler<ActionEvent> addEventHandler = (ActionEvent event) -> {
    };
    private EventHandler<ActionEvent> cancelEventHandler = (ActionEvent event) -> {
    };

    public void setOnAddButtonClick(EventHandler<ActionEvent> handler) {
        this.addEventHandler = handler;
    }

    @FXML
    private void onAddButtonClick(ActionEvent event) {
        this.addEventHandler.handle(event);
    }

    public void setOnCancelButtonClick(EventHandler<ActionEvent> handler) {
        this.cancelEventHandler = handler;
    }

    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        this.cancelEventHandler.handle(event);
    }
    
    @FXML
    private TextField subjectTextField;
    
    @FXML
    private TextField essayLevel1;
    @FXML
    private TextField essayLevel2;
    @FXML
    private TextField essayLevel3;
    @FXML
    private TextField essayLevel4;
    @FXML
    private TextField essayLevel5;
    
    @FXML
    private TextField choiceLevel1;
    @FXML
    private TextField choiceLevel2;
    @FXML
    private TextField choiceLevel3;
    @FXML
    private TextField choiceLevel4;
    @FXML
    private TextField choiceLevel5;
    
    public ArrayList<Integer> getEssayNumberList() {
        ArrayList<Integer> essayNumberList = new ArrayList<>();
        essayNumberList.add(new Integer(this.essayLevel1.getText()));
        essayNumberList.add(new Integer(this.essayLevel2.getText()));
        essayNumberList.add(new Integer(this.essayLevel3.getText()));
        essayNumberList.add(new Integer(this.essayLevel4.getText()));
        essayNumberList.add(new Integer(this.essayLevel5.getText()));
        return essayNumberList;
    }
    
    public ArrayList<Integer> getChoiceNumberList() {
        ArrayList<Integer> choiceNumberList = new ArrayList<>();
        choiceNumberList.add(new Integer(this.choiceLevel1.getText()));
        choiceNumberList.add(new Integer(this.choiceLevel2.getText()));
        choiceNumberList.add(new Integer(this.choiceLevel3.getText()));
        choiceNumberList.add(new Integer(this.choiceLevel4.getText()));
        choiceNumberList.add(new Integer(this.choiceLevel5.getText()));
        return choiceNumberList;
    }
    
    public String getSubject() {
        return this.subjectTextField.getText();
    }
}
