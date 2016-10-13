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
public class ChoiceAnswer
{
    private int id;
    private boolean isTrue;
    private String content;

    public ChoiceAnswer() {
        this.id = 0;
        this.isTrue = false;
        this.content = "";
    }

    public ChoiceAnswer(int id, boolean isTrue, String content) {
        this.id = id;
        this.isTrue = isTrue;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsTrue() {
        return isTrue;
    }

    public void setIsTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}