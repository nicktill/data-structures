public class BSTesterP7
{
	public static void main( String[] args ) throws Exception
	{
		BSTreeP7<String> tree1 = new BSTreeP7<String>( args[0] );
		System.out.format( "\ntree1: loaded from %s contains %d nodes on %d levels:\n", args[0], tree1.countNodes(), tree1.countLevels() );
		System.out.print("IN ORDER tree1:    "); tree1.printInOrder();
		System.out.print("LEVEL ORDER tree1: "); tree1.printLevelOrder();
		int[] levelCounts = tree1.calcLevelCounts();
		System.out.println();
		for (int i = 0 ; i<levelCounts.length; ++i )
			System.out.format("level:%2d   %d\n",i,levelCounts[i] );
		
		
		// REMOVE SEVERAL NODES WHO HAVE 2 CHILDREN
		tree1.remove( "C" ); tree1.remove( "I" ); tree1.remove( "P" ); 	tree1.remove( "W" ); // ALL HAVE 2 CHILDREN
		
		System.out.format( "\ntree1: after removing C, I, P, W, contains %d nodes on %d levels:\n", tree1.countNodes(), tree1.countLevels() );
		System.out.print("IN ORDER tree1:    "); tree1.printInOrder();
		System.out.print("LEVEL ORDER tree1: "); tree1.printLevelOrder();	
		levelCounts = tree1.calcLevelCounts();
		System.out.println();
		for (int i = 0 ; i<levelCounts.length; ++i )
			System.out.format("level:%2d   %d\n",i,levelCounts[i] );
		
		
		// REMOVE SEVERAL lEAVES
		tree1.remove( "J" ); tree1.remove( "S" ); tree1.remove( "Z" ); // ALL ARE LEAVES
		
		System.out.format( "\ntree1: after removing J, S, Z, contains %d nodes on %d levels:\n", tree1.countNodes(), tree1.countLevels() );
		System.out.print("IN ORDER tree1:    "); tree1.printInOrder();
		System.out.print("LEVEL ORDER tree1: "); tree1.printLevelOrder();
		levelCounts = tree1.calcLevelCounts();
		System.out.println();
		for (int i = 0 ; i<levelCounts.length; ++i )
			System.out.format("level:%2d   %d\n",i,levelCounts[i] );

		// REMOVE SEVERAL NODES WHICH HAVE EXACTLY ONE CHILD
		tree1.remove( "D" ); tree1.remove( "K" ); tree1.remove( "R" ); tree1.remove( "Y" ); // THESE HAVE EXACTLY 1 CHILD

		System.out.format( "\ntree1: after removing D, K, R, Y, contains %d nodes on %d levels:\n", tree1.countNodes(), tree1.countLevels() );
		System.out.print("IN ORDER tree1:    "); tree1.printInOrder();
		System.out.print("LEVEL ORDER tree1: "); tree1.printLevelOrder();
		levelCounts = tree1.calcLevelCounts();
		System.out.println();
		for (int i = 0 ; i<levelCounts.length; ++i )
			System.out.format("level:%2d   %d\n",i,levelCounts[i] );

		System.out.println();
		System.out.println( "Now attempting remove of A..Z");
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for ( int i=0 ; i<alphabet.length() ; ++i )
		{
			if ( tree1.remove(alphabet.charAt(i)+"") )
			{
				System.out.format("\nremove(%c) successful: ", alphabet.charAt(i) ); 
				tree1.printLevelOrder();
			}
			else
			{
				System.out.format("\nremove(%c) failure:    ", alphabet.charAt(i) );
				tree1.printLevelOrder();
			}
		}
	}
}
