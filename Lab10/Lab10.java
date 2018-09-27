import java.util.*;
import java.io.*;

public class Lab10
{
	public static void main (String[] args) throws Exception
	{
		BufferedReader infile = new BufferedReader (new FileReader(args[0]));
		HashSet<String> hash = new HashSet<String>();
		while (infile.ready())
		{
			String word = infile.readLine();
			if (!hash.add(word))
			{
				System.out.println("NOT UNIQUE");
				System.exit(0);
			}			
		}
		infile.close();
		System.out.println("UNIQUE");
	}
}