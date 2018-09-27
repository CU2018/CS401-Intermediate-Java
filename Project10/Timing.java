import java.io.*;
import java.util.*;

public class Timing
{
	//static TreeSet<String> wordsFound = new TreeSet<String>();
	static ArrayList<String> wordsFound = new ArrayList<String>();
	static Trie trie;

	public static void main( String[] args ) throws Exception
	{
		final long MILLISEC_PER_SEC=1000;
		final long SEC_PER_MIN = 60;
		long startTime = System.currentTimeMillis();	


		// ....ALL YOUR MAIN CODE IN HERE
		Timing t = new Timing();
		trie = t.new Trie();
		BufferedReader dictionary = new BufferedReader (new FileReader(args[0]));
		while (dictionary.ready())
		{
			trie.addWord(dictionary.readLine());
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
		Collections.sort(wordsFound);
		for (String fWords : wordsFound)
			System.out.println(fWords);


		// THE VARY LAST CHUNK OF CODE IN YOUR MAIN     *****REMOVE BEFORE HANDIN*****
		long stopTime = System.currentTimeMillis();
		long elapsedMillisec = stopTime-startTime;		
		long elapsedSec = elapsedMillisec/MILLISEC_PER_SEC;	
		long elapsedMin = elapsedSec/SEC_PER_MIN;
		elapsedSec %= SEC_PER_MIN;
		elapsedMillisec %= MILLISEC_PER_SEC;		
		

		System.out.format("\nYour program took %d min and %s sec and %d millisec (thousandths) to execute\n",elapsedMin,elapsedSec, elapsedMillisec);
	}
	
	
	private static void depthFirstSearch(String[][] boggle, int r, int c, String word ) throws Exception
    {	
		word += boggle[r][c];
		
		
		if (word.length() >= 3 && trie.containsWord(word)&& !wordsFound.contains(word))
			wordsFound.add(word);		
		else if (! trie.containsPrefix(word)) return;
		//System.out.println(word);	
		
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
	public class Trie
	{
		private final int size = 26;
		private TrieNode root;
		public Trie()
		{
			root = new TrieNode();
		}
		
		private class TrieNode
		{
			private TrieNode[] children;
			private boolean isEnd;
			public TrieNode()
			{
				this.children = new TrieNode[size];
				this.isEnd = false;
			}
		}
			
		public boolean addWord(String str)
		{
			char[] letters = str.toCharArray();
			TrieNode node = root;
			for (char c : letters)
			{
				int index = c - 'a';
				if (node.children[index] == null)
					node.children[index] = new TrieNode();
				node = node.children[index];
			}
			node.isEnd = true;
			return true;
		}
		
		public boolean containsPrefix(String str)
		{
			char[] letters = str.toCharArray();
			TrieNode node = root;
			for (char c : letters)
			{
				int index = c - 'a';
				if (node.children[index] != null)
					node = node.children[index];
				else 
					return false;
			}
			return true;
		}
		
		public boolean containsWord(String str)
		{
			char[] letters = str.toCharArray();
			TrieNode node = root;
			for (char c : letters)
			{
				int index = c - 'a';
				if (node.children[index] != null)
					node = node.children[index];
				else 
					return false;
			}
			return node.isEnd;
		}
	}
	
	static boolean outOfBound(String[][] boggle, int r, int c)
	{
		if (r < 0 || c < 0 || r > boggle.length - 1 || c  > boggle.length - 1)
			return true;
		return false;
	}

}
