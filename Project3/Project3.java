/* Project3.java  InsertInOrder with bSearch optimization to compute insertion index */

import java.util.*;
import java.io.*;

public class Project3
{
	static final int INITIAL_CAPACITY = 5;

	public static void main( String args[] ) throws Exception
	{
		if (args.length < 1 )
		{
			System.out.println("ERROR: Must enter an int on cmd line (i.e. # of random ints to put in array)\n");
			//要求user在使用cmd时放入一个int来表示放入数组的int个数
			System.exit(0);
		}

		int numInts2generate = Integer.parseInt( args[0] ); //抓取cmd中用户输入的int

		Random randGen =  new Random( 17 ); // 通过random生成随机数，17是seed，这样每次生成的数都是一样的
		int[] arr = new int[INITIAL_CAPACITY];
		int count= 0;
		for ( int i = 0 ; i<numInts2generate ; ++i)
		{
			if ( count==arr.length ) arr = upSizeArr(arr); //先判断arr是否够大，不够大就手动upSize
			insertInOrder( arr, count++, 1 + randGen.nextInt(1000) ); //1 + randGen.nextInt(1000)表示产生从1到1000的随机数，
			                                                          //由于seed，每次产生的随机数都相同；count++用于计算已
																	  //初始化的值，便于通过trimArr把arr的大小重新定义
		}
		arr=trimArr(arr,count); // 放入count值，把arr大小变得刚好，全部初始化
		printArray( arr );  

	} // END MAIN

	static void printArray( int[] arr  ) //打印数组中所有成员
    {
        for( int i=0 ; i<arr.length ;++i )
            System.out.print(arr[i] + " " );
        System.out.println();
    }

	static int[] upSizeArr( int[] fullArr ) //两倍扩容
	{
		int[] upSizedArr = new int[ fullArr.length * 2 ];
		for ( int i=0; i<fullArr.length ; ++i )
			upSizedArr[i] = fullArr[i];
		return upSizedArr;
	}

	static int[] trimArr( int[] oldArr, int count ) //原来的数组可能比已初始化的数大，需要通过此方法来缩小数组，保证数组中
	{                                               //所有的数都已经初始化
		int[] trimmedArr = new int[ count ];
		for ( int i=0; i<count ; ++i )
			trimmedArr[i] = oldArr[i];
		return trimmedArr;
	}

	static void insertInOrder( int[] arr, int count, int newVal  ) //通过binearysearch找到合适位置插入新值
	{
		int index = bSearch( arr, count, newVal );

		if (index < 0) //若返回的index为负，就是没有找到，就把index推回去，找到可以加入的index位置
			index = -index - 1;
		for (int i = count - 1; i >= index; i--) //此时index全都变成正数，把index右边的全都右移，给index留位置
		{
			arr[i + 1] = arr[i];
		}
		arr[index] = newVal;  //把新值放入index留的位置中
	}

	static int bSearch(int[] a, int count, int key)    //二分法查找方法 O(log2(N))
	{
		int lo = 0, hi = count - 1;
		while (lo <= hi) //循环在lo和hi不相交的情况下进行
		{
			int mid = lo + (hi-lo)/2; //取一个中间值，不能用(hi + lo) / 2，数值一大容易overflow，前者就不会
			if (key == a[mid]) //如果key刚好等于中间值，就返回mid
			{
				return mid;
			}
			else if (key > a[mid]) //如果key更大，就说明要在中间值劈开的两个部分中右边更大的位置中继续找，lo右移至mid+1
				lo = mid + 1;
			else   //如果key更小，就说明要在中间值劈开的两个部分中左边更小的位置中继续找，hi左移至mid-1
				hi = mid - 1;
		}
		return -(lo + 1); //若没有找到，就返回-(lo+1)，通过insertInOrder判断正负，若为负，就放到-index-1 or -(index + 1)的位置
	}
} // END PROJECT3