
import java.io.*;

public class Trie
{
	private static final int size = 26;
	private static TrieNode root;
	public Trie()
	{
		this.root = new TrieNode();
	}
	
	private static class TrieNode
	{
		private TrieNode[] children;
		private boolean isEnd;
		public TrieNode()
		{
			this.children = new TrieNode[size];
			this.isEnd = false;
		}
	}
		
	public static boolean addWord(String str)
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
	
	public static boolean containsPrefix(String str)
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
	
	public static boolean containsWord(String str)
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
	
	/*public static void main(String[] args) throws Exception
	{
		BufferedReader dictionary = new BufferedReader (new FileReader("dictionary.txt"));
		Trie trie = new Trie();
		while (dictionary.ready())
		{
			trie.addWord(dictionary.readLine());
		}
		dictionary.close();
		System.out.println(containsPrefix("quer"));
		System.out.println(containsWord("quer"));
	}*/
}