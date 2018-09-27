import java.io.*;
import java.util.*;

public class Test1
{
	static long numWordsFound = 0;
	public static void main (String[] args) throws Exception
	{
		Scanner infile = new Scanner(new File(args[0]));
		int rowsAndCols = infile.nextInt();
		String[][] boggle = new String[rowsAndCols][rowsAndCols];
		while (infile.hasNext())
		{
			for (int r = 0; r < boggle.length; r++)
			{
				for (int c = 0; c < boggle.length; c++)
					boggle[r][c] = infile.next();
			}
		}
		infile.close();
		String word = "";
		for (int r = 0; r < boggle.length; r++)
		{
			for (int c = 0; c < boggle[r].length; c++)
			{
				System.out.println(r + " " + c);
				depthFirstSearch(boggle, r, c, word);
			}
				
		}
		System.out.println(numWordsFound);
	}
   
    private static void depthFirstSearch(String[][] boggle, int r, int c, String word )
    {	
		word += boggle[r][c];
		
		
		System.out.println(word);
	
		numWordsFound++;
		
		
		if ((!outOfBound(boggle,r-1,c))&&isLowerCase(boggle[r-1][c])) //N
		{
			System.out.print("N  ");
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r-1, c, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r-1,c+1))&&isLowerCase(boggle[r-1][c+1])) //NE
		{
			System.out.print("NE  ");
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r-1, c+1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r,c+1))&&isLowerCase(boggle[r][c+1])) //E
		{
			System.out.print("E  ");
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r, c+1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r+1,c+1))&&isLowerCase(boggle[r+1][c+1])) //SE
		{
			System.out.print("SE  ");
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r+1, c+1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r+1,c))&&isLowerCase(boggle[r+1][c])) //S
		{
			System.out.print("S  ");
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r+1, c, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r+1,c-1))&&isLowerCase(boggle[r+1][c-1])) //SW
		{
			System.out.print("SW  ");
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r+1, c-1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r,c-1))&&isLowerCase(boggle[r][c-1])) //W
		{
			System.out.print("W  ");
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r, c-1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r-1,c-1))&&isLowerCase(boggle[r-1][c-1]))  //NW
		{
			System.out.print("NW  ");
			boggle[r][c] = boggle[r][c].toUpperCase();		
			depthFirstSearch(boggle, r-1, c-1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		return;

		
    }
	
   
    static boolean isLowerCase(String str)
	{
		char chr = str.charAt(0);
		if (Character.isLowerCase(chr))
			return true;
		return false;
	}
	
	static boolean outOfBound(String[][] boggle, int r, int c)
	{
		if (r < 0 || c < 0 || r > boggle.length - 1 || c  > boggle.length - 1)
			return true;
		return false;
	}
   
}//END Test1