/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objs;

/**
 *
 * @author ducnh
 */
public class EssayQuestion extends Question
{
    private String description;
    private String answer;

    public EssayQuestion() {
        this.description = "";
        this.answer = "";
    }

    public EssayQuestion(String description, String answer) {
        this.description = description;
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
