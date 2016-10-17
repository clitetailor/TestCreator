/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import objs.ChoiceAnswer;
import objs.ChoiceQuestion;
import objs.EssayQuestion;

/**
 *
 * @author hoangkien
 */
public class Database {

    private static Connection conn = null;

    public static void initialize() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/oop?"
                        + "user=root&password=1234&useSSL=false");
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            //System.out.println("OK");
        }
    }

    public static ArrayList<EssayQuestion> getEssayQuestionsBySubject(String subject) {
        ArrayList<EssayQuestion> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT essayquestion.*, subject.subjectName " +
                           "FROM essayquestion, subject " +
                           "WHERE essayquestion.subjectId = subject.subjectId " + 
                           "AND subjectName LIKE '" + subject + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                EssayQuestion eq = new EssayQuestion();
                eq.setId(rs.getInt("essayQuestionId"));
                eq.setAnswer(rs.getString("answer"));
                eq.setContent(rs.getString("content"));
                eq.setDescription(rs.getString("description"));
                eq.setLevel(rs.getInt("level"));
                eq.setSubject(rs.getString("subjectName"));
                list.add(eq);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return list;
    }
    
    private static ArrayList<ChoiceAnswer> getChoiceAnswersByChoiceQuestion(ChoiceQuestion question) {
        ArrayList<ChoiceAnswer> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM choiceanswer WHERE choiceQuestionId = ?");
            stmt.setInt(1, question.getId());
            ResultSet rs = stmt.executeQuery();
            ChoiceAnswer choice = null;
            while (rs.next()) {
                choice = new ChoiceAnswer();
                choice.setId(rs.getInt("choiceAnswerId"));
                choice.setContent(rs.getString("content"));
                choice.setIsTrue(rs.getBoolean("trueFalse"));
                list.add(choice);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return list;
    }
    
    public static ArrayList<ChoiceQuestion> getChoiceQuestionsBySubject(String subject) {
        ArrayList<ChoiceQuestion> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT choicequestion.* FROM choicequestion, subject " +
                "WHERE choicequestion.subjectId = subject.subjectId " +
                "AND subject.subjectName LIKE ?");
            stmt.setString(1, subject);
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            ChoiceQuestion cq = null;
            while (rs.next()) {
                cq = new ChoiceQuestion();
                cq.setId(rs.getInt("choiceQuestionId"));
                cq.setContent(rs.getString("content"));
                cq.setLevel(rs.getInt("level"));
                cq.setSubject(subject);
                cq.setAnswers(getChoiceAnswersByChoiceQuestion(cq));
                list.add(cq);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return list;
    }
    
    public static ArrayList<String> getAllSubject() {
        ArrayList<String> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM subject";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString("subjectName"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return list;
    }
    
    public static boolean save(EssayQuestion question) {
        try {
            if (question.getId() < 1) {
                PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO essayquestion SET " +
                    "content = ?, subjectId = ?, ");
            }
            else {
                
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return true;
    }

    public static void main(String[] args) {
        Database.initialize();
        ArrayList<String> list = getAllSubject();
        for (String content : list) {
            System.out.println(content);
        }
        //ChoiceQuestion cq = new ChoiceQuestion();
    }
}
