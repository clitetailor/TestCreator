/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.Database;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import objs.Question;

/**
 * FXML Controller class
 *
 * @author ducnh
 */
public class RepositoryEditor extends HBox {

    public RepositoryEditor() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RepositoryEditor.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        this.questionForm.setManaged(false);
        
        this.questionForm.setOnDoneButtonClick((event) -> {
            Database.saveQuestion(this.questionForm.getQuestion());
            
            this.questionForm.resetContent();
            this.updateContent();
        });
        
        Database.initialize();
        this.questionSearchBox.setSubjects(Database.getAllSubjects());
        
        this.questionSearchBox.setOnSearchButtonClick((event) -> {
            this.updateContent();
        });
        
        this.questionSearchBox.setOnClearButtonClick((event) -> {
            this.questionVBox.getChildren().clear();
        });
        
        
        this.questionEditForm.removeTopPart();
        this.questionEditForm.setManaged(false);
    }
    
    private void updateContent() {
        String subject = this.questionSearchBox.getSubject();
        Integer level = this.questionSearchBox.getLevel();
        String type = this.questionSearchBox.getQuestionType();

        if (subject == null || type == null || subject.equals("")) {
            return;
        }

        ArrayList<Question> questions = new ArrayList<>();

        if ("EssayQuestion".equals(type)) {
            if (level == null) {
                questions.addAll(Database.getEssayQuestionsBySubject(subject));
            } else {
                questions.addAll(Database.getEssayQuestionsBySubject(subject, level));
            }
        } else if ("ChoiceQuestion".equals(type)) {
            if (level == null) {
                questions.addAll(Database.getChoiceQuestionsBySubject(subject));
            } else {
                questions.addAll(Database.getChoiceQuestionsBySubject(subject, level));
            }
        } else {
            return;
        }
        
        this.questionSearchBox.setSubjects(Database.getAllSubjects());
        this.questionSearchBox.setSubject(subject);
        
        try {
            this.setQuestions(questions);
        } catch (IOException ex) {
            Logger.getLogger(RepositoryEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void setQuestions(ArrayList<Question> questions) throws IOException {
        this.questionVBox.getChildren().clear();
        
        for (Question question: questions) {
            QuestionBox questionBox = new QuestionBox(question);
            questionBox.setOnEditButtonClick((event) -> {
                try {
                    this.questionEditForm.setQuestion(question);
                } catch (IOException ex) {
                    Logger.getLogger(RepositoryEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.questionEditForm.setVisible(true);
                this.questionEditForm.setManaged(true);
                this.scrollPane.setVvalue(0.0);
                
                
                this.questionEditForm.setOnDoneButtonClick((subsubevent) -> {
                    Database.saveQuestion(question);
                });

                this.questionEditForm.setOnCancelButtonClick((subsubevent) -> {
                    this.questionEditForm.setVisible(false);
                    this.questionEditForm.setManaged(false);
                });
            });
            
            questionBox.setOnDeleteButtonClick((event) -> {
                Database.deleteQuestion(question);
            });
            
            this.questionVBox.getChildren().add(questionBox);
        }
    }
    
    @FXML
    private QuestionSearchBox questionSearchBox;
    @FXML
    private QuestionForm questionForm;
    @FXML
    private QuestionForm questionEditForm;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox questionVBox;

    @FXML
    private void onAddButtonClick(ActionEvent event) {
        this.questionForm.setVisible(true);
        this.questionForm.setManaged(true);

        this.scrollPane.setVvalue(0.0);
    }
    
    private void showQuestions(ArrayList<Question> questions) throws IOException {
        this.questionVBox.getChildren().clear();
        for (Question question: questions) {
            QuestionBox questionBox = new QuestionBox(question);
            this.questionVBox.getChildren().add(questionBox);
        }
    }

    public EventHandler<ActionEvent> closeEventHandler = (ActionEvent event) -> {
    };

    public void setOnCloseButtonClick(EventHandler<ActionEvent> handler) {
        this.closeEventHandler = handler;
    }

    @FXML
    private void onCloseButtonClick(ActionEvent event) {
        this.closeEventHandler.handle(event);
    }
}
