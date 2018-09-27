/* Project2.java  Dynamic histogram */

import java.io.*;
import java.util.*;

public class Project2
{
	static final int INITIAL_CAPACITY = 10;
	public static void main (String[] args) throws Exception
	{
		if (args.length < 1 )
		{
			System.out.println("\nusage: C:\\> java Project2 <input filename>\n\n"); 
			System.exit(0);
		}
		int[] histogram = new int[0]; 


		String[] wordList = new String[INITIAL_CAPACITY];
		int wordCount = 0;
		BufferedReader infile = new BufferedReader( new FileReader(args[0]) );
		while ( infile.ready() )
		{
			String word = infile.readLine();

			if (wordCount == wordList.length)
				wordList = upSizeArr (wordList);  
			
			wordList[wordCount++] = word; //这样就是从1开始算而不是0，因为没有词长为0的单词

			int wordLength = word.length();
            if (wordLength + 1 > histogram.length) 
            	histogram = upSizeHisto(histogram, wordLength + 1);  
            ++histogram[word.length()]; //对于这么长的单词的个数加一，用于计数
		
		} 
		infile.close();

		wordList = trimArr( wordList, wordCount);
		System.out.println( "After final trim: wordList length: " + wordList.length + " wordCount: " + wordCount );


		for ( int i = 0; i < histogram.length ; i++ )
			System.out.format("words of length %2d  %d\n", i,histogram[i] );

	}

	static String[] upSizeArr( String[] fullArr )
	{	
		String[] newList = new String[fullArr.length * 2]; //一般两倍upsize
		for (int i = 0; i < fullArr.length; i++)
			newList[i] = fullArr[i]; //deep copy
		return newList; 
	}
	static String[] trimArr( String[] oldArr, int count )
	{
		String[] newTrim = new String[count];
		for (int i = 0; i < count; i++)
			newTrim[i] = oldArr[i];
		return newTrim; 
	}

	static int[] upSizeHisto( int[] oldArr, int newLength )
	{
		int[] newHisto = new int[newLength];
		for (int i = 0; i < oldArr.length; i++)
			newHisto[i] = oldArr[i];
		return newHisto; 
	}
} 









