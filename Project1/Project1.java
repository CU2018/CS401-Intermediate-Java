// F16 CS 401 Speeding Ticket Project
// Project1.java Starter File

import java.io.*;
import java.util.*;

public class Project1
{
	public static void main (String args[])
	{
		// Create a scanner to read from keyboard
		Scanner kbd = new Scanner (System.in);

		String firstName="N/A",lastName="N/A";
		int age=0, speedLimit=0, actualSpeed=0, mphOverLimit=0;
		int baseFine=0, underAgeFine=0, zoneFine=0, totalFine=0;
		// DO NOT ADD TO OR MODIFY ABOVE THIS LINE
		System.out.print("Enter first name and last name: "); 
        firstName = kbd.next();
        lastName = kbd.next();
       
        System.out.print("Your age: ");
        age = kbd.nextInt();
        
        System.out.print("The speed limit: ");
        speedLimit = kbd.nextInt();
        
        System.out.print("Driver's actual speed: ");
        actualSpeed = kbd.nextInt();
        
        mphOverLimit = actualSpeed - speedLimit;
        
        kbd.nextLine();
        
        System.out.print("Did violation occur in a construction zone? (true/false): ");
        boolean constructionZone = kbd.nextBoolean(); 
        
        final int LOWER_FINE = 30, HIGER_FINE = 50, MAJORITY_AGE = 21;
       
        if (mphOverLimit < 5)
        {
        	baseFine = 0;
            zoneFine = 0;
        }
        else if (mphOverLimit > 5 && mphOverLimit <20)
        {
        	baseFine = (mphOverLimit / 5) * LOWER_FINE;
        	
        	if (constructionZone)	
        		zoneFine = baseFine;
        	else 
        		zoneFine = 0;
        	
        }
        else if (mphOverLimit > 20)
        {
        	baseFine = (mphOverLimit / 5) * HIGER_FINE;
        	
        	if (constructionZone)	
        		zoneFine = baseFine;
        	else 
        		zoneFine = 0;
        	
        	if (age < MAJORITY_AGE)
        		underAgeFine = 300;
        	else 
        		underAgeFine = 0;
        	
        }
        totalFine = baseFine + zoneFine + underAgeFine;
		// your variables & code in here
        



		// DO NOT ADD TO OR MODIFY BELOW THIS LINE
		System.out.println();
		System.out.format( "name: %s, %s\n",lastName,firstName );
		System.out.format( "age: %d yrs.\n",age );
		System.out.format( "actual speed: %d mph.\n",actualSpeed );
		System.out.format( "speed limit: %d mph.\n",speedLimit );
		System.out.format( "mph over limit: %d mph.\n",mphOverLimit );
		System.out.format( "base fine: $%d\n",baseFine );
		System.out.format( "zone fine: $%d\n",zoneFine );
		System.out.format( "under age fine: $%d\n",underAgeFine );
		System.out.format( "total fine: $%d\n",totalFine );
	} // END MAIN
} // END PROJECT1 CLASS