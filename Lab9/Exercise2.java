import java.io.*;
import java.util.*;


public class Exercise2
{

    public static void main( String[] args )
    {

try 
{

        int[][]m1 = new int[2][3];
        int[][]m2 = new int[2][3];
        int[][]m3 = new int[2][3];

		for (int r=0; r<m1.length ; ++r)
		{
			for (int c=0 ; c<m1[r].length ; ++c )
				m1[r][c] = (c+1) * (r+1);
		}

		for (int r=0; r<m2.length ; ++r)
		{
			for (int c=0 ; c<m2[r].length ; ++c )
				m2[r][c] = (r+2) * (c+2);
		}

		printMatrix("MATRIX1",m1);
		printMatrix("MATRIX2",m2);
		addMatrices(m3, m1,m2 );    

		printMatrix("MATRIX1 + MATRIX2",m3);

}
catch( Exception e )
{
	System.out.println("EXCEPTION CAUGHT: " + e );
	System.exit(0);
}


    } // END MAIN

    private static void addMatrices( int[][] sum, int[][]m1, int[][]m2 )
    {
		for (int r = 0; r < sum.length; r++)
		{
			for (int c = 0; c < sum[r].length; c++)
			{
				sum[r][c] = m1[r][c] + m2[r][c];
			}
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
            } // END FOR EACH ROW

        System.out.println(); 
    } // END PRINTMATRIX

} // END CLASS
