import java.io.*;
import java.util.*;
public class Exercise1
{
	public static void main( String args[] ) 
	{
		if (args.length < 1)
		{
			System.out.println("\nYou must enter an input filename on cmd line!\n");
			System.exit(0);
		}

		String infileName = args[0];
		Scanner infile = null;
		do 
		{
			try 
			{
				infile = new Scanner ( new File(infileName) );
				break;
			}
			catch (FileNotFoundException fnfe)
			{
				System.out.print("Bad filename :=(    Enter a valid input file name: ");
				Scanner scanner = new Scanner(System.in);
				infileName = scanner.next();
			}
		}
		while (true);

		int tokenCnt=0;
		while (infile.hasNext())
		{
			String token = infile.next();
			System.out.printf("%d: %s\n", tokenCnt++, token);
		}
	}
} //End Lab11