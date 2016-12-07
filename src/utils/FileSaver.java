package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
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
                // doi tuong questionObject duoc gan bang doi tuong encodeEssayQuestion 
                questionObject = this.encodeEssayQuestion((EssayQuestion) question);
                questionArray.add(questionObject); // them vao JSONArray
            } else if (question instanceof ChoiceQuestion) { // neu la cau hoi trac nghiem
                questionObject = this.encodeChoiceQuestion((ChoiceQuestion) question);
                questionArray.add(questionObject);
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
        questionObject.put("id", "" + question.getId() + "");
        questionObject.put("level", "" + question.getLevel() + "");
        questionObject.put("content", question.getContent());
        questionObject.put("answer", question.getAnswer());          
        questionObject.put("description", question.getDescription());         
        return questionObject;
    }

    private JSONObject encodeChoiceQuestion(ChoiceQuestion question) {
        JSONObject questionObject = new JSONObject();
        questionObject.put("type", "ChoiceQuestion");
        questionObject.put("subject", question.getSubject());
        questionObject.put("id", "" + question.getId() + "");
        questionObject.put("level", "" + question.getLevel()+ "");
        questionObject.put("content", question.getContent());

        // tao mot JSONAray chua dap an
        JSONArray answerArray = new JSONArray();
        for (ChoiceAnswer answer : question.getAnswers()) { // duyet tu dau den cuoi cua ArrayList
            JSONObject answerObject = new JSONObject();

            // put dap an vao
            answerObject.put("id", "" + answer.getId() + "");
            answerObject.put("content", answer.getContent());
            answerObject.put("isTrue", "" + answer.isIsTrue() + "");
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
            JSONObject dataObject = (JSONObject) jsonParser.parse(file);
            JSONArray jsonArray = (JSONArray) dataObject.get("data");
            for (int i = 0; i < jsonArray.size(); i++) {
                // ep kieu ve JSONObject
                JSONObject questionObject = (JSONObject) jsonArray.get(i);
                String type = (String) questionObject.get("type");
                if (type.equals("EssayQuestion")) {
                    EssayQuestion eq = new EssayQuestion();
                    
                    // lay cac gia tri cua cau hoi de thiet lap cho doi tuong
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

                    ArrayList<ChoiceAnswer> answerList = new ArrayList<>();

                    for (int j = 0; j < answerArray.size(); ++j) {
                        // khai bao doi tuong JSONObject
                        JSONObject answerObject = (JSONObject) answerArray.get(j);
                        ChoiceAnswer ca = new ChoiceAnswer();
                        ca.setId(Integer.parseInt((String) answerObject.get("id")));
                        ca.setContent((String) answerObject.get("content"));
                        ca.setIsTrue(Boolean.parseBoolean((String) answerObject.get("isTrue")));
                        answerList.add(ca);
                    }

                    cq.setAnswers(answerList);
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
//        EssayQuestion eq = new EssayQuestion();
//        eq.setSubject("Văn");
//        eq.setId(4);
//        eq.setLevel(5);
//        eq.setContent("Ngô Tất Tố");
//        eq.setAnswer("Chị Dậu");
//        eq.setDescription("Tắt đèn");

//        JSONObject questionObject = (new FileSaver()).encodeChoiceQuestion(choiceQuestion);
//        System.out.println(questionObject);
//        StringWriter out = new StringWriter();
//        questionObject.writeJSONString(out);
 
//        list.add(eq);

//       System.out.print(out.toString());
        
        FileSaver fs = new FileSaver();
//        fs.saveQuestion("E:/Subject/LTHDT/Project/test7.txt", list);      
        for (Question cq : fs.readFile("E:/Subject/LTHDT/Project/test2.txt")) {
            if (cq instanceof ChoiceQuestion) {
                System.out.println("subject: " + cq.getSubject());
                System.out.println("id: " + cq.getId());
                System.out.println("level: " + cq.getLevel());
                System.out.println("content: " + cq.getContent());
//                System.out.println("answers:\n" + ((ChoiceQuestion) cq).printf());                
                System.out.println();
            } else if (cq instanceof EssayQuestion) {
                System.out.println("subject: " + cq.getSubject());
                System.out.println("id: " + cq.getId());
                System.out.println("level: " + cq.getLevel());
                System.out.println("content: " + cq.getContent());
                System.out.println("answer: " + ((EssayQuestion) cq).getAnswer());
                System.out.println("description: " + ((EssayQuestion) cq).getDescription());
                System.out.println();
            }
        }
    }
}
