public class BSTesterP6
{
	public static void main( String[] args ) throws Exception
	{
		BSTreeP6<String> tree1 = new BSTreeP6<String>( args[0] );
		System.out.format( "\ntree1 loaded from %s contains %d nodes\n", args[0], tree1.countNodes() ); 
		System.out.format("NUM OF LEAVES=%d\n",tree1.countLeaves() );
		System.out.format("NUM OF INTERIOR NODES=%d\n",tree1.countInteriorNodes() );
		int[] levelCounts = tree1.calcLevelCounts();
		System.out.format("NUM OF LEVELS=%d\n",levelCounts.length );
		for (int i = 0 ; i<levelCounts.length; ++i )
			System.out.format("level:%2d   %d\n",i,levelCounts[i] );

		BSTreeP6<String> tree2 = new BSTreeP6<String>( tree1 );
		System.out.format( "\ntree2 copy constructed from tree1 contains %d nodes\n", tree2.countNodes() ); 
		System.out.format("NUM OF LEAVES=%d\n",tree2.countLeaves() );
		System.out.format("NUM OF INTERIOR NODES=%d\n",tree2.countInteriorNodes() );
		levelCounts = tree2.calcLevelCounts();
		System.out.format("NUM OF LEVELS=%d\n",levelCounts.length );
		for (int i = 0 ; i<levelCounts.length; ++i )
			System.out.format("level:%2d   %d\n",i,levelCounts[i] );
	}
}
