import java.io.*;
import java.util.*;

public class Test2
{
	static HashSet<String> dWords = new HashSet<String>();
	static TreeSet<String> wordsFound = new TreeSet<String>();
	
	public static void main (String[] args) throws Exception
	{ 
		BufferedReader dictionary = new BufferedReader (new FileReader("dictionary.txt"));
		while (dictionary.ready())
		{
			dWords.add(dictionary.readLine());
		}
		dictionary.close();
		Scanner board = new Scanner(new File(args[0]));
		int rowsAndCols = board.nextInt();
		String[][] boggle = new String[rowsAndCols][rowsAndCols];
		while (board.hasNext())
		{
			for (int r = 0; r < boggle.length; r++)
			{
				for (int c = 0; c < boggle.length; c++)
					boggle[r][c] = board.next();
			}
		}
		board.close();
		String word = "";
		for (int r = 0; r < boggle.length; r++)
			for (int c = 0; c < boggle[r].length; c++)
				depthFirstSearch(boggle, r, c, word);
		for (String fWords : wordsFound)
			System.out.println(fWords);
	}
   
    private static void depthFirstSearch(String[][] boggle, int r, int c, String word ) throws Exception
    {	
		word += boggle[r][c];
		
		if (dWords.contains(word)&&word.length() >= 3)
				wordsFound.add(word);
		
		
		if ((!outOfBound(boggle,r-1,c))&&isLowerCase(boggle[r-1][c])) //N
		{
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r-1, c, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r-1,c+1))&&isLowerCase(boggle[r-1][c+1])) //NE
		{
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r-1, c+1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r,c+1))&&isLowerCase(boggle[r][c+1])) //E
		{
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r, c+1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r+1,c+1))&&isLowerCase(boggle[r+1][c+1])) //SE
		{
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r+1, c+1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r+1,c))&&isLowerCase(boggle[r+1][c])) //S
		{
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r+1, c, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r+1,c-1))&&isLowerCase(boggle[r+1][c-1])) //SW
		{
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r+1, c-1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r,c-1))&&isLowerCase(boggle[r][c-1])) //W
		{
			boggle[r][c] = boggle[r][c].toUpperCase();
			depthFirstSearch(boggle, r, c-1, word);
			boggle[r][c] = boggle[r][c].toLowerCase();
		}
		if ((!outOfBound(boggle,r-1,c-1))&&isLowerCase(boggle[r-1][c-1]))  //NW
		{
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
   
    
}//END Test2