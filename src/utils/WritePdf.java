package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import objs.ChoiceAnswer;
import objs.ChoiceQuestion;
import objs.EssayQuestion;
import objs.Question;

public class WritePdf {

    public static void printPdf(String path, ArrayList<Question> questions) {
        try {
            Document test = new Document();
            Document solution = new Document();

            Font font = FontFactory.getFont("./lib/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            
            String dir = Paths.get(path.substring(0, path.lastIndexOf('.'))).normalize().toString();
            
            PdfWriter.getInstance(test, new FileOutputStream(dir + ".pdf"));
            PdfWriter.getInstance(solution, new FileOutputStream(dir + ".solution.pdf"));

            test.open();
            solution.open();

            Paragraph testParagraph = new Paragraph("Đề thi", font);
            Paragraph solutionParagraph = new Paragraph("Đáp án", font);

            String str = null;

            int essayCount = 1;
            int choiceCount = 1;

            str = "\nI. PHẦN TỰ LUẬN:\n";
            testParagraph.add(str);
            solutionParagraph.add(str);

            for (int i = 0; i < questions.size(); ++i) {
                if (questions.get(i).getClass() == ChoiceQuestion.class) {
                    str = "Câu " + String.valueOf(choiceCount) + ": " + questions.get(i).getContent() + "\n";

                    testParagraph.add(str);
                    solutionParagraph.add(str);

                    ChoiceQuestion choiceQuestion = (ChoiceQuestion) questions.get(i);
                    ArrayList<ChoiceAnswer> answers = choiceQuestion.getAnswers();

                    char alpha = 'A';
                    for (ChoiceAnswer answer: answers) {
                        str = String.valueOf(alpha) + ". " + answer.getContent() + "\n";
                        testParagraph.add(str);
                        str = String.valueOf(alpha) + ". " + answer.isTrue() + "\n";
                        solutionParagraph.add(str);

                        ++alpha;
                    }

                    ++choiceCount;
                }
            }

            str = "\nII. PHẦN TRẮC NGHIỆM:\n";
            testParagraph.add(str);
            solutionParagraph.add(str);

            for (int i = 0; i < questions.size(); ++i) {
                if (questions.get(i).getClass() == EssayQuestion.class) {
                    str = "Câu " + String.valueOf(essayCount) + ": " + questions.get(i).getContent() + "\n";

                    testParagraph.add(str);
                    solutionParagraph.add(str);

                    EssayQuestion essayQuestion = (EssayQuestion) questions.get(i);
                    
                    if (essayQuestion.getDescription() != null && !"".equals(essayQuestion.getDescription())) {
                        str = "Gợi ý: " + essayQuestion.getDescription() + "\n";
                        testParagraph.add(str);
                    }
                    str = "Đáp án: " + essayQuestion.getAnswer() + "\n";
                    solutionParagraph.add(str);

                    ++essayCount;
                }
            }

            test.add(testParagraph);
            solution.add(solutionParagraph);

            test.close();
            solution.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        ArrayList<ChoiceAnswer> answer = new ArrayList<>();
        ChoiceAnswer s1 = new ChoiceAnswer();
        s1.setContent("Absolute");
        ChoiceAnswer s3 = new ChoiceAnswer(1, true,"");

        answer.add(s1);
        answer.add(s3);
        ChoiceQuestion cauhoi = new ChoiceQuestion();
        cauhoi.setContent("hãy tả con mèo nhà em");
        cauhoi.setAnswers(answer);
        EssayQuestion cauhoi2 = new EssayQuestion("cam nhan cua e ve mua thu", "hoi lanh nong nan", "ban thay ntn?");
        cauhoi2.setContent("điểm khác nhau giữa interface và abstract");
        cauhoi2.setAnswer("gồm 4 ý chính");
        ArrayList<Question> question = new ArrayList<>();
        question.add(cauhoi2);
        question.add(cauhoi);
        printPdf("./Dethi", question);

    }

}
