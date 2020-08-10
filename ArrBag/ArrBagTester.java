import java.io.*;
import java.util.*;

public class ArrBagTester
{
     public static void main(String args[]) throws Exception
	{
	if ( args.length < 1 ) die( "usage: java ArrBagTester <filename> <filename> <filename> <filename>\n" );
		// INSTANCE AN OBECT OF OUR GENERIC CLASS AND SPECIFY IT AS A CONTAINER OF TYPE ARRAY OF STRING

		ArrBag<String> bagStr1 = new ArrBag<String>();
		loadFromFile( bagStr1, args[0] );
		System.out.println( "\nbagStr1 loaded from " + args[0] + ": "  + bagStr1 );

		ArrBag<String> bagStr2 = new ArrBag<String>();
		loadFromFile( bagStr2, args[1] );
		System.out.println( "bagStr2 loaded from " + args[1] + ": "  + bagStr2 );

		System.out.println();

		ArrBag<String> unionStr = bagStr1.union( bagStr2 );
		System.out.println( "union: " + unionStr  );

		ArrBag<String> intersectStr = bagStr1.intersection( bagStr2 );
		System.out.println( "intersection: " + intersectStr  );

		ArrBag<String> diffStr = bagStr1.difference( bagStr2 );	// BAG1 - BA2.   ORDER MATTTERS
		System.out.println( "difference: " + diffStr  );

		ArrBag<String> xorStr = bagStr1.xor( bagStr2 );
		System.out.println( "xor: " + xorStr  );

		// ECHO BAG 1 & 2 TO PROVE THEY WERE NOT MODIFIED

		System.out.println();
		System.out.println( "bagStr1 after set ops: " + bagStr1 );
		System.out.println( "bagStr2 after set ops: " + bagStr2 );

		// CLEAR BAG 1 & 2 THEN PRINT ON ELAST TIME. SHOULD BE EMPTY!

		System.out.println(); bagStr1.clear() ; bagStr2.clear();
		System.out.println( "bagStr1 after clear: " + bagStr1 );
		System.out.println( "bagStr2 after clear: " + bagStr2 );
	}

	static void die( String errMsg )
	{
		System.out.println( "\nFATAL ERROR: " + errMsg );
		System.exit(0);
	}

	static void loadFromFile( ArrBag<String> bag, String filename ) throws Exception
	{

		Scanner infile = new Scanner( new File( filename) );
		while( infile.hasNext() )
			bag.add( infile.next() );
		infile.close();
	}
}
