
public class Lexer 
{
	public String command;
	public String token;
	public int beginIndex=0;
	
	
	Lexer(String command)
	{
		this.command=command;
		// constructor that initalize the this.command with thw command that come from the user 
	}


	// function return current token from the string
	public String getToken() throws Exception
	{
		String token;
		int endIndex;
		
		// end of tokens
		if (beginIndex==command.length())
			return new String("null");
		// we have more tokens
		else
		{
			char begin=command.charAt(beginIndex);
	    	// if token is integer
    		if(Character.isDigit(begin)) 
    		{ 
	    		endIndex=beginIndex+1;
	    		while((endIndex<command.length()) && (Character.isDigit(command.charAt(endIndex))))
	     		      endIndex++;
	     		token = command.substring(beginIndex, endIndex);
	    		beginIndex=endIndex;
	    		return token;
	    	}
	    	// if token is one char
    		else if ((Character.isLowerCase(begin)) ||
		       (begin=='+') || (begin=='-') ||
		       (begin=='*') || (begin=='/') || 
		       (begin=='(') || (begin==')') || 
	    	   (begin=='=') || (begin==';')) 
	    	{
	      		token = command.substring(beginIndex, beginIndex+1);
	    		beginIndex++;
	    		return token;
	    	}

		}
		// if token is illegal
		throw new Exception ("Lexer Error! illegal token");
	}
	
}
