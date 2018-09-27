import java.io.*;
import java.util.*;

public class Boggle
{
	static TreeSet<String> dWords = new TreeSet<String>();
	static TreeSet<String> wordsFound = new TreeSet<String>();

	public static void main( String[] args ) throws Exception
	{

		BufferedReader dictionary = new BufferedReader (new FileReader(args[0]));
		while (dictionary.ready())
		{
			dWords.add(dictionary.readLine());
		}
		dictionary.close();
		
		Scanner board = new Scanner(new File(args[1]));
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
		else if (!dWords.ceiling(word).startsWith(word))
			return;	
		
		String temp;
		if ((!outOfBound(boggle,r-1,c))&&(boggle[r-1][c] != null)) //N
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r-1, c, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r-1,c+1))&&(boggle[r-1][c+1] != null)) //NE
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r-1, c+1, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r,c+1))&&(boggle[r][c+1] != null)) //E
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r, c+1, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r+1,c+1))&&(boggle[r+1][c+1] != null)) //SE
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r+1, c+1, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r+1,c))&&(boggle[r+1][c] != null)) //S
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r+1, c, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r+1,c-1))&&(boggle[r+1][c-1] != null)) //SW
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r+1, c-1, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r,c-1))&&(boggle[r][c-1] != null)) //W
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r, c-1, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r-1,c-1))&&(boggle[r-1][c-1] != null))  //NW
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r-1, c-1, word);
			boggle[r][c] = temp;
		}
		return;
	}
	
	static boolean outOfBound(String[][] boggle, int r, int c)
	{
		if (r < 0 || c < 0 || r > boggle.length - 1 || c  > boggle.length - 1)
			return true;
		return false;
	}
	
	

}//END BOGGLE
