
public class Parser
{

	public Lexer lexer;
	public String token;
	public int [] symbolTable = new int[26];
	public int [] symbolTableUse = new int[26];

	// function start parser
	public void parse(String command) throws Exception
	{
		lexer=new Lexer(command);
		//take the command to the Lexer for check if the input are acceptable
		line();
	}
	
	// function line
	public void line() throws Exception
	{
		int res=0;
		int resIndex=0;
		boolean flag=true;
        token=lexer.getToken();
        
        // run until we have no more tokens
		while(!(token.equals("null")))
		{	
			// legal first token can be integer or ( or -
			if ((Character.isDigit(token.charAt(0))) || (token.equals("("))|| (token.equals("-")))
				res = expression();
			// legal first token can be char
			else if (Character.isLowerCase(token.charAt(0)))
			{
				resIndex=token.charAt(0)-97;
				token=lexer.getToken();
				// insert case
				if (token.equals("="))
				{
					token=lexer.getToken();
					res=expression();
					flag=false;
				}
				// show result case
				else if (!token.equals("null"))
				{
					lexer.beginIndex=lexer.beginIndex-2;
					token=lexer.getToken();
					res = expression();
				}
			}
			// the token ; must end the string
			if (token.equals(";"))
			{
				token=lexer.getToken();
				if(!token.equals("null"))throw new Exception("Parser error! tokens after ;");
			}
			else throw new Exception("Parser error!");
		}
		// show or insert the result
		if (flag) System.out.println(res);
		else
		{
			symbolTable[resIndex]=res;
			symbolTableUse[resIndex]=1;
		}
	}

	// function expression
	public int expression() throws Exception
	{
		int val;
		String op;
		val=term();
		// support more then one action of +- 
		while( token.equals("+") || token.equals("-") )
		{
			op=token;
			token=lexer.getToken();
			// do action
			if(op.equals("+")) val=val+term();
			else val=val-term(); 
		}
		return val;
	}
	
	// func term
	public int term()throws Exception
	{
		int val;
		String op;
		val=factor();
		// support more then one action of */ 
		while( token.equals("*") || token.equals("/"))
		{
			op=token;
			token=lexer.getToken();
			// do action
			if (op.equals("*")) val=val*factor();
			else val=val/factor();
		}
		return val;
	}
	
	// func factor
	public int factor() throws Exception
	{
		int val;
		
		// case token is integer
		if (Character.isDigit(token.charAt(0)))
			{
				val=Integer.parseInt(token);
				token=lexer.getToken();
				return val;
			}
		// case token is lower case letter
		else if(Character.isLowerCase(token.charAt(0)))
		{
			// chack if var init
			if(symbolTableUse[token.charAt(0)-97]==1)
			{
			val=symbolTable[token.charAt(0)-97];
			token=lexer.getToken();
			return val;
			}
			else throw new Exception("Parser error! var not init"); 
		}
		// case token is minus
		else if (token.equals("-")) 
		{
			token=lexer.getToken();
			return -factor();
		}
		// case token is left '('
		else if (token.equals("(")) 
			{
    			token=lexer.getToken();
				val=expression();
				// right ')' for closing expression
				if(token.equals(")"))
				{
					token=lexer.getToken();
					return val;
				}
				// if there is no rparen 
				throw new Exception("Parser error! missing rparen");
			}
		throw new Exception("Parser error!");
	}
}
