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

    public ArrayList<Question> readFile(String path) throws FileNotFoundException, IOException, ParseException {
        // tao ra mot JSONParser
        JSONParser jsonParser = new JSONParser();

        // tao mot doi tuong FileReader
        FileReader file = new FileReader(path);
        
        ArrayList<Question> list = new ArrayList<>();
        try {
            // parser file ve mot JSONArray
            JSONArray jsonArray = (JSONArray) jsonParser.parse(file);
            for (int i = 0; i < jsonArray.size(); i++) {
                // ep kieu ve JSONObject
                JSONObject questionObject = (JSONObject) jsonArray.get(i);
                String type = (String) questionObject.get("type");
                if (type.equals("EssayQuestion")) {
                    EssayQuestion eq = new EssayQuestion();
                    eq.setSubject((String) questionObject.get("subject"));
                    eq.setId(Integer.parseInt((String) questionObject.get("id")));
                    eq.setLevel(Integer.parseInt((String) questionObject.get("level")));
                    eq.setContent((String) questionObject.get("content"));
                    eq.setAnswer((String) questionObject.get("answer"));
                    eq.setDescription((String) questionObject.get("description"));
                    list.add(eq);
                } else if (type.equals("ChoiceQuestion")) {
                    ChoiceQuestion cq = new ChoiceQuestion();
                    cq.setSubject((String) questionObject.get("subject"));
                    cq.setId(Integer.parseInt((String) questionObject.get("id")));
                    cq.setLevel(Integer.parseInt((String) questionObject.get("level")));
                    cq.setContent((String) questionObject.get("content"));

                    // tao mot mang JSONArray de chua cac cau tra loi
                    JSONArray answerArray = (JSONArray) questionObject.get("answers");
                    JSONObject answerObject = new JSONObject();
                    
                    // su dung iterator de truy xuat den cac phan tu cua mang answerArray
                    Iterator<String> iterator = answerArray.iterator();
                    while (iterator.hasNext()) { // neu trong mang co nhieu phan tu
                        ChoiceAnswer ca = new ChoiceAnswer();
                        ca.setId(Integer.parseInt((String) answerObject.get("id")));
                        ca.setIsTrue((boolean) answerObject.get("isTrue"));
                        ca.setContent((String) answerObject.get("content"));
                        answerArray.add(ca);
                        iterator.next();
                    }
                    list.add(cq);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
//        ArrayList<Question> list = new ArrayList<>();
//        ChoiceQuestion choiceQuestion = new ChoiceQuestion();
//        choiceQuestion.setContent("Cai gi do");
//        ArrayList<ChoiceAnswer> answers = new ArrayList<ChoiceAnswer>();
//        answers.add(new ChoiceAnswer(1, true, "Con meo"));
//        answers.add(new ChoiceAnswer(2, false, "Con chuot"));
//        choiceQuestion.setAnswers(answers);
//
//        JSONObject questionObject = (new FileSaver()).encodeChoiceQuestion(choiceQuestion);
//        System.out.println(questionObject);
//        StringWriter out = new StringWriter();
//        questionObject.writeJSONString(out);
//
//        System.out.print(out.toString());
//        FileSaver fs = new FileSaver();
////        fs.saveQuestion("C:/Users/admin/Desktop/test2.txt", list);      
//        for(Question question : fs.readFile("C:/Users/admin/Desktop/test2.txt")) {
//            System.out.println(question.getId());
//            System.out.println(question.getSubject());
//            System.out.println(question.getLevel());
//        }
    }
}
