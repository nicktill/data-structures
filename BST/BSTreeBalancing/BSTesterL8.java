public class BSTesterL8
{
	public static void main( String[] args ) throws Exception
	{
		BSTreeL8<String> tree1 = new BSTreeL8<String>( args[0] );
		System.out.format( "\ntree1: loaded from %s contains %d nodes on %d levels:\n", args[0], tree1.countNodes(), tree1.countLevels() );
		System.out.print("PRE ORDER tree1:   "); tree1.printPreOrder();
		System.out.print("IN ORDER tree1:    "); tree1.printInOrder();
		System.out.print("POST ORDER tree1:  "); tree1.printPostOrder();
		System.out.print("LEVEL ORDER tree1: "); tree1.printLevelOrder();
		int[] levelCounts = tree1.calcLevelCounts();
		for (int i = 0 ; i<levelCounts.length; ++i )
			System.out.format("level:%2d   %d\n",i,levelCounts[i] );

		BSTreeL8<String> tree2 = tree1.makeBalancedCopyOf();  // TREE1 RETURNS BALANCED COPY OF SELF BUT DOES NOT CHANGE SELF
		System.out.format( "tree2: a balanced copy of tree1 contains %d nodes on %d levels:\n", tree2.countNodes(), tree2.countLevels()  );
		System.out.print("PRE ORDER tree2:   "); tree2.printPreOrder();
		System.out.print("IN ORDER tree2:    "); tree2.printInOrder();
		System.out.print("POST ORDER tree2:  "); tree2.printPostOrder();
		System.out.print("LEVEL ORDER tree2: "); tree2.printLevelOrder();
		System.out.println( "tree2.equals(tree1)=" + tree1.equals(tree2) ); // SHOULD PRINT false
		levelCounts = tree2.calcLevelCounts();
		for (int i = 0 ; i<levelCounts.length; ++i )
			System.out.format("level:%2d   %d\n",i,levelCounts[i] );

		BSTreeL8<String> tree3 = new BSTreeL8<String>( tree2 );  // SHOULD PRODUCE AN EXACT DEEP COPY
		System.out.format( "\ntree3: a constructor copy (identical) of tree2 contains %d nodes on %d levels:\n", tree3.countNodes(), tree3.countLevels()  );	
		System.out.print("PRE ORDER tree3:   "); tree3.printPreOrder();
		System.out.print("IN ORDER tree3:    "); tree3.printInOrder();
		System.out.print("POST ORDER tree3:  "); tree3.printPostOrder();
		System.out.print("LEVEL ORDER tree3: "); tree3.printLevelOrder();
		System.out.println( "tree3.equals(tree2)=" + tree3.equals(tree2) ); // SHOULD PRINT true
		levelCounts = tree3.calcLevelCounts();
		for (int i = 0 ; i<levelCounts.length; ++i )
			System.out.format("level:%2d   %d\n",i,levelCounts[i] );
	}
}
