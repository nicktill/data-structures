public class BSTesterL7
{
	public static void main( String[] args ) throws Exception
	{
		BSTreeL7<String> tree1 = new BSTreeL7<String>( args[0] );
		System.out.format( "\ntree1 loaded from %s contains %d nodes:\n", args[0], tree1.countNodes() ); 
		System.out.println("INORDER print of tree1");
		tree1.printInOrder();
		System.out.println("PRE ORDER print of tree1");
		tree1.printPreOrder();
		System.out.println("POST ORDER print of tree1");
		tree1.printPostOrder();
		System.out.println("LEVEL ORDER print of tree1");
		tree1.printLevelOrder();		
	}
}
