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
        return this.isTrue;
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
    
//    public String print(){
//        String data = "id: " + getId() + "\n" + "isTrue: " + (isIsTrue() ? "true" : "false") + "\n" + "content: " + getContent() + "\n\n";
//        return data;
//    }
    
}
