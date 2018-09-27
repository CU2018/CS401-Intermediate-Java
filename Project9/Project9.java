import java.io.*;
import java.util.*;

public class Project9
{
	private static int[][] loadSwamp(String infileName, int[] dropInt) throws Exception
	{
		Scanner infile = new Scanner(new File(infileName));
		int rowsAndCols = infile.nextInt();
		dropInt[0] = infile.nextInt();
		dropInt[1] = infile.nextInt();
		int[][] swamp = new int[rowsAndCols][rowsAndCols];
		while (infile.hasNextInt())
		{
			for (int r = 0; r < swamp.length; r++)
			{
				for (int c = 0; c < swamp.length; c++)
					swamp[r][c] = infile.nextInt();
			}
		}
		infile.close();
		return swamp;
	}
	public static void main(String []args) throws Exception
	{
		int[] dropInt = new int[2];
		int[][] swamp = loadSwamp(args[0], dropInt);
		int row=dropInt[0], col=dropInt[1];
		String path = "";
		depthFirstSearch(swamp, row, col, path);
	}
	static void depthFirstSearch(int[][]swamp, int r, int c, String path)
	{
		path += "[" + r + "," + c + "]";
		if (onEdge(swamp, r, c))
		{
			System.out.println(path);
			return;
		}
		
		int N = swamp[r-1][c];
		int NE = swamp[r-1][c+1];
		int E = swamp[r][c+1];
		int SE = swamp[r+1][c+1];
		int S = swamp[r+1][c];
		int SW = swamp[r+1][c-1];
		int W = swamp[r][c-1];
		int NW = swamp[r-1][c-1];
		
		if (N == 1)
		{
			swamp[r][c] = -1;
			depthFirstSearch(swamp, r-1, c, path);
			swamp[r][c] = 1;
		}
		if (NE == 1)
		{
			swamp[r][c] = -1;
			depthFirstSearch(swamp, r-1, c+1, path);
			swamp[r][c] = 1;
		}
		if (E == 1)
		{
			swamp[r][c] = -1;
			depthFirstSearch(swamp, r, c+1, path);
			swamp[r][c] = 1;
		}
		if (SE == 1)
		{
			swamp[r][c] = -1;
			depthFirstSearch(swamp, r+1, c+1, path);
			swamp[r][c] = 1;
		}
		if (S == 1)
		{
			swamp[r][c] = -1;
			depthFirstSearch(swamp, r+1, c, path);
			swamp[r][c] = 1;
		}
		if (SW == 1)
		{
			swamp[r][c] = -1;
			depthFirstSearch(swamp, r+1, c-1, path);
			swamp[r][c] = 1;
		}
		if (W == 1)
		{
			swamp[r][c] = -1;
			depthFirstSearch(swamp, r, c-1, path);
			swamp[r][c] = 1;
		}
		if (NW == 1)
		{
			swamp[r][c] = -1;
			depthFirstSearch(swamp, r-1, c-1, path);
			swamp[r][c] = 1;
		}	
		return;
	} // END DFS
	
	static boolean onEdge(int[][] swamp, int r, int c)
	{
		if (r == 0 || c == 0 || r == swamp.length - 1 || c == swamp.length - 1)
			return true;
		return false;
	}
	
}
