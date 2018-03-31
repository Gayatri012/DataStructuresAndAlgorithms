
public class BinaryTree<AnyType> {
	
    public BinaryTree( )
    {
        root = null;
    }	
	// Basic node stored in unbalanced binary search trees
    private static class Node<AnyType>
    {
            // Constructors
        Node( AnyType theElement )
        {
            this( theElement, null, null );
        }

	
	    Node( AnyType theElement, Node<AnyType> lt, Node<AnyType> rt )
	    {
	        element  = theElement;
	        left     = lt;
	        right    = rt;
	    }
	
	    AnyType element;      // The data in the node
	    Node<AnyType> left;   // Left child
	    Node<AnyType> right;  // Right child
	}


  /** The tree root. */
	private Node<AnyType> root;

}
