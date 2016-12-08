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
import java.util.Collections;
import objs.ChoiceAnswer;
import objs.ChoiceQuestion;
import objs.EssayQuestion;
import objs.Question;

/**
 *
 * @author hoangkien
 */
public class Database {

    private static Connection conn = null;
    /**
     * Khởi tạo kết nối database
     */
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

    /**
     * Lấy toàn bộ câu hỏi tự luận theo môn học
     * 
     * @param subject Tên môn học
     * @return ArrayList chứa các câu hỏi tự luận
     */
    public static ArrayList<EssayQuestion> getEssayQuestionsBySubject(String subject) {
        ArrayList<EssayQuestion> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM essayquestion WHERE subject LIKE '" + subject + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                EssayQuestion eq = new EssayQuestion();
                eq.setId(rs.getInt("essayQuestionId"));
                eq.setAnswer(rs.getString("answer"));
                eq.setContent(rs.getString("content"));
                eq.setDescription(rs.getString("description"));
                eq.setLevel(rs.getInt("level"));
                eq.setSubject(rs.getString("subject"));
                list.add(eq);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return list;
    }
    
    /**
     * Lấy toàn bộ câu hỏi tự luận theo môn học và độ khó
     * 
     * @param subject Tên môn học
     * @param level Mức độ khó
     * @return ArrayList chứa các câu hỏi tự luận
     */
    public static ArrayList<EssayQuestion> getEssayQuestionsBySubject(String subject, int level) {
        ArrayList<EssayQuestion> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM essayquestion WHERE subject LIKE '" + 
                    subject + "' and level = " + level;
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                EssayQuestion eq = new EssayQuestion();
                eq.setId(rs.getInt("essayQuestionId"));
                eq.setAnswer(rs.getString("answer"));
                eq.setContent(rs.getString("content"));
                eq.setDescription(rs.getString("description"));
                eq.setLevel(level);
                eq.setSubject(rs.getString("subject"));
                list.add(eq);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return list;
    }

    /**
     * Lấy toàn bộ đáp án của một câu hỏi trắc nghiệm, hàm này được
     * tích hợp sẵn trong getChoiceQuestionsBySubject()
     * 
     * @param question
     * @return ArrayList chứa các đáp án của câu hỏi
     */
    private static ArrayList<ChoiceAnswer> getChoiceAnswersByChoiceQuestion(ChoiceQuestion question) {
        ArrayList<ChoiceAnswer> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM choiceanswer WHERE choiceQuestionId = ?");
            stmt.setInt(1, question.getId());
            ResultSet rs = stmt.executeQuery();
            ChoiceAnswer choice;
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

    /**
     * Lấy toàn bộ câu hỏi trắc nghiệm theo môn học
     * Lưu ý: phương thức này đã chứa phương thức getChoiceAnswersByChoiceQuestion
     * lấy mảng chứa các đáp án nên không cần phải gọi thêm
     * 
     * @param subject
     * @return ArrayList chứa các câu hỏi Trắc nghiệm
     */
    
    public static ArrayList<ChoiceQuestion> getChoiceQuestionsBySubject(String subject) {
        ArrayList<ChoiceQuestion> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM choicequestion WHERE subject LIKE ?");
            stmt.setString(1, subject);
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            ChoiceQuestion cq;
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

    /**
     * Lấy toàn bộ câu hỏi trắc nghiệm theo môn học và độ khó
     * Lưu ý: phương thức này đã chứa phương thức getChoiceAnswersByChoiceQuestion
     * lấy mảng chứa các đáp án nên không cần phải gọi thêm
     * 
     * @param subject Tên môn học
     * @param level độ khó
     * @return ArrayList chứa các câu hỏi Trắc nghiệm
     */
    
    public static ArrayList<ChoiceQuestion> getChoiceQuestionsBySubject(String subject, int level) {
        ArrayList<ChoiceQuestion> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM choicequestion WHERE subject LIKE ? and level = " + level);
            stmt.setString(1, subject);
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            ChoiceQuestion cq;
            while (rs.next()) {
                cq = new ChoiceQuestion();
                cq.setId(rs.getInt("choiceQuestionId"));
                cq.setContent(rs.getString("content"));
                cq.setLevel(level);
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
    
    /**
     * Lấy toàn bộ danh sách môn học
     * 
     * @return ArrayList của String chưa các môn học
     */
    public static ArrayList<String> getAllSubject() {
        ArrayList<String> list = new ArrayList<>();
        try {
            String query = "SELECT subject FROM essayquestion union select subject from choicequestion;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString("subject"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return list;
    }

    /**
     * Lưu câu hỏi tự luận, dùng saveQuestion
     * 
     * @param question
     * @return Giá trị true hoặc false tương ứng lưu thành công hay không
     */
    private static boolean saveEssayQuestion(EssayQuestion question) {
        try {
            PreparedStatement stmt;
            if (question.getId() < 1) {
                stmt = conn.prepareStatement("INSERT INTO essayquestion "
                        + "SET content = ?, subject = ?, level = ?, "
                        + "description = ?, answer = ?",
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, question.getContent());
                stmt.setString(2, question.getSubject());
                stmt.setInt(3, question.getLevel());
                stmt.setString(4, question.getDescription());
                stmt.setString(5, question.getAnswer());
                stmt.executeUpdate();
                ResultSet key = stmt.getGeneratedKeys();
                if (key.next()) {
                    question.setId(key.getInt(1));
                }
            } else {
                // Update
                stmt = conn.prepareStatement(
                        "UPDATE essayquestion SET content = ?, subject = ?, "
                        + "level = ?, description ?, answer = ? "
                        + "WHERE essayQuestionId = ?");
                stmt.setString(1, question.getContent());
                stmt.setString(2, question.getSubject());
                stmt.setInt(3, question.getLevel());
                stmt.setString(4, question.getDescription());
                stmt.setString(5, question.getAnswer());
                stmt.setInt(6, question.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return true;
    }

    /**
     * Lưu câu hỏi trắc nghiệm, Dùng saveQuestion
     * 
     * @param question
     * @return Giá trị true hoặc false tương ứng lưu thành công hay không
     */
    private static boolean saveChoiceQuestion(ChoiceQuestion question) {
        try {
            PreparedStatement stmt;
            if (question.getId() < 1) {
                // Insert new question
                stmt = conn.prepareStatement(
                        "INSERT INTO choicequestion SET "
                        + "content = ?, subject = ?, level = ?",
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, question.getContent());
                stmt.setString(2, question.getSubject());
                stmt.setInt(3, question.getLevel());
                stmt.executeUpdate();
                ResultSet key = stmt.getGeneratedKeys();
                if (key.next()) {
                    question.setId(key.getInt(1));
                }
                for (ChoiceAnswer answer : question.getAnswers()) {
                    stmt = conn.prepareStatement(
                            "INSERT INTO choiceanswer SET choiceQuestionId = ?,"
                            + "content = ?, trueFalse = ?",
                            Statement.RETURN_GENERATED_KEYS);
                    stmt.setInt(1, question.getId());
                    stmt.setString(2, answer.getContent());
                    stmt.setBoolean(3, answer.isIsTrue());
                    stmt.executeUpdate();
                    key = stmt.getGeneratedKeys();
                    if (key.next()) {
                        answer.setId(key.getInt(1));
                    }
                }
            } else {
                // Update
                stmt = conn.prepareStatement(
                        "UPDATE choicequestion SET content = ?, subject = ?, level = ? "
                        + "WHERE choiceQuestionId = ?");
                stmt.setString(1, question.getContent());
                stmt.setString(2, question.getSubject());
                stmt.setInt(3, question.getLevel());
                stmt.setInt(4, question.getId());
                stmt.executeUpdate();
                stmt = conn.prepareStatement(
                        "DELETE FROM choiceanswer WHERE choiceQuestionId = ?");
                stmt.setInt(1, question.getId());
                stmt.executeUpdate();
                // Save answer
                for (ChoiceAnswer answer : question.getAnswers()) {
                    stmt = conn.prepareStatement(
                            "INSERT INTO choiceanswer SET choiceQuestionId = ?,"
                            + "content = ?, trueFalse = ?",
                            Statement.RETURN_GENERATED_KEYS);
                    stmt.setInt(1, question.getId());
                    stmt.setString(2, answer.getContent());
                    stmt.setBoolean(3, answer.isIsTrue());
                    stmt.executeUpdate();
                    ResultSet key = stmt.getGeneratedKeys();
                    if (key.next()) {
                        answer.setId(key.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return true;
    }

    /**
     * Lưu câu hỏi trắc nghiệm hoặc tự luận, thay thế cho
     * saveChoiceQuestion, saveEssayQuestion
     * 
     * @param question
     * @return Boolean lưu thành công hay không
     */
    public static boolean saveQuestion(Question question) {
        if (question instanceof EssayQuestion) {
            return saveEssayQuestion((EssayQuestion) question);
        } else {
            return saveChoiceQuestion((ChoiceQuestion) question);
        }
    }

    /**
     * Lấy ngẫu nhiên một số câu hỏi tự luận của một môn ứng với một độ khó
     * 
     * @param subject Môn học
     * @param level Mức độ khó
     * @param number Số câu muốn lấy
     * @return ArrayList của các câu hỏi tự luận
     */
    public static ArrayList<EssayQuestion> getRandomEssayQuestion(String subject, int level, int number) {
        ArrayList<EssayQuestion> result = new ArrayList<>();
        try {
            ArrayList<Integer> idList = new ArrayList<>();

            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT essayQuestionId AS id FROM essayquestion "
                    + "WHERE level = ? and subject LIKE ?");
            stmt.setInt(1, level);
            stmt.setString(2, subject);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idList.add(rs.getInt("id"));
            }
            Collections.shuffle(idList);
            int count = number;
            int index = 0;
            EssayQuestion question;
            while (count > 0) {
                if (index == idList.size()) {
                    break;
                }
                stmt = conn.prepareStatement("SELECT * from essayquestion "
                        + "WHERE essayQuestionId = ?");
                stmt.setInt(1, idList.get(index));
                index++;
                rs = stmt.executeQuery();
                if (rs.next()) {
                    question = new EssayQuestion();
                    question.setId(rs.getInt("essayQuestionId"));
                    question.setAnswer(rs.getString("answer"));
                    question.setContent(rs.getString("content"));
                    question.setDescription(rs.getString("description"));
                    question.setLevel(level);
                    question.setSubject(subject);
                    result.add(question);
                    count--;
                }

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return result;
    }

    /**
     * Lấy ngẫu nhiên một số câu hỏi trắc nghiệm của một môn ứng với một độ khó
     * 
     * @param subject Môn học
     * @param level Mức độ khó
     * @param number Số câu muốn lấy
     * @return ArrayList của các câu hỏi trắc nghiệm
     */
    public static ArrayList<ChoiceQuestion> getRandomChoiceQuestion(String subject, int level, int number) {
        ArrayList<ChoiceQuestion> result = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT choiceQuestionId AS id FROM choicequestion "
                    + "WHERE level = ? and subject LIKE ?");
            stmt.setInt(1, level);
            stmt.setString(2, subject);
            ArrayList<Integer> idList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idList.add(rs.getInt("id"));
            }
            Collections.shuffle(idList);
            int count = number;
            int index = 0;
            ChoiceQuestion question;
            while (count > 0) {
                if (index == idList.size()) {
                    break;
                }
                stmt = conn.prepareStatement("SELECT * from choicequestion "
                        + "WHERE choiceQuestionId = ?");
                stmt.setInt(1, idList.get(index));
                index++;
                rs = stmt.executeQuery();
                if (rs.next()) {
                    // Add question here
                    question = new ChoiceQuestion();
                    question.setId(rs.getInt("choiceQuestionId"));
                    question.setContent(rs.getString("content"));
                    question.setLevel(level);
                    question.setSubject(subject);
                    ArrayList<ChoiceAnswer> answerList = new ArrayList<>();
                    // Anwser here
                    stmt = conn.prepareStatement(
                            "SELECT * FROM choiceanswer WHERE choiceQuestionId = ?");
                    stmt.setInt(1, question.getId());
                    rs = stmt.executeQuery();
                    ChoiceAnswer answer;
                    while (rs.next()) {
                        answer = new ChoiceAnswer();
                        answer.setContent(rs.getString("content"));
                        answer.setId(rs.getInt("choiceAnswerId"));
                        answer.setIsTrue(rs.getBoolean("trueFalse"));
                        answerList.add(answer);
                    }
                    question.setAnswers(answerList);
                    result.add(question);
                    count--;
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return result;
    }
    
    /**
     * Xóa câu hỏi
     * 
     * @param question câu hỏi cần xóa
     * @return giá trị boolean thực hiện thành công hay không
     */
    public static boolean deleteQuestion(Question question) {
        String qry = "";
        if (question instanceof EssayQuestion) {
            qry = "DELETE from essayquestion where essayQuestionId = ?";
        }
        else {
            qry = "DELETE from choicequestion WHERE choiceQuestionId = ?";
        }
        try {
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setInt(1, question.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        initialize();
        EssayQuestion q = new EssayQuestion();
        q.setId(1);
        System.out.println(deleteQuestion(q));
    }
}
