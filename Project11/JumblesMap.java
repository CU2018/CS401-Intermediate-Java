import java.io.*;
import java.util.*;

public class JumblesMap
{
	public static void main(String [] args) throws Exception
	{
		if (args.length < 2)
		{
			System.out.println("\nusage: C:\\>java JumblesMap dictionary.txt jumbles.txt\n");
			System.exit(0);
		}
		
		BufferedReader dFile = new BufferedReader(new FileReader(args[0]));
		HashMap<String, TreeSet<String>>lookUpTable = new HashMap<String, TreeSet<String>>();
		while (dFile.ready())
		{
			String dWord = dFile.readLine();
			String dCanWord = canonical(dWord);
			TreeSet<String> dWordList = new TreeSet<String>();
			if (!lookUpTable.containsKey(dCanWord))
			{
				dWordList = new TreeSet<String>();
				dWordList.add(dWord);
				lookUpTable.put(dCanWord, dWordList);
			}
				
			else
			{
				dWordList = lookUpTable.get(dCanWord);
				dWordList.add(dWord);
				lookUpTable.put(dCanWord, dWordList);
			}
				
		}
		dFile.close();

		BufferedReader jFile = new BufferedReader(new FileReader(args[1]));
		TreeSet<String> jumbles = new TreeSet<String>();
		while (jFile.ready())
		{
			jumbles.add(jFile.readLine());
		}
		jFile.close();
		for (String jWord: jumbles)
		{
			String jCanWord = canonical(jWord);
			if (lookUpTable.containsKey(jCanWord))
			{
				System.out.print(jWord + " ");
				TreeSet <String> dWordList = new TreeSet<String>();
				dWordList = lookUpTable.get(jCanWord);
				for (String dWord : dWordList)
					System.out.print(dWord + " ");
				System.out.println();
					
			}
				
			else
				System.out.println(jWord);
		}

	}	//END main
	
	static String canonical (String word)
	{
		char [] letters = word.toCharArray();
		Arrays.sort(letters);
		return new String(letters);
	}
}