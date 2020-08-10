import java.io.*;
import java.util.*;

public class LinkedListTester
{
	public static void main( String args[] ) throws Exception
	{
		LinkedList<String> list1 = new LinkedList<String>( args[0], false );  // false = INSERTATTAIL CALLED ON EACH T READ FROM FILE
		System.out.format("list1 %s size %d unordered %s\n",args[0],list1.size(),list1 ); // invokes toString

		 list1.remove( "charlie" ); list1.remove("echo"); list1.remove("zebra"); // HEAD, TAIL, NOWHERE
		System.out.format("list1 (after remove charlie, echo & zebra) %s\n",list1 ); // invokes toString
		
		LinkedList<String> list2 = new LinkedList<String>( args[0], true );  // true = INSERTINORDER CALLED ON EACH T READ FROM FILE
		System.out.format("list2 %s size %d ordered %s\n",args[0],list2.size(),list2 ); // invokes toString

		LinkedList<String> list3 = new LinkedList<String>( args[1], true );  // true = INSERTINORDER CALLED ON EACH T READ FROM FILE
		System.out.format("list3 %s size %d ordered %s\n",args[1],list3.size(),list3 ); // invokes toString	
		
		// MUST RETURN ALL SET OP LISTS IN SORTED ORDER NO DUPES 
		LinkedList<String> unionList = list2.union(list3);
		System.out.println("list2.union(list3) " + unionList); 	
		
		LinkedList<String> interList = list2.inter(list3);	
		System.out.println("list2.inter(list3) " + interList); 
		
		LinkedList<String> diffList = list2.diff(list3);		
		System.out.println("list2.diff(list3)  " + diffList); 
		
		LinkedList<String> xorList = list2.xor(list3);				
		System.out.println("list2.xor(list3)   " + xorList); 
		
		// ECHO LISTS 2 & 3  TO PROVE THEY WERE NOT MODIFIED
		
		System.out.println();
		System.out.println( "list2 after set ops: " + list2 );
		System.out.println( "list3 after set ops: " + list3 );
		
		// DESTROY LIST2 BY REPEATEDLY CALLING REMOVE AT TAIL UNTIL EMPTY
		while ( ! list2.empty() ) list2.removeAtTail();

		// DESTROY LIST3 BY REPEATEDLY CALLING REMOVE AT FRONT
		while ( ! list3.empty() ) list3.removeAtFront();
		
	// ECHO LISTS 2 & 3  TO PROVE THEY WERE DESTROYED
		
		System.out.println();
		System.out.println( "list2 after all nodes removedAtTail: " + list2 );
		System.out.println( "list3 after all nodes removedAtFront: " + list3 );
		
		
	} // END MAIN
} // EOF