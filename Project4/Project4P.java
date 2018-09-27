import java.util.*;
import java.io.*;

public class Project4P
{
	public static void main(String[] args) throws Exception
	{
		if (args.length < 2)
		{
			System.out.println("\nusage: C:\\>java Project4P dictionary.txt jumbles.txt\n");
			System.exit(0);
		}
		
		BufferedReader dFile = new BufferedReader(new FileReader(args[0]));
		ArrayList <String> answerKey = new ArrayList<String>();
		while (dFile.ready())
		{
			String dWord = dFile.readLine();
			String dCanWord = canonical(dWord);
			answerKey.add( dCanWord + " " + dWord );
		}
		dFile.close();
		Collections.sort(answerKey);
		
		BufferedReader jFile = new BufferedReader(new FileReader(args[1]));
		ArrayList<String> jum = new ArrayList<String>();
		while (jFile.ready())
		{
			jum.add(jFile.readLine());
		}
		jFile.close();
		Collections.sort(jum);
		
		for (String jWord : jum)
		{
			System.out.print(jWord + " ");
			String jCanWord = canonical(jWord);
			int index = searchAnswerKey(answerKey, jCanWord + " ");
			if (index == -1)
			{
				System.out.println();
			}
			else
			{
				while(answerKey.get(index).startsWith(jCanWord + " "))
				{
					System.out.print(answerKey.get(index).substring(jCanWord.length() + 1) + " ");
					++index;
					//if (index > answerKey.size() - 1)
					//	break;
				}
				System.out.println();
			}
				
		}
	}
	static int searchAnswerKey(ArrayList<String>answerKey, String jSearch)
	{
		int size = answerKey.size() - 1;
		for (int i = 0; i < size; i++)
		{
			if (answerKey.get(i).startsWith(jSearch))
				return i;
		}
		return -1;
	}
	static String canonical(String word)
	{
		char[] letters = word.toCharArray();
		Arrays.sort(letters);
		return new String(letters);
	}
}

















