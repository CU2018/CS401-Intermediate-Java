import java.io.*;
import java.util.*;

public class Timing
{
	public static void main( String[] args ) throws Exception
	{
		final long MILLISEC_PER_SEC=1000;
		final long SEC_PER_MIN = 60;
		long startTime = System.currentTimeMillis();	


		// ....ALL YOUR MAIN CODE IN HERE
		
		BufferedReader infile = new BufferedReader (new FileReader(args[0]));
		//HashSet<String> hash = new HashSet<String>();  //small.txt: runtime: 1 millisec   large.txt: runtime: 1 sec and 804 millisec (thousandths)
		//ArrayList<String> arr = new ArrayList<String>();  //small.txt: runtime: 191 millisec  large.txt: runtime: infinite!!!!!!!
		TreeSet<String> tree = new TreeSet<String>();  //small.txt: runtime: 2 millisec  large.txt: runtime:  2 sec and 51 millisec (thousandths)
		while (infile.ready())
		{
			String word = infile.readLine();
			//if (arr.contains(word))
			if (!tree.add(word))
			//if (!hash.add(word))
			{
				System.out.println("NOT UNIQUE!");
				long stopTime = System.currentTimeMillis();
				long elapsedMillisec = stopTime-startTime;		
				long elapsedSec = elapsedMillisec/MILLISEC_PER_SEC;	
				long elapsedMin = elapsedSec/SEC_PER_MIN;		
				elapsedMillisec %= MILLISEC_PER_SEC;			

				System.out.format("\nYour program took %d min and %s sec and %d millisec (thousandths) to execute\n",elapsedMin,elapsedSec, elapsedMillisec);
				System.exit(0);
			}
			//arr.add(word);
		/*BufferedReader infile = new BufferedReader (new FileReader(args[0]));
		String prev = infile.readLine();
		while (infile.ready())
		{
			String next = infile.readLine();
			if (prev.equals(next))
			{
				System.out.println("NOT UNIQUE!");
				System.exit(0);
			}
			prev = next;
		}
		System.out.println("UNIQUE");*/
		}
		infile.close();
		System.out.println("UNIQUE");
	


		// THE VARY LAST CHUNK OF CODE IN YOUR MAIN     *****REMOVE BEFORE HANDIN*****
		long stopTime = System.currentTimeMillis();
		long elapsedMillisec = stopTime-startTime;		
		long elapsedSec = elapsedMillisec/MILLISEC_PER_SEC;	
		long elapsedMin = elapsedSec/SEC_PER_MIN;		
		elapsedMillisec %= MILLISEC_PER_SEC;			

		System.out.format("\nYour program took %d min and %s sec and %d millisec (thousandths) to execute\n",elapsedMin,elapsedSec, elapsedMillisec);
	}
}