import java.io.*;
import java.util.*;

public class Exercise2
{
	public static void main( String args[] )
	{

		Scanner kbd = new Scanner(System.in);
		do 
		{
			try
			{
				System.out.print("Enter a number between 1 and 100 ");
				int x = kbd.nextInt();
				if (x < 1 || x > 100)
				{
					throw new Exception("Out of Range Exception. Must be in 1..100\n");
				}
				System.out.printf("You entered %d\n", x );
				break;
			}
			catch (InputMismatchException e)
			{
				System.out.print("Input not a number. Try again.\n");
				kbd.next();
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		while (true);
		

	} //END main
} //EOF