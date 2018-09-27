import java.io.*;
import java.util.*;

public class Project4
{
	public static void main( String[] args ) throws Exception
	{
		final long MILLISEC_PER_SEC=1000;
		final long SEC_PER_MIN = 60;
		long startTime = System.currentTimeMillis();
		
		if (args.length < 2 )
		{
			System.out.println("\nusage: C:\\>java Project4 dictionary.txt jumbles.txt\n");
			System.exit(0);
		}

		// PROCESS DICTIONARY FILE
		BufferedReader dFile = new BufferedReader( new FileReader(args[0]) );
		ArrayList<String> answerKey = new ArrayList<String>();	//创建一个数组，里面的String是由一个单词的canonical形式
		                                                        //和一个空格加原单词组成的
		while ( dFile.ready() )
		{
			String dWord = dFile.readLine();
			answerKey.add( canonical(dWord) + " " + dWord );
		} 
		dFile.close();	
		Collections.sort( answerKey ); //通过Collections.sort来给ArrayList排序

		
		BufferedReader jFile = new BufferedReader( new FileReader(args[1]) );
		ArrayList<String> jum = new ArrayList<String>();	
		while ( jFile.ready() )
	   {
			jum.add( jFile.readLine() );
	   }
		jFile.close();	
		Collections.sort( jum );
	
		for ( String jWord : jum )
		{		
			System.out.print(jWord + " "); //先打印出jumble word 和一个空格，后面再加对应上的所有字典里的词
			String jCanWord = canonical(jWord);
			int index = searchAnswerKey (answerKey, jCanWord + " "); //通过向searchAnswerKey方法传入数组和要查找的词
			                                                         //(jumble word的canonical形式加空格，)为了区分
																	 //相同开头的单词
			if (index == -1) //返回-1就说明没有找到单词，什么也不输出，直接空行到下一个单词
				System.out.println();
			else
			{
				while (answerKey.get(index).startsWith(jCanWord + " ")) //通过返回的index找后面的单词是否也能match，因为都已经sort过了，所以上下一定差不多
				{
					System.out.print(answerKey.get(index).substring(jCanWord.length()+1) + " "); //通过String类的substring方法只打印answerKey中原单词
					++index;
				}
				System.out.println(); //空行找下一个单词
			}
		}
		long stopTime = System.currentTimeMillis();
		long elapsedMillisec = stopTime-startTime;		// runtime in millisecs
		long elapsedSec = elapsedMillisec/MILLISEC_PER_SEC;	// truncated whole secs
		long elapsedMin = elapsedSec/SEC_PER_MIN;		// truncated whole mins
		elapsedSec %= SEC_PER_MIN;               // just keep remainder secs after whole mins removed
		elapsedMillisec %= MILLISEC_PER_SEC;				// just keep remainder millisecs after whole secs removed

		System.out.format("\nYour program took %d min and %s sec and %d millisec (thousandths) to execute\n",elapsedMin,elapsedSec, elapsedMillisec);
	}
		/*for each jWord in the jumbles arraylist
		{	print jWord + " "
			make a canoncial from it called jCanon
			int index = searchAnswerKey( jCanon + " " );
			if index comes back -1 
				println() // there was NO dict word mathching
			else
				use index to go to the first match in answerKey
			while the string at answerKey.get(index) startsWith jcanon + " "
			{	print that string BUT only print substring starting at jCaonon.length()+1 
				++index
			}
			println
		} //END FOR EACH JUMBLE WORD IN JLIST
	
	}*/
	
	static int searchAnswerKey (ArrayList<String> answerKey, String jSearch ) //若找到返回index，没找到返回-1
	{
		for (int i = 0 ; i < answerKey.size() ; ++i )
		{
			if (answerKey.get(i).startsWith(jSearch)) //通过String类的.startsWith方法来在字典的每一个单词里面找是否有此开头的单词，有的话返回index
				return i;
		}				
		return -1;  // 没有的话返回-1
	}
	

	static String canonical( String word ) //通过此方法把word内部变成字母表排列的形式
	{	
		char[] letters = word.toCharArray(); 
		Arrays.sort( letters ); 
		return new String( letters ); 
	} 	
}