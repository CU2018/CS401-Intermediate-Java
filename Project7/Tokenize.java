import java.util.*;
import java.io.*;

public class Tokenize
{
	public static void main( String[] args)
	{
		// Stolen directly from stackoverflow with just a few mods :)

		String expr="4+5-12/3.5-5.4*3.14"; // replace with any expression to test
		System.out.println( "expr: " + expr );
		ArrayList<String> operatorList = new ArrayList<String>(); 
		
		ArrayList<String> operandList = new ArrayList<String>();
		// StringTokenizer is like an infile and calling .hasNext()
		StringTokenizer st = new StringTokenizer( expr,"+-*/", true );
		while (st.hasMoreTokens())
		{
			String token = st.nextToken();
			if ("+-/*".contains(token))
				operatorList.add(token);
			else
				operandList.add(token);
    	}
		
 		System.out.println("Operators:" + operatorList);
		System.out.println("Operands:" + operandList);
		int i = 0;
		while (i < operatorList.size())
		{
		if ("*".contains(operatorList.get(i)))
		{
			operandDouble.set(i, operandDouble.get(i) * operandDouble.get(i + 1));
			}
			if ("/".contains(operatorList.get(i)))
			{
				operandDouble.set(i, operandDouble.get(i) / operandDouble.get(i + 1));
			}
								
			i++;
		}
	}
}

