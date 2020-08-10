import java.io.*;
import java.util.*;

///////////////////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////////////////////
class BSTreeP7<T>
{
	private BSTNode<T> root;
	private boolean addAttemptWasDupe=false;
	@SuppressWarnings("unchecked")
	public BSTreeP7( String infileName ) throws Exception
	{
		root=null;
		Scanner infile = new Scanner( new File( infileName ) );
		while ( infile.hasNext() )
			add( (T) infile.next() ); // THIS CAST RPODUCES THE WARNING
		infile.close();
	}

	// DUPES BOUNCE OFF & RETURN FALSE ELSE INCR COUNT & RETURN TRUE
	@SuppressWarnings("unchecked")
	public boolean add( T key )
	{	addAttemptWasDupe=false;
		root = addHelper( this.root, key );
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




	//////////////////////////////////////////////////////////////////////////////////////
	// # # # #   WRITE THE REMOVE METHOD AND ALL HELPERS / SUPPORTING METHODS   # # # # # 

	// return true only if it finds/removes the node
	public boolean remove( T key2remove ){ 
		ArrayList<T> keys = new ArrayList<T>();
		arrayAddInOrder(root, keys);
		if (keys.size() == 0)
			return false;
        if (!contains(root, key2remove))
			return false;
		return remove(keys, key2remove);
	}   
	// @SuppressWarnings("unchecked")
    // public boolean remove(BSTNode<T> root, T key){
	// 	Comparable keyT = (Comparable<T>)key;
	// 	if (root == null)
	// 		return false;
	// 	else if (keyT.compareTo(root.key) > 0)
	// 		return remove(root.right, key);
	// 	else if (keyT.compareTo(root.key) < 0)
	// 		return remove(root.left, key);
	// 	else {
	// 		if (root.right == null && root.left == null){
	// 			root = null;
	// 			return true;
	// 		}

	// 	}
	// 	return false;
	// }

    boolean remove(ArrayList<T> keys, T key){
		this.root = null;
		keys.remove(key);
		addKeysInBalancedOrder(keys, 0, keys.size() - 1, this);
		return true;
	}
	void addKeysInBalancedOrder(ArrayList<T> keys, int lo, int hi, BSTreeP7<T> balancedBST){
		if (keys.size() == 0)
			return;
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

    
    public void printLevelOrder(){
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
	
	@SuppressWarnings("unchecked")
	private boolean contains(BSTNode<T> root, T key){
		int comp = ((Comparable)key).compareTo( root.key );
		if (root.key.equals(key))
			return true;
		if (comp < 0)
			return (root.left == null) ? false : contains(root.left, key);
		if (comp > 0)
			return (root.right == null) ? false : contains(root.right, key);
		return false;
	}

    public void printInOrder(){	
        printInOrder( this.root );
		System.out.println();
	}
    private void printInOrder( BSTNode<T> root ) {  // LVR
		if (root == null)
			return;
		printInOrder(root.left);
		System.out.print(root.key + " ");
		printInOrder(root.right);
    }

    public int countNodes(){
		return countNodes( this.root );
	}
	private int countNodes( BSTNode<T> root ){
		if (root == null)
			return 0;
		return 1 + countNodes(root.left) + countNodes(root.right);
	}
    
    public int countLevels(){	
        return countLevels( root );
	}
	private int countLevels( BSTNode<T> root){	
        if (root == null)
            return 0;
        int left = 1 + countLevels(root.left);
        int right = 1 + countLevels(root.right);
        return Math.max(left, right);
    }

    public int[] calcLevelCounts(){	
        int levelCounts[] = new int[countLevels()];
		calcLevelCounts( root, levelCounts, 0 );
		return levelCounts;
	}
	private void calcLevelCounts( BSTNode<T> root, int levelCounts[], int level ){   
        if (root != null){
            levelCounts[level] += 1;
            calcLevelCounts(root.left, levelCounts, level + 1);
            calcLevelCounts(root.right, levelCounts, level + 1);
        }
	}
  
} // END BSTREEP7 CLASS
