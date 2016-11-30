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
    public EssayQuestion(String question, String answer, String description)
    {
	this.content = new String(question);
	this.description = new String(description);
	this.answer = new String(answer);
    }
    
    public EssayQuestion()
    {
	
    }
    
    public String description;
    public String answer;
}
