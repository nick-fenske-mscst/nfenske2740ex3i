package nfenske2740ex3i;

import javax.swing.DefaultListModel;

public class DriverExam 
{
	private char[] answers;
	private char[] responses;
	private final double requiredPct = 0.7;
	
	public DriverExam(char[] input)
	{
		this.answers = new char[input.length];
		for(int i = 0; i < input.length; i++)
		{
			this.answers[i] = input[i];
		}
	}
	
	public DriverExam(DefaultListModel input)
	{
		this.answers = new char[input.getSize()];
		for(int i = 0; i < input.getSize(); i++)
		{
			String r = input.get(i).toString();
			this.answers[i] = r.charAt(0);
		}
	}
	
	public void setResponses(DefaultListModel input)
	{
		this.responses = new char[input.getSize()];
		for(int i = 0; i < input.getSize(); i++)
		{
			String r = input.get(i).toString();
			this.responses[i] = r.charAt(0);
		}
	}
	
	public DefaultListModel getAnswers()
	{
		DefaultListModel answersModel = new DefaultListModel();
		
		for(int i = 0; i < this.answers.length; i++)
		{
			answersModel.addElement(this.answers[i]);
		}
		
		return answersModel;
	}
	
	public int validate()
	{
		int i = 0;
		
		while(i < this.responses.length)
		{
			if(this.responses[i] != 'A' && this.responses[i] != 'B' && this.responses[i] != 'C' && this.responses[i] != 'D')
			{
				return i;
			}
			
			i++;
		}
		return -1;
	}
	
	public boolean passed()
	{		
		if(totalCorrect() >= requiredPct * this.responses.length)
			return true;
		
		return false;
	}
	
	public int totalCorrect()
	{
		int count = 0;
		
		for(int i = 0; i < this.answers.length; i++)
		{
			if(this.responses[i] == this.answers[i])
				count++;
		}
		return count;
	}
	
	public int totalIncorrect()
	{
		int count = 0;
		
		for(int i = 0; i < this.answers.length; i++)
		{
			if(this.responses[i] != this.answers[i])
				count++;
		}
		return count;
	}
	
	public int[] questionsMissed()
	{
		int[] missed = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int m = 0;
		
		for(int i = 0; i < this.answers.length; i++)
		{
			if(this.responses[i] != this.answers[i])
			{
				missed[m] = i + 1;
				m++;
			}
		}
		return missed;
	}
	
}
