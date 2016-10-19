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
    public ChoiceAnswer(String answer)
    {
	this.content = answer;
    }
    
    public int id;
    public boolean isTrue;
    public String content;
}
