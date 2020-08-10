import java.io.*;
import java.util.*;

public class PrettyPrintTester
{
	public static void main( String args[] ) throws Exception
	{
		// THIS TREE WIL NOT BE BALANCED (UNLESS THE KEYS HAPPEN TO BE BE IN IDEAL ORDER)
		BSTreePP bstOriginal = new BSTreePP( args[0] ); // JUST ADDS KEYS IN ORDER GOTTEN FROM FILE.
		bstOriginal.prettyPrint();
		System.out.println("\n\n"); // a couple blanks lines to separate the next version of the tree
		
		// THIS TREE WILL BE GUARANTEED TO BE BALANCED BUT CONTAIN ALL SAME KEYS AS THE ORIGINAL
		BSTreePP bstBalanced= bstOriginal.makeBalancedCopyOf();
		bstBalanced.prettyPrint();	
	}// END MAIN
} // END