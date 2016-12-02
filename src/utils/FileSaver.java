/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import objs.ChoiceAnswer;
import objs.ChoiceQuestion;
import objs.EssayQuestion;
import objs.Question;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author admin
 */
@SuppressWarnings("unchecked")
public class FileSaver {
    
    public void saveQuestion(String path, ArrayList<Question> list) throws FileNotFoundException, IOException {
        // tao mot JSONArray
        JSONArray questionArray = new JSONArray();
        for (Question question : list) {
            JSONObject questionObject; // khai bao doi tuong JSONObject
            if (question instanceof EssayQuestion) { // neu la cau hoi tu luan
                questionObject = this.encodeEssayQuestion((EssayQuestion) question);
                questionArray.add(questionObject); // them vao JSONArray
            } else if (question instanceof ChoiceQuestion) { // neu la cau hoi trac nghiem
                questionObject = this.encodeChoiceQuestion((ChoiceQuestion) question);
                questionArray.add(questionObject); // them vao JSONArray
            } else {

            }
        }

        JSONObject testObject = new JSONObject();
        testObject.put("data", questionArray);

        FileWriter file = new FileWriter(path);
        try {
            file.write(testObject.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + testObject);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.flush();
            file.close();
        }
    }

    private JSONObject encodeEssayQuestion(EssayQuestion question) {
        // tao ra mot JSONObject
        JSONObject questionObject = new JSONObject();
        
        // put cac truong cua doi tuong vao JSONObject
        questionObject.put("type", "EssayQuestion");
        questionObject.put("subject", question.getSubject());
        questionObject.put("id", question.getId());
        questionObject.put("level", question.getLevel());
        questionObject.put("content", question.getContent());
        questionObject.put("answer", question.getAnswer());
        questionObject.put("description", question.getDescription());

        return questionObject;
    }

    private JSONObject encodeChoiceQuestion(ChoiceQuestion question) {
        JSONObject questionObject = new JSONObject();
        questionObject.put("type", "ChoiceQuestion");
        questionObject.put("subject", question.getSubject());
        questionObject.put("id", question.getId());
        questionObject.put("level", question.getLevel());
        questionObject.put("content", question.getContent());
        
        // tao mot JSONAray chua dap an
        JSONArray answerArray = new JSONArray();
        for (ChoiceAnswer answer : question.getAnswers()) { // duyet tu dau den cuoi cua ArrayList
            JSONObject answerObject = new JSONObject();
            
            // put dap an vao
            answerObject.put("content", answer.getContent());
            answerArray.add(answerObject); // them vao JSONArray
        }
        
        // put cac dap an vao JSONArray
        questionObject.put("answers", answerArray);
        return questionObject;
    }

    public void readFile(String path) throws FileNotFoundException, IOException, ParseException {
        // tao ra mot JSONParser
        JSONParser jsonParser = new JSONParser();

        // tao mot doi tuong FileReader
        FileReader file = new FileReader(path);
        try {
            // parser file ve mot JSONArray
            JSONArray jsonArray = (JSONArray) jsonParser.parse(file);
            for (int i = 0; i < jsonArray.size(); i++) {
                // ep kieu doi tuong vua tao thanh mot doi tuong JSONObject
                JSONObject questionObject = (JSONObject) jsonArray.get(i);
                String type = (String) questionObject.get("type");
                if (type.equals("EssayQuestion")) {                  
                    String subject = (String) questionObject.get("subject");
                    int id = Integer.parseInt((String) questionObject.get("id"));
                    int level = Integer.parseInt((String) questionObject.get("level"));
                    String content = (String) questionObject.get("content");
                    String answer = (String) questionObject.get("answer");
                    String description = (String) questionObject.get("description");

                    System.out.println("type: " + type);
                    System.out.println("subject: " + subject);
                    System.out.println("id: " + id);
                    System.out.println("level: " + level);
                    System.out.println("content: " + content);
                    System.out.println("answer: " + answer);
                    System.out.println("description: " + description);
                    System.out.println();
                } else if (type.equals("ChoiceQuestion")) {
                    String subject = (String) questionObject.get("subject");
                    int id = Integer.parseInt((String) questionObject.get("id"));
                    int level = Integer.parseInt((String) questionObject.get("level"));
                    String content = (String) questionObject.get("content");

                    // tao mot mang JSONArray de chua cac cau tra loi
                    JSONArray answerArray = (JSONArray) questionObject.get("answers");
                    System.out.println("type: " + type);
                    System.out.println("subject: " + subject);
                    System.out.println("id: " + id);
                    System.out.println("level: " + level);
                    System.out.println("content: " + content);
                    System.out.println("answers: ");

                    // su dung iterator de truy xuat den cac phan tu cua mang answerArray
                    Iterator<String> iterator = answerArray.iterator();
                    while (iterator.hasNext()) { // neu trong mang co nhieu phan tu
                        System.out.println(iterator.next()); // in ra cac phan tu
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        ArrayList<Question> list = new ArrayList<>();
        ChoiceQuestion choiceQuestion = new ChoiceQuestion();
        choiceQuestion.setContent("Cai gi do");
        ArrayList<ChoiceAnswer> answers = new ArrayList<ChoiceAnswer>();
        answers.add(new ChoiceAnswer(1, true, "Con meo"));
        answers.add(new ChoiceAnswer(2, false, "Con chuot"));
        choiceQuestion.setAnswers(answers);

        JSONObject questionObject = (new FileSaver()).encodeChoiceQuestion(choiceQuestion);
        System.out.println(questionObject);
        StringWriter out = new StringWriter();
        questionObject.writeJSONString(out);

        System.out.print(out.toString());
        FileSaver fs = new FileSaver();
        fs.saveQuestion("C:/Users/admin/Desktop/test2.txt", list);
//        fs.readFile("C:/Users/admin/Desktop/test2.txt");
    }
}
