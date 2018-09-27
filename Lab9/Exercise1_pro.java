import java.io.*;
import java.util.*;

public class Exercise1_pro
{
	public static void main(String [] args)
	{
		int[][] m = new int[5][5];
		fillDiagonal_1(m);
		printMatrix("DIAGONAL_1", m);
		zeroMatrix(m);
		printMatrix("ZEROS", m);
		fillDiagonal_2(m);
		printMatrix("DIAGONAL_2", m);
		zeroMatrix(m);
		printMatrix("ZEROS", m);
		fillBoarder(m);
		printMatrix("BOARDER", m);
	}
	
	private static void fillDiagonal_1(int[][] matrix)
	{
		for (int i = 0; i < matrix.length*matrix.length; i++)
		{
			if (i/matrix.length + i%matrix.length == matrix.length - 1)
				matrix[i/matrix.length][i%matrix.length] = i%matrix.length;
			else
				matrix[i/matrix.length][i%matrix.length] = 0;
		}
	}
	
	private static void fillDiagonal_2(int[][] matrix)
	{
		for (int i = 0; i < matrix.length*matrix.length; i++)
		{
			if (i/matrix.length == i%matrix.length)
				matrix[i/matrix.length][i%matrix.length] = i%matrix.length;
			else
				matrix[i/matrix.length][i%matrix.length] = 0;
		}
	}
	
	private static void fillBoarder(int[][] matrix)
	{
		for (int c = 0; c < matrix[0].length; c++)
			matrix[0][c] = c;
		for (int c = 0; c < matrix[matrix.length - 1].length; c++)
		{
			int r = matrix.length-1;
			matrix[r][c] = r - c;
		}
		for (int r = 0; r < matrix.length; r++)
			matrix[r][0] = r;
		for (int r = 0; r < matrix.length; r++)
		{
			int c = matrix[r].length-1;
			matrix[r][c] = c - r;
		}
	}
	
	private static void zeroMatrix(int[][] matrix)
	{
		for (int r = 0; r < matrix.length; r++)
		{
			for (int c = 0; c < matrix[r].length; c++)
				matrix[r][c] = 0;
		}
	}
	
	private static void printMatrix(String label, int[][] matrix)
	{
		System.out.println(label);
		for (int row=0 ; row<matrix.length ; row++)
        {
            for (int col=0 ; col < matrix[row].length ; ++col )
                System.out.print( matrix[row][col] + " ");

            System.out.println();
        }
        System.out.println(); 
	}
}