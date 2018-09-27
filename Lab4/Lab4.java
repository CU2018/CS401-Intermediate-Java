import java.util.*;
import java.io.*;

public class Lab4 
{
	public static void main(String[] args) throws Exception
	{
		if (args.length < 1)
		{
			System.out.println("\nusage: C:\\> java Lab4 jumbles.txt\n");
			System.exit(0);
		}
		
		BufferedReader infile = new BufferedReader( new FileReader(args[0]) );
		ArrayList<String> wordList = new ArrayList<String>();
		
		while (infile.ready())
		{
			wordList.add(infile.readLine());
		}
		infile.close();
					
		Collections.sort(wordList); //collections类的一个静态方法，所以可以通过类名直接访问此方法
		                            //ArrayList是Collections类的一个子类
		for (String word : wordList) 
		{
			System.out.println(word + " " + canonical(word));
		}
			
	}
	static String canonical(String word) //通过此方法，把单词变成按照字母顺序排列的形式
	{
		char[] letters = word.toCharArray(); //先通过ArrayList里面toCharArray的方法把字符串转换成一个个char字母并放入
		                                     //char 数组中
		Arrays.sort(letters); //通过Array类中的Arrays.sort()静态方法来排序char数组里面的字母，所以可以通过类名直接访问此方法
		String newWord = new String(letters); //通过new一个String，把char转换为一个String的单词
		return newWord;
	}

}