import java.io.*;
import java.util.*;

//////////////////////////////////////////////////////////////////////////////////////
class BSTNode<T>
{	T key;
	BSTNode<T> left,right;
	BSTNode( T key, BSTNode<T> left, BSTNode<T> right )
	{	this.key = key;
		this.left = left;
		this.right = right;
	}
}

///////////////////////////////////////////////////////////////////////////////////////
class Queue<T>
{	LinkedList<BSTNode<T>> queue;
	Queue() { queue =  new LinkedList<BSTNode<T>>(); }
	boolean empty() { return queue.size() == 0; }
	void enqueue( BSTNode<T>  node ) { queue.addLast( node ); }
	BSTNode<T>  dequeue() { return queue.removeFirst(); }
	// THROWS NO SUCH ELEMENT EXCEPTION IF Q EMPTY
}

///////////////////////////////////////////////////////////////////////////////////////
class BSTreeL8<T>
{
	private BSTNode<T> root;
	private int nodeCount;
	private boolean addAttemptWasDupe=false;

	// DEFAULT C'TOR
	public BSTreeL8()
	{
		nodeCount=0;
		root=null;
	}

	// INPUT FILE C'TOR
	@SuppressWarnings("unchecked")
	public BSTreeL8( String infileName ) throws Exception
	{
		nodeCount=0;
		root=null;
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );
		while ( infile.ready() )
			add( (T) infile.readLine() ); // THIS CAST RPODUCES THE WARNING
		infile.close();
	}

	// COPY C'TOR
	public BSTreeL8(  BSTreeL8<T> other )
	{
		nodeCount=0;
		root=null;
		// DO A PRE ORDER TRAVERSAL OF OTHER TREE WHERE VISITATION
		// OPERATION IS TO ADD EACH NODE FROM OTHER TREE INTO THIS TREE

		addNodesInPrOrder( other.root );
	}
	private void addNodesInPrOrder( BSTNode<T> otherBSTNode )
	{
		if ( otherBSTNode == null ) return;
		this.add( otherBSTNode.key );
		this.addNodesInPrOrder( otherBSTNode.left );
		this.addNodesInPrOrder( otherBSTNode.right );
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

	public int size()
	{
		return nodeCount; // LOCAL VAR KEEPING COUNT
	}

	public int countNodes() // DYNAMIC COUNT ON THE FLY TRAVERSES TREE
	{
		return countNodes( this.root );
	}
	private int countNodes( BSTNode<T> root )
	{
		if (root==null) return 0;
		return 1 + countNodes( root.left ) + countNodes( root.right );
	}

	// INORDER TRAVERSAL REQUIRES RECURSION
	public void printInOrder()
	{
		printInOrder( this.root );
		System.out.println();
	}
	private void printInOrder( BSTNode<T> root )
	{
		if (root == null) return;
		printInOrder( root.left );
		System.out.print( root.key + " " );
		printInOrder( root.right );
	}

	// PRE ORDER TRAVERSAL REQUIRES RECURSION
	public void printPreOrder()
	{	printPreOrder( this.root );
		System.out.println();
	}
	private void printPreOrder( BSTNode<T> root )
	{	if (root == null) return;
		System.out.print( root.key + " " );
		printPreOrder( root.left );
		printPreOrder( root.right );
	}

	// POST ORDER TRAVERSAL REQUIRES RECURSION
	public void printPostOrder()
	{	printPostOrder( this.root );
		System.out.println();
	}
	private void printPostOrder( BSTNode<T> root )
	{	if (root == null) return;
		printPostOrder( root.left );
		printPostOrder( root.right );
		System.out.print( root.key + " " );
	}

	public void printLevelOrder()
	{	if (this.root == null) return;
		Queue<T> q = new Queue<T>();
		q.enqueue( this.root ); // this. just for emphasis/clarity
		while ( !q.empty() )
		{	BSTNode<T> n = q.dequeue();
			System.out.print( n.key + " " );
			if ( n.left  != null ) q.enqueue( n.left );
			if ( n.right != null ) q.enqueue( n.right );
		}
		System.out.println();
	}

  public int countLevels()
  {
    return countLevels( root ); 
  }
  private int countLevels( BSTNode root)
  {
    if (root==null) return 0;
    return 1 + Math.max( countLevels(root.left), countLevels(root.right) );
  }
  
  public int[] calcLevelCounts()
  {
    int levelCounts[] = new int[countLevels()];
    calcLevelCounts( root, levelCounts, 0 );
    return levelCounts;
  }
  private void calcLevelCounts( BSTNode root, int levelCounts[], int level )
  {
    if (root==null)return;
    ++levelCounts[level];
    calcLevelCounts( root.left, levelCounts, level+1 );
    calcLevelCounts( root.right, levelCounts, level+1 );
  }

	// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
	// 				DO NOT MODIFY ANYTHING ABOVE THIS LINE.  YOU FILL IN ALL THE CODE BELOW

	// SIMILAR TO COPY CONSTRUCTOR BUT PRODUCES BALANCED COPY OF OTHER BST
	public BSTreeL8<T> makeBalancedCopyOf( )
	{
		// define an ArrayList<T>
		ArrayList<T> keys = new ArrayList<T>();
		// traverse this tree IN-ORDER adding A B C D E F G in that order to ArrayList
		// you'll need a recursive in order helper. The visitation operation will be
        // to add the key of each root to the ArrayList
        arrayAddInOrder(root, keys);

		// define a new BSTreeL8<T>
		BSTreeL8<T> balancedBST = new BSTreeL8<T>();

		// now call a recursive method (you write it below) that looks a lot like a binary
		// search that  visits the elememnts of the ArrayList likea bserach would and
		// the visitation operation is to add that ArrayList element to the BST in an
        // ordering that will produce a balanced BST
        addKeysInBalancedOrder(keys, 0, countNodes() - 1, balancedBST);
        

		return balancedBST;   // return that balancedBST;
	}

	// suggested signature for method that imitates binary search thru keys array
	// and inserts keys into the new BST instead of doing comparisons etc
    //void addKeysInBalancedOrder ( ArrayList<T> keys, int lo, int hi, BSTreeL8<T> balancedBST )  // V L R

    void addKeysInBalancedOrder(ArrayList<T> keys, int lo, int hi, BSTreeL8<T> balancedBST){
        int mid = (hi + lo)/2;
        balancedBST.add(keys.get(mid));
        if (lo > hi)
            return;
        addKeysInBalancedOrder(keys, lo, mid - 1, balancedBST);
        addKeysInBalancedOrder(keys, mid + 1, hi, balancedBST);        
    }
    
    void arrayAddInOrder(BSTNode<T> root, ArrayList<T> keys){
        if (root == null) 
            return;
		arrayAddInOrder( root.left, keys);
		keys.add(root.key);
		arrayAddInOrder(root.right, keys);
    }

    public boolean equals( BSTreeL8<T> other )
	{	
        return equals(this.root, other.root);
	}
	// TRUE ONLY IF BOTH TREES IDENTICAL IN EVERY WAY INCLUDING SHAPE
	private boolean equals( BSTNode<T> thisRoot, BSTNode<T> otherRoot )
	{	
        if (thisRoot == null || otherRoot == null)
            return false;
        else if (!thisRoot.key.equals(otherRoot.key))
            return false;
        equals(thisRoot.left, otherRoot.left);
        equals(thisRoot.right, otherRoot.right);
        return true;
	}
} // END BSTreeL8 CLASS


