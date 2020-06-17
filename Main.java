import java.util.Scanner;

public class Main 
{	
   public static void main( String args[] ) throws Exception
   {
	   Scanner s= new Scanner(System.in);
	   //for get input
	   
	   String command;
       boolean flag=false;
       //the flag come to tell us if the commend over
       Parser parser=new Parser();
       //define of variable that come from Parser class
	   System.out.println("Enter the command END to end the program");
	   System.out.println("-----------------------------------------");
	   System.out.println("for exemple 5+5*5; ");
	   System.out.println("if you write 'end' you are close the calculator");
	   System.out.println("-----------------------------------------");
	   // for the uaer to undrestand the languge that i am defined
	   
       try
       {
		do
		{
			System.out.println("Enter your command here:");
			System.out.println();
			// request to write command
 			command=s.nextLine();
			System.out.println();
            //input of command 
			//"END" command shut down the program
			if (command.equals("END"))flag=true;
			// else call parser
			else parser.parse(command);

		} while(!flag);
       }
       catch(Exception ex)
       {
   		System.out.println(ex);
       }
       
		System.out.println("Goodbye!!!");

		s.close();
   } 
}
