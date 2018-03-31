import java.util.ArrayList;


// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.to
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    
    /********* Project 2 begins here **********/
    /**
     * 
     * @return count of nodes in tree
     */
    public int nodeCount() {
    	if (root != null)
    		/*return nodeCount(root) - 1;	//Number of nodes in binary tree is one less than the total count of nulls of nodes */    	
    		return nodeCount(root);
    	else
    		return 0;	//If no tree exists, return size as 0
    }
    
    /**
     * 
     * @param t Every node of the tree
     * @return count of nodes in tree
     */
    private int nodeCount(BinaryNode<AnyType> t) {
    	/*if (t==null)
    		return 1;
    	return (nodeCount(t.left) + nodeCount(t.right));	//Counting the nulls of nodes */    
    	if (t==null)
    		return 0;
    	else
    		return (1 + nodeCount(t.left) + nodeCount(t.right));		
    }
   
    /**
     * 
     * @return true if the tree is full, i.e., tree has every node as either leaf or a parent with two children. Else return false
     */
    public boolean isFull(){
    	if (root != null)
    		return isFull(root);
    	else {
    		System.out.println("No tree exists");
    		return false;	//If no tree exists, return false
    	}
    }
    
    /**
     * 
     * @param t Node of the tree
     * @return true if the tree is full, i.e., tree has every node as either leaf or a parent with two children. Else return false
     */
    private boolean isFull(BinaryNode<AnyType> t) {
    	if (t != null) 	
    		if (!((t.left != null && t.right != null) || (t.left == null && t.right == null)))
    			return false;
    		else
    			return (isFull(t.left) && isFull(t.right));
    	else
    		return true;	
    }
    
    /**
     * 
     * @param t2 The second tree structure to be compared with the tree that calls this function
     * @return true if the structure of current tree matches the tree structure of t2
     */
    public boolean compareStructure(BinarySearchTree<AnyType> t2) {
    	if (this.root != null && t2.root != null)
    		return compareStructure(this.root, t2.root);
    	else {
    	System.out.println("Tree doesn't exist");
    		return false;
    	}
    }
    
    /**
     * 
     * @param t1 Node of first tree to be compared for its structure
     * @param t2 Node of second tree to be compared for its structure
     * @return true if the structure of t1 matches the structure of t2
     */
    private boolean compareStructure(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
    	if (t1 != null && t2 != null)
    		if ((t1.left != null && t2.left == null) || (t1.right != null && t2.right == null) || (t1.left == null && t2.left != null)  || (t1.right != null && t2.right == null)) 
    			return false;
    		else 
    			return (compareStructure(t1.left, t2.left) && compareStructure(t1.right, t2.right));
    	else if ((t1 == null && t2 != null) || (t1 != null && t2 == null))
    		return false;
    	else
    		return true;
    }
    
    /**
     * 
     * @param t2 The second tree to be compared with the tree that calls this function
     * @return true if current tree and tree t2 are identical
     */
    public boolean equals(BinarySearchTree<AnyType> t2) {
    	if (this.root != null && t2.root != null)
    		return equals(this.root, t2.root);
    	else {
    		System.out.println("Tree doesn't exist");
    		return false;
    	}	
    }
    /**
     * 
     * @param t1 Node of first tree to be compared
     * @param t2 Node of second tree to be compared
     * @return true if both nodes are identical. Call recursively to conclude if their corresponding trees are identical
     */
    private boolean equals(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
    	if (t1 != null && t2 != null) {
    		if ((t1.left != null && t2.left != null && t1.left.element.compareTo(t2.left.element) != 0) || (t1.right != null && t2.right != null && t1.right.element.compareTo(t2.right.element) != 0)) {
    			return false;
    		}
    		else
    			return(equals(t1.left, t2.left) && equals(t1.right, t2.right));
    	}
    	else if ((t1 != null && t2 == null) || (t1 == null && t2 != null)) {
    		return false;
    	}
    	else
    		return true;
    }
    
    /**
     * 
     * @return the new tree which is a copy of the tree that calls this function
     */
    public BinarySearchTree<AnyType> copy() {
    	BinarySearchTree<AnyType> newTree = new BinarySearchTree<AnyType>();
    	if (this.root != null) {
    		newTree.root = new BinaryNode<AnyType>(this.root.element, null, null);
    		newTree.root = copy(this.root, newTree.root);
    		//newTree.root = this.root; //This copies the tree structure, but more is expected in the project
    	}
    	else {
    		System.out.println("Tree doesn't exist");
    	}
    	return newTree;
    }
    
    /**
     * 
     * @param t1 Node of the first tree from which new tree is to be copied
     * @param t2 Node of second tree to which first tree is copied to
     * @return Root Node of the copied tree 
     */
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
    	if (t1 != null && t1.left != null) {
    		t2.left = new BinaryNode<AnyType>(t1.left.element, null, null);
    		copy(t1.left,t2.left);
    	}
    	if (t1 != null && t1.right != null) {
    		t2.right = new BinaryNode<AnyType>(t1.right.element, null, null);
    		copy(t1.right,t2.right);
    	}
    	return t2;
    }
    
    /**
     * 
     * @return Mirror tree of the tree that called this function
     */
    public BinarySearchTree<AnyType> mirror() {
    	//TODO: Make this as the BinaryTree instead of BinarySearchTree
    	BinarySearchTree<AnyType> newMirrorTree = new BinarySearchTree<AnyType>();
    	if (this.root != null) {
    		newMirrorTree.root = new BinaryNode<AnyType>(this.root.element, null, null);
    		newMirrorTree.root = mirror(this.root, newMirrorTree.root);
    	}
    	
    	return newMirrorTree;
    }
    
    /**
     * 
     * @param t1 Node whose tree structure is to be mirrored
     * @param t2 Node to which the tree structure is to be mirrored
     * @return Root node of the new mirrored tree
     */
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
    	if (t1 != null && t1.left != null) {
    		t2.right = new BinaryNode<AnyType>(t1.left.element, null, null);
    		mirror(t1.left, t2.right);
    	}
    	if (t1 != null && t1.right != null) {
    		t2.left = new BinaryNode<AnyType>(t1.right.element, null, null);
    		mirror(t1.right, t2.left);
    	}
    	
    	return t2;
    }
    
    /**
     * 
     * @param t2 Tree which is to be compared for mirror image
     * @return true if the tree is a mirror of the tree that calls this function
     */
    public boolean isMirror(BinarySearchTree<AnyType> t2) {
    	if(this.root != null && t2.root != null && this.root.element == t2.root.element)
    		return isMirror(this.root, t2.root);
    	else
    		return false;
    }
    
    /**
     * 
     * @param t1 Node of the first tree which is to be compared for mirror of the tree
     * @param t2 Node of the second tree which is to be compared for mirror of the other tree
     * @return true if both nodes are mirror of each other
     */
    private boolean isMirror(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
    	if (t1 != null && t2 != null) {
    		if ((t1.left != null && t2.right != null && t1.left.element.compareTo(t2.right.element) == 0) || (t1.right != null && t2.left != null && t1.right.element.compareTo(t2.left.element) == 0))
    			return (isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left));
    		else if ((t1.left == null && t2.right == null) || (t1.right == null && t2.left == null))
    			return true;
    		else
    			return false;
    	}
    	else if ((t1 != null && t2 == null) || (t1 == null && t2 != null))
    		return false;
    	else
    		return true;	//Both t1 and t2 are null
    }
    
    /**
     * Single right rotation in a binary search tree
     * @param x The element to be rotated right
     * @throws IllegalArgumentException if the left child of the node with x is null
     */
    public BinarySearchTree<AnyType> rotateRight(AnyType x) {
    	BinarySearchTree<AnyType> rightRotatedTree = new BinarySearchTree<AnyType>();
    	BinarySearchTree<AnyType> originalTree = new BinarySearchTree<AnyType>();
    	originalTree.root = new BinaryNode<AnyType>(this.root.element, this.root.left, this.root.right);
    	if (originalTree.root != null && originalTree.contains(x)) {
	    	if (x == originalTree.root.element) {
	    		rightRotatedTree.root = rotateRight(originalTree.root,x,true);
	    		originalTree.root = new BinaryNode<AnyType>(rightRotatedTree.root.element, rightRotatedTree.root.left, rightRotatedTree.root.right);
	    	}
	    	else {
	    		rightRotatedTree.root = rotateRight(originalTree.root, x, false);
	    		originalTree.root = new BinaryNode<AnyType>(rightRotatedTree.root.element, rightRotatedTree.root.left, rightRotatedTree.root.right);
	    	}
	    }
    	else
    		System.out.println("Either tree doesn't exist or " + x + " is not an element of the tree");
    	
    	return rightRotatedTree;
    }
    
    /**
     * Single right rotation in a binary search tree means to put a node down to the right of left child. Thus its left child replaces it
     * and the left child's right child becomes the left child of the pushed down node 
     * @param t Node which is the parent of the element to be rotated right, in case the element is present in the tree
     * @param x The element to be rotated right
     * @throws  IllegalArgumentException if the left child of the node with element x is null
     */
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> t, AnyType x, boolean isRoot) {
    	boolean isRotated = false;
    	if (isRoot) {
    		//Single rotation at the root
    		if (t.left != null) {
    			if (t.left.right != null) {
    				BinaryNode<AnyType> temp = new BinaryNode<AnyType>(t.left.right.element, t.left.right.left, t.left.right.right);
					BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.element, temp, t.right); //new subtree with the element to be rotated at the top and it's left child's right child as the left child of x
					
					t.left.right = temp2;	//Assign the new sub tree as the right child of x's left child 
					t = t.left;
					isRotated = true;
    			}
    			else {
    				BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.element, null, t.right); //new subtree with the element to be rotated at the top and it's left child's right child as the left child of x
					t.left.right = temp2;	//Assign the new sub tree as the right child of x's left child 
					t = t.left;
    			}
    		}
    		else
    			throw new IllegalArgumentException("Right rotation can't be performed. The left child of " + x + " can't be null for right rotation");
    		
    	}
    	else if (t != null) {
    		if (t.left != null && t.left.element.compareTo(x) == 0) {
    			if (t.left.left != null) {	//Left child of the element to be swapped must not be null
    				if (t.left.left.right != null) {
    					BinaryNode<AnyType> temp = new BinaryNode<AnyType>(t.left.left.right.element, t.left.left.right.left, t.left.left.right.right);
    					BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.left.element, temp, t.left.right); //new subtree with the element to be rotated at the top and it's left child's right child as the left child of x
    					
    					t.left.left.right = temp2;	//Assign the new sub tree as the right child of x's left child 
    					t.left = t.left.left;	//Parent of x assigned to x's left child
    					
    					isRotated = true;
    				}
    				else {
    					BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.left.element, null, t.left.right);
    					
    					t.left.left.right = temp2;	
    					t.left = t.left.left;		
    					
    					isRotated = true;
    				}
    			}
    			else
    				throw new IllegalArgumentException("Right rotation can't be performed. The left child of " + x + " can't be null for right rotation");
    		}
    		else if (t.right != null && t.right.element.compareTo(x) == 0) {
    			if (t.right.left != null) {
    				if (t.right.left.right != null) {
    					BinaryNode<AnyType> temp = new BinaryNode<AnyType>(t.right.left.right.element, t.right.left.right.left, t.right.left.right.right);
    					BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.right.element, temp, t.right.right);
    					
    					t.right.left.right = temp2;
    					t.right = t.right.left;
    					
    					isRotated = true;
    				}
    				else {
    					BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.right.element, null, t.right.right);
    					t.right.left.right = temp2; 
    					t.right = t.right.left;
    					
    					isRotated = true;
    				}
    			}
    			else
    				throw new IllegalArgumentException("Right rotation can't be performed. The left child of " + x + " can't be null for right rotation");
    		}
    	}
    	if (!isRotated && t.left != null)
    		t.left = rotateRight(t.left, x, false);
    	if (!isRotated && t.right != null)
    		t.right = rotateRight(t.right, x, false);
    	
    	return t;
    }
    
    /**
     * Single left rotation in a binary search tree
     * @param x The element to be rotated left
     * @throws IllegalArgumentException if the right child of the node with element x is null
     */
    public BinarySearchTree<AnyType> rotateLeft(AnyType x) {
    	BinarySearchTree<AnyType> leftRotatedTree = new BinarySearchTree<AnyType>();
    	BinarySearchTree<AnyType> originalTree = new BinarySearchTree<AnyType>();
    	originalTree.root = new BinaryNode<AnyType>(this.root.element, this.root.left, this.root.right);	//To ensure the tree we have passed originally doesn't change its structure
    	
    	if (originalTree.root != null && originalTree.contains(x)) {
	    	if (x == originalTree.root.element) {
	    		leftRotatedTree.root = rotateLeft(originalTree.root,x,true);
	    		originalTree.root = new BinaryNode<AnyType>(leftRotatedTree.root.element, leftRotatedTree.root.left, leftRotatedTree.root.right);		
	    	}
	    	else {
	    		leftRotatedTree.root = rotateLeft(originalTree.root, x, false);
	    		originalTree.root = new BinaryNode<AnyType>(leftRotatedTree.root.element, leftRotatedTree.root.left, leftRotatedTree.root.right);
	    	}
	    }
    	else
    		System.out.println("Either tree doesn't exist or " + x + " is not an element of the tree");
    	
    	return leftRotatedTree;
    }
    
    /**
     * Single left rotation in a binary search tree means to put a node down to the left of right child. Thus its left child replaces it's 
     * position and the right child's left child becomes the right child of the pushed down node 
     * @param t Node which is the parent of the element to be rotated left, in case the element is present in the tree
     * @param x The element to be rotated left
     * @throws  IllegalArgumentException if the right child of the node with element x is null
     */
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> t, AnyType x, boolean isRoot) {
    	boolean isRotated = false;
    	if (isRoot) {
    		//Single rotation at the root
    		if (t.right != null) {
    			if (t.right.left != null) {
    				BinaryNode<AnyType> temp = new BinaryNode<AnyType>(t.right.left.element, t.right.left.left, t.right.left.right);
					BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.element, t.left, temp); //new subtree with the element to be rotated at the top and it's right child's left child as the right child of x
					
					t.right.left = temp2;	//Assign the new sub tree as the left child of x's right child 
					t = t.right;
					isRotated = true;
    			}
    			else {
    				BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.element, t.left, null); 
					t.right.left = temp2;	 
					t = t.right;
    			}
    		}
    		else
    			throw new IllegalArgumentException("Left rotation can't be performed. The right child of " + x + " can't be null for left rotation");
    		
    	}
    	else if (t != null) {
    		if (t.left != null && t.left.element.compareTo(x) == 0) {
    			if (t.left.right != null) {	//Right child of the element to be swapped must not be null
    				if (t.left.right.left != null) {
    					BinaryNode<AnyType> temp = new BinaryNode<AnyType>(t.left.right.left.element, t.left.right.left.left, t.left.right.left.right);
    					BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.left.element, t.left.left, temp);
    					
    					t.left.right.left = temp2; 
    					t.left = t.left.right;	
    					
    					isRotated = true;
    				}
    				else {
    					BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.left.element, t.left.left, null);
    					
    					t.left.right.left = temp2; 
    					t.left = t.left.right;	
    					
    					isRotated = true;
    				}
    			}
    			else
    				throw new IllegalArgumentException("Left rotation can't be performed. The right child of " + x + " can't be null for left rotation");
    		}
    		else if (t.right != null && t.right.element.compareTo(x) == 0) {
    			if (t.right.right != null) {
    				if (t.right.right.left != null) {
    					BinaryNode<AnyType> temp = new BinaryNode<AnyType>(t.right.right.left.element, t.right.right.left.left, t.right.right.left.right);
    					BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.right.element, t.right.left, temp);
    					
    					t.right.right.left = temp2;
    					t.right = t.right.right;
    					
    					isRotated = true;
    				}
    				else {
    					BinaryNode<AnyType> temp2 = new BinaryNode<AnyType>(t.right.element, t.right.left, null);
    					t.right.right.left = temp2;
    					t.right = t.right.right;
    					
    					isRotated = true;
    				}
    			}
    			else
    				throw new IllegalArgumentException("Left rotation can't be performed. The right child of " + x + " can't be null for left rotation");
    		}
    	}
    	if (!isRotated && t.left != null)
    		t.left = rotateLeft(t.left, x, false);
    	if (!isRotated && t.right != null)
    		t.right = rotateLeft(t.right, x, false);
    	
    	return t;
    }
    
    
    /**
     * Method to print the elements of a Binary Tree level by level, with each level beginning at a new line
     */
    public void printLevels() {
    	if (root != null) {
    		printLevels(root, true);
    	}	
    	else
    		System.out.println("Tree doesn't exist");
    }
    
    /**
     * 
     * @param t Node whose elements are to be printed level by level, with each level in a new line 
     * @param isRoot true if the node t is a root, else false
     */
    private void printLevels(BinaryNode<AnyType> t, boolean isRoot) {
    	//find the height of the tree
    	int height = 0;
    	ArrayList<ArrayList<BinaryNode<AnyType>>> treeElements = new ArrayList<ArrayList<BinaryNode<AnyType>>>();
    	if (isRoot) {
    		//height = findHeight(t, 0, 0);
    		height = height(t);
    		
    		treeElements.add(new ArrayList<BinaryNode<AnyType>>() {{add(root); }});
    	}
    	
    	for (int i = 0; i < height; i++) {
    		ArrayList<BinaryNode<AnyType>> tempList = new ArrayList<BinaryNode<AnyType>>();
    		for (BinaryNode<AnyType> levelElement : treeElements.get(i)) {
    			if (levelElement.left != null)
    				tempList.add(levelElement.left);
    			if (levelElement.right != null)
    				tempList.add(levelElement.right);
    		}
    		treeElements.add(tempList);
    	}
    	
    	//Print elements
    	for (ArrayList<BinaryNode<AnyType>> level : treeElements) {
    		for (BinaryNode<AnyType> node : level)
    			System.out.print(node.element + " ");
    		System.out.println();
    	}
    }
    
  /*  *//**
     * 
     * @param t	Node whose height is to be found
     * @param leftHeight Height of the left node of t in tree
     * @param rightHeight Height of the right node of t in tree
     * @return
     *//*
    private int findHeight(BinaryNode<AnyType> t, int leftHeight, int rightHeight) {
    	if (t == null)
    		return 0;
    	else {
    		if (t.left != null)
    			leftHeight = findHeight(t.left, leftHeight, rightHeight);	
    		if (t.right != null)
    			rightHeight = findHeight(t.right, leftHeight, rightHeight);
    		
    		if (leftHeight > rightHeight)
    			return (leftHeight + 1);
    		else
    			return (rightHeight + 1);
    		
    	}
}
 */  
    
    public void preOrderPrint(BinaryNode<AnyType> t) {
    	if (t != null) {
    		System.out.println(t.element);
    		preOrderPrint(t.left);
    		preOrderPrint(t.right);
    	}
    }
    
      
   // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        
        // Tree t:
        //			40
        //		   / \
        //		  10  50
        //		 / \  / \	
        //		5  20 45 60
        //     	  / \	  / \
        //		 15  30   55 70
        //			/ \		 / \
        //		   25 35    65  80
        //						/ \
        //					   75 90
        //						  / \
        //						 85  100
        //							  /
        //							 95
        //
        
        t.insert(40);
        for (int i = 10; i <= 100; i += 10) {
        	t.insert(i);
        }
        for (int i = 5; i < 100; i +=10) {
        	t.insert(i);
        }
        
        
        System.out.println("********* Node count of tree t *******");
        System.out.println("t.nodeCount() : " + t.nodeCount());
        
        System.out.println("************ Full Tree ************ ");
        System.out.println("Is tree t full? " +  t.isFull());		//Tree t has got only one child (95) for node with element 100. Not a full tree
        
        t.remove(95);
        System.out.println("Is tree t full after removing child 95? " + t.isFull());

        //Tree t1 :
        //---------
        //			6
        //		   / \
        //		  0   8
        //		  \	  / \
        //		  2  7   10
        //		 / \
        //		1	4
        //
        //
        // Tree t2 :
        //-----------
        //			11
        //		   /  \
        //		  5    13
        //		  \	   / \
        //		  7	  12  15
        //		 / \
        //		6   9
        //
        
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
        t1.insert(6);
        t2.insert(11);
        for (int i = 0; i < 10; i += 2) {
        	t1.insert(i);
        	t2.insert(i + 5);
        }
        
        for (int i = 10; i > 0; i -= 3) {
        	t1.insert(i);
        	t2.insert(i + 5);
        }
       
        System.out.println("********** Structure Comparison *************");
        System.out.println("Comparison result of the structures of trees t1 and t2 : " + t1.compareStructure(t2));	//false
        System.out.println("Comparison result of the structures of trees t and t1 : " + t.compareStructure(t1));	//true
        
        System.out.println("****** Equals **********");
        System.out.println("Is t1 equal to t1? " + t1.equals(t1));	//true
        System.out.println("Is t1 equal to t2? " + t1.equals(t2));	//false
        
        System.out.println("****** Copy **********");
        BinarySearchTree<Integer> copyOfT2 = t2.copy();
        System.out.println("Tree t2 copied. Its levels are : ");
        copyOfT2.printLevels();
        System.out.println("Is copy of t2 equal to t2? " + copyOfT2.equals(t2));	//true
        
        copyOfT2.remove(7);
        System.out.println("Is copy of t2 after removing element 7 equal to t2? " + copyOfT2.equals(t2));		//false
        
        
        System.out.println("****** Mirror **********");
        BinarySearchTree<Integer> newMirrorTree = t1.mirror();
        System.out.println("Mirroring done. newMirrorTree is the mirror of t1");
        
        // newMirrorTree structure:
        //---------------------------
        //			6
        //		   / \
        //		  8   0
        //		 / \   \
        //		10  7   2 
        //		 	   / \
        //			  4	  1
        //
        
        System.out.println("****** Is Mirror **********");
        System.out.println("Is t2 a mirror of newMirrorTree? " + t2.isMirror(newMirrorTree));	//false
        System.out.println("Is t1 a mirror of newMirrorTree? " + t1.isMirror(newMirrorTree));	//true
        
        System.out.println("****** Rotate right **********");
        BinarySearchTree<Integer> rightRotatedTree1 = t1.rotateRight(8);
        System.out.println("Tree t1 levels after right rotation of 8 :");
        rightRotatedTree1.printLevels();
        
        BinarySearchTree<Integer> rightRotatedTree = t2.rotateRight(11);
        System.out.println("Tree t2 levels after right rotation of 11 (11 is its root) :");
        rightRotatedTree.printLevels();
        
        System.out.println("****** Rotate left **********");
        BinarySearchTree<Integer> leftRotatedTree = copyOfT2.rotateLeft(13);
        System.out.println("Tree t2 levels after left rotation of 13 :");
        leftRotatedTree.printLevels();
        
        System.out.println("********** Print Levels *************");
        t.printLevels();
        
        t2.rotateRight(5);	//Should throw IllegalArgumentException, as element 5 has NULL left child. Right rotation can't be done
        //This code is unreachable, as the previous line throws an exception. Can be reached if the previous line is commented and the code rerun
        //t2.rotateLeft(12);	//Should throw IllegalArgumentException, as element 12 has NULL right child. Left rotation can't be done
        
        //Author's code commented out 
        /*final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree( );
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }
*/    
        }
}