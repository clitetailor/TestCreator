/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objs;

import java.util.ArrayList;

/**
 *
 * @author ducnh
 */
public class ChoiceQuestion extends Question
{
    public ChoiceQuestion(String question, String... answers)
    {
	this.content = new String(question);
	
	this.answers = new ArrayList<ChoiceAnswer>();
	
	for (String answer: answers)
	{
	    this.answers.add(new ChoiceAnswer(answer));
	}
    }
    
    public final ArrayList<ChoiceAnswer> answers;
}
