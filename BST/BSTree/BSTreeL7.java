import java.io.*;
import java.util.*;

///////////////////////////////////////////////////////////////////////////////
class BSTNode<T>
{	
	T key;
	BSTNode<T> left,right;
	BSTNode( T key, BSTNode<T> left, BSTNode<T> right )
	{	this.key = key;
		this.left = left;
		this.right = right;
	}
}

///////////////////////////////////////////////////////////////////////////////
class Queue<T>
{	
	LinkedList<BSTNode<T>> queue;
	Queue() { 
		queue =  new LinkedList<BSTNode<T>>(); 
	}
	boolean empty() { 
		return queue.size() == 0; 
	}
	void enqueue( BSTNode<T>  node ) { 
		queue.addLast( node ); 
	}
	BSTNode<T>  dequeue() { 
		return queue.removeFirst(); 
	}
	// DEQUEUE THROWS NO SUCH ELEMENT EXCEPTION IF Q EMPTY
}

////////////////////////////////////////////////////////////////////////////////
class BSTreeL7<T>
{
	private BSTNode<T> root;
	private int nodeCount;
	private boolean addAttemptWasDupe=false;
	@SuppressWarnings("unchecked")
	public BSTreeL7( String infileName ) throws Exception
	{
		nodeCount=0;
		root=null;
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );
		while ( infile.ready() )
			add( (T) infile.readLine() ); // THIS CAST RPODUCES THE WARNING
		infile.close();
	}

	public int size()
	{
		return nodeCount; // LOCAL VAR KEEPING COUNT
	}

	// DUPES BOUNCE OFF & RETURN FALSE ELSE INCR COUNT & RETURN TRUE
	@SuppressWarnings("unchecked")
	public boolean add( T key )
	{	addAttemptWasDupe=false;
		root = addHelper( this.root, key );
		if (!addAttemptWasDupe) ++nodeCount;
		return !addAttemptWasDupe;
	}
	@SuppressWarnings("unchecked")
	private BSTNode<T> addHelper( BSTNode<T> root, T key )
	{
		if (root == null) return new BSTNode<T>(key,null,null);
		int comp = ((Comparable)key).compareTo( root.key );
		if ( comp == 0 )
			{ addAttemptWasDupe=true; return root; }
		else if (comp < 0)
			root.left = addHelper( root.left, key );
		else
			root.right = addHelper( root.right, key );

		return root;
    } // END addHelper


	// ITS A SEARCH - ONE OF FEW OPS YOU CAN DO ITERATIVELY
	public boolean contains( T key )
	{
		return contains( this.root, key  );
	}
	@SuppressWarnings("unchecked")
	private boolean contains( BSTNode<T> root, T key  )
	{
		if (root == null) return false;
		int comp = ((Comparable)key).compareTo( root.key );
		if ( comp == 0 ) return true;
		if (comp < 0) return contains(root.left, key );
		return contains(root.right, key );
	}


	// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
	// DO NOT MODIFY ANYTHING ABOVE THIS LINE.  YOU FILL IN ALL THE CODE BELOW
	// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

	public int countNodes()
	{
		return countNodes( this.root );
	}
	private int countNodes( BSTNode<T> root )
	{
		if (root == null)
			return 0;
		return 1 + countNodes(root.left) + countNodes(root.right);
	}

	public void printInOrder()
	{	printInOrder( this.root );
		System.out.println();
	}
	private void printInOrder( BSTNode<T> root ) // LVR
	{
		if (root == null)
			return;
		printInOrder(root.left);
		System.out.print(root.key + " ");
		printInOrder(root.right);
	}

	public void printPreOrder()
	{	printPreOrder( this.root );
		System.out.println();
	}
	private void printPreOrder( BSTNode<T> root ) // VLR
	{
		if (root == null)
			return;
		System.out.print(root.key + " ");
		printPreOrder(root.left);
		printPreOrder(root.right);
	}

	public void printPostOrder()
	{	printPostOrder( this.root );
		System.out.println();
	}
	private void printPostOrder( BSTNode<T> root ) // LRV
	{
		if (root == null)
			return;
		printPostOrder(root.left);
		printPostOrder(root.right);
		System.out.print(root.key + " ");
	}

	public void printLevelOrder()
	{
		if (root == null)
			return;
		Queue<T> queue = new Queue<T>();
		queue.enqueue(this.root);
		while (!queue.empty()){
			BSTNode<T> curr = queue.dequeue();
			System.out.print(curr.key + " ");
			if (curr.left != null)
				queue.enqueue(curr.left);
			if (curr.right != null)
				queue.enqueue(curr.right);				
		}
	}
}
