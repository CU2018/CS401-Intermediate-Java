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
		


		// THE VARY LAST CHUNK OF CODE IN YOUR MAIN     *****REMOVE BEFORE HANDIN*****
		long stopTime = System.currentTimeMillis();
		long elapsedMillisec = stopTime-startTime;		
		long elapsedSec = elapsedMillisec/MILLISEC_PER_SEC;	
		long elapsedMin = elapsedSec/SEC_PER_MIN;		
		elapsedMillisec %= MILLISEC_PER_SEC;			

		System.out.format("\nYour program took %d min and %s sec and %d millisec (thousandths) to execute\n",elapsedMin,elapsedSec, elapsedMillisec);
	}
}