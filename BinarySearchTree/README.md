# Binary Search Tree Implementation

Note: Some part of the code in this project is adapted from author Mark Allen Weiss' code of Data Structures and Algorithm Analysis in Java book.

Implementation of BinarySearchTree without using API.

A binary search tree is a binary tree (every node with at most 2 children) where for every node, all node values in its left subtree is smaller than it, and all node values in its right subtree larger than it.

The code modifies the author's BinarySearchTree code to implement some new methods as described below:

nodeCount - Recursively traverses the tree and returns the count of nodes.

isFull - Returns true if the tree is full. Definition: A full tree has every node as either a leaf or a parent with two children.

compareStructure - Compares the structure of current tree to another tree and returns true if they match.

     For example, these two trees have the same structure:
            5           10
           / \         /  \
          3   8       5   15
         / \         / \
        1   4       2   7
equals - Compares the current tree to another tree and returns true if they are identical.

copy - Creates and returns a new tree that is a copy of the original tree.

mirror - Creates and returns a new tree that is a mirror image of the original tree. For example, for the tree on the left, the tree on the right is returned:

     100                 100
    /   \               /   \
   50   150    -->     150  50
  /                           \
 40                           40
  \                           /
  45                         45
isMirror- Returns true if the tree is a mirror of the passed tree.

rotateRight - Performs a single rotation on the node having the passed value. For example, if a RotateRight on 100 is performed: //rotate right on 40 will be an exception

    100                  50
   /   \                /   \
  50   150    -->      40   100
 /                      \     \
40                      45    150
 \ 
 45
rotateLeft - As above but left rotation.

printLevels - performs a level-by-level printing of the tree.
