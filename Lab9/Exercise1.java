import java.io.*;
import java.util.*;


public class Exercise1
{
    public static void main( String[] args )
    {
        int[][]m = new int[5][5];
        fillDiagonal_1( m ); 
        printMatrix( "DIAGONAL_1",m );
        zeroMatrix( m ); 
        printMatrix( "ZEROS",m ); 
        fillDiagonal_2( m ); 
        printMatrix( "DIAGONAL_2",m );
        zeroMatrix( m );  
        printMatrix( "ZEROS",m ); 
        fillBorder( m ); 
        printMatrix( "BORDER",m );
    } 
	
    private static void fillDiagonal_1( int[][] matrix )
    {
		int c = matrix[0].length - 1;
		for (int r = 0; r < matrix.length; r++)
		{
			matrix[r][c] = c;
			c--;
		}
    }

    private static void fillDiagonal_2( int[][] matrix )
    {
		int r = matrix.length - 1;
		for (int c = matrix[r].length - 1; c >= 0; c--)
		{
			matrix[r][c] = r;
			r--;
		}
    }

    private static void fillBorder( int[][] matrix )
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

    private static void zeroMatrix( int[][] matrix )
    {
        for (int r = 0; r < matrix.length; r++)
		{
			for (int c = 0; c < matrix[r].length; c++)
				matrix[r][c] = 0;
		}
    }

    private static void printMatrix( String label, int[][] matrix )
    {
        System.out.println(label);
        for (int row=0 ; row<matrix.length ;  ++row) 
            {
                for (int col=0 ; col < matrix[row].length ; ++col )
                    System.out.print( matrix[row][col] + " ");

                System.out.println(); 
            } 

        System.out.println(); 
    }
} 