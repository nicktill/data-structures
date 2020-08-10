/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph
{
	//private final int NO_EDGE = -1;
	private Edge[] G;

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

		int dimension = graphFile.nextInt();   	
		G = new Edge[dimension]; 	
		numEdges=0;

		while ( graphFile.hasNextInt() )
		{
            int r = graphFile.nextInt();
            int c = graphFile.nextInt();
            int w = graphFile.nextInt();
            addEdge(r, c, w);
		}
		// for (int node = 0 ; node < G.length ; ++node )
		// {
		// 	System.out.format( "DEBUG:: in (%d)=%d  ",node,inDegree(node) );
		// 	System.out.format( "out(%d)=%d  ",node,outDegree(node) );
		// 	System.out.format( "deg(%d)=%d\n",node,degree(node) );
		// }

	} // END readGraphFile

	private void addEdge( int r, int c, int w )
	{
		Edge head = G[r];
		G[r] = new Edge(c, w, head);
		numEdges++;
	}
	
    private boolean hasEdge(int fromNode, int toNode)
    {
		if (numEdges == 0)
			return false;
		for (Edge curr = G[fromNode]; curr != null; curr = curr.next)
			if (curr.dest == toNode)
				return true;
		return false;
	}
	// IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
	// NODE IS THE ROW COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
	private int inDegree(int node)
	{
		int deg = 0;
		for (int i = 0; i < G.length; i++)
			for (Edge curr = G[i]; curr != null; curr = curr.next)
				if (curr.dest == node)
					deg++;
		return deg;
	}

	// OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
	// NODE IS THE ROW #. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
	private int outDegree(int node)
	{
		int deg = 0;
		for (Edge curr = G[node]; curr != null; curr = curr.next)
			deg++;
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
	
	public void removeEdge(int fromNode, int toNode)
	{
		try {
			if (G[fromNode].dest == toNode) {
				G[fromNode] = G[fromNode].next;
				return;
			}
			Edge curr = G[fromNode];
			Edge prev = null;
			while (curr != null && !(curr.dest == toNode)) {
				prev = curr;
				curr = curr.next;
			}
			prev.next = curr.next;
		}
		catch (Exception e){
			System.out.println(new Exception(("Non Existent Edge Exception: removeEdge(" + fromNode + "," + toNode + ")"), e));
		}
	}
	// TOSTRING
	public String toString()
	{	
		String the2String = "";
		for (int r=0 ; r < G.length ;++r )
		{
			the2String+=(r + ":");
			for (Edge curr = G[r]; curr != null; curr = curr.next)
				the2String+=(" -> " + "[" + curr.dest + "," + curr.weight + "]");
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING          
} //EOF

class Edge
{
	int dest;
	int weight;
	Edge next;

	public Edge (int d, int w, Edge n) {
		dest = d;
		weight = w;
		next = n;
	}

}
