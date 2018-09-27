/* Lab3.java  InsertInOrder */

import java.util.*;
import java.io.*;

public class Lab3
{
	static final int INITIAL_CAPACITY = 5;

	public static void main( String args[] ) throws Exception
	{
		if (args.length < 1 )
		{
			System.out.println("\nusage: C:\\> java Lab3 L3input.txt\n");
			System.exit(0);
		}

		Scanner infile =  new Scanner( new File( args[0] ) );
		int[] arr = new int[INITIAL_CAPACITY];
		int count= 0;
		while (infile.hasNextInt())
		{
			if ( count == arr.length ) arr = upSize( arr );
			insertInOrder( arr, count, infile.nextInt() );
			++count; //通过count来计算放入的个数or初始化的个数，但是下标值是count-1
		}
		infile.close();
		printArray( "SORTED ARRAY: ", arr, count );

	}
	
	
	static void printArray( String caption, int[] arr, int count  )
	{
		System.out.print( caption );
		for( int i=0 ; i<count ;++i )
			System.out.print(arr[i] + " " );
		System.out.println();
	}

	
	static void insertInOrder( int[] arr, int count, int key   ) //插入指定key
	{
		int i;
		for (i = count - 1; i >= 0; i--)
		{
		    if (key > arr[i]) //如果key比array从小到大sorted后最后一个还大，就说明是最大的，break直接把key赋给array最后一个的位置，
		    {	
				break;
		    }
		    arr[i + 1] = arr [i];  //如果不就arr[i]向右移一位，for循环后找到key适合的位置，break 把key放入正确的位置上
		}
	    arr[i + 1] = key;
	}
	static int[] upSize( int[] fullArr ) //两倍扩容
	{
		int[] newArr = new int[fullArr.length * 2];
		for (int i = 0; i < fullArr.length; i++)
			newArr[i] = fullArr[i]; //deep copy
		return newArr; 
	}
} // END LAB3