/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph
{
	private final int NO_EDGE = -1; // all real edges are positive
	private int G[][];              // will point to a 2D array to hold our graph data

	private int numEdges;
	public Graph( String graphFileName )
	{
		loadGraphFile( graphFileName );
	}
	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////
	//
	// FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
	// THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN EDGE IN THE GRAPH

	private void loadGraphFile( String graphFileName )
	{
		Scanner graphFile = null;
		try {
			graphFile = new Scanner( new File( graphFileName ) );
		}
		catch (Exception e){
			System.out.println(e);
			System.exit(0);
		}

		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new int[dimension][dimension]; 		// N x N ARRAY OF ZEROS
		numEdges=0;

        // WRITE A LOOP THAT PUTS NO_EDGE VALUE EVERYWHERE EXCPT ON THE DIAGONAL
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                G[i][j] = (i == j) ? 0 : NO_EDGE;

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// USE row & col AS WHERE TO STORE THE weight
		// i.e. g[row][col] = w;

		while ( graphFile.hasNextInt() )
		{
			// read in the row,col,weight // that eat us this line
            // call add edge
            int r = graphFile.nextInt();
            int c = graphFile.nextInt();
            int w = graphFile.nextInt();
            addEdge(r, c, w);
		}

	} // END readGraphFile

	private void addEdge( int r, int c, int w )
	{
		G[r][c] = w;
		++numEdges; // only this method adds edges so we do increment counter here only
	}
	
    private boolean hasEdge(int fromNode, int toNode)
    {
		if (numEdges == 0)
			return false;
        return (G[fromNode][toNode] != NO_EDGE) ? true : false;
    }
	// IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
	// NODE IS THE ROW COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
	private int inDegree(int node)
	{
		int deg = 0;
		for (int i = 0; i < G[node].length; i++)
			deg = (G[i][node] == NO_EDGE || G[i][node] == 0) ? deg : (deg + 1);        
		return deg;
	}

	// OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
	// NODE IS THE ROW #. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
	private int outDegree(int node)
	{
		int deg = 0;
		for (int i = 0; i < G[node].length; i++)
			deg = (G[node][i] == NO_EDGE || G[node][i] == 0) ? deg : (deg + 1);        
		return deg;
	}

	// DEGREE IS TOTAL NUMBER OF ROAD BOTH IN AND OUT OFR THE CITY 
	private int degree(int node)
	{
		return inDegree(node) + outDegree(node);
	}

	// PUBLIC METHODS 
	
	public int maxOutDegree()
	{
		int deg = 0;
		for (int i = 0; i < G.length; i++)
			deg = (outDegree(i) > deg) ? outDegree(i) : deg;
		return deg;
	}
	public int maxInDegree()
	{
		int deg = 0;
		for (int i = 0; i < G.length; i++)
			deg = (inDegree(i) > deg) ? inDegree(i) : deg;
		return deg;
	}

	public int minOutDegree()
	{
		int deg = maxOutDegree();
		for (int i = 0; i < G.length; i++)
			deg = (outDegree(i) < deg) ? outDegree(i) : deg;
		return deg;
    }
	public int minInDegree()
	{
		int deg = maxInDegree();
		for (int i = 0; i < G.length; i++)
			deg = (inDegree(i) < deg) ? inDegree(i) : deg;
		return deg;
	}	
	
	public int maxDegree()
	{
		int deg = 0;
		for (int i = 0; i < G.length; i++)
			deg = (degree(i) > deg) ? degree(i) : deg;
		return deg;
	}

	public int minDegree()
	{
		int deg = maxDegree();
		for (int i = 0; i < G.length; i++)
			deg = (degree(i) < deg) ? degree(i) : deg;
		return deg;
	}
	
	public void removeEdge(int fromNode, int toNode) throws Exception
	{
		try {
			if (hasEdge(fromNode, toNode))
				G[fromNode][toNode] = NO_EDGE;
		}
		catch (Exception e){
			System.out.println(new Exception(("Non Existent Edge Exception: removeEdge(" + fromNode + "," + toNode + ")"), e));
		}
	}
	// TOSTRING
	public String toString()
	{	String the2String = "";
		for (int r=0 ; r < G.length ;++r )
		{
			for ( int c=0 ; c < G[r].length ; ++c )
				the2String += String.format("%3s",G[r][c] + " ");
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING
            
} //EOF
