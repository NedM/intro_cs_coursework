package ps2.bst_simple;

import ps2.bst_exception.DuplicateItemException;
import ps2.bst_exception.ItemNotFoundException;

/*
 * Basic Binary Search Tree that only stores unique integers.
 */
public class BinarySearchTree {

	/* The tree root. */
	protected BinaryNode root;

	/**
	 * Create an Empty Tree.
	 @effects creates a root node with value set to null
	 @modifies root node and subtree
	 @param None
	 @return NA
	 @requires None
	 @throws None
	 */
	public BinarySearchTree() {
		root = null;
		checkRep();
	}

	/**
	 * Insert key into the tree.
	 @effects inserts a node with specified key into the tree
	 @modifies the subtree underneath the key specified if the key is not a duplicate
	 @param key int value where the node should be inserted
	 @return void
	 @requires None
	 @throws DuplicateItemException if the key already exists in the tree 
	 */
	public void insert(int key) {
		checkRep();
		root = insert(key, root);
		checkRep();
	}

	/**
	 * Remove key from the tree.
	 @effects removes the node at the key specified from the tree and reconfigures the subtree
	 @modifies the subtree under the key specified including the removal of the node at the key specified
	 @param key int value of the node to remove
	 @return void
	 @requires None
	 @throws ItemNotFoundException if the key cannot be found in the tree
	 */
	public void remove(int key) {
		checkRep();
		root = remove(key, root);
		checkRep();
	}

	/**
	 * Remove minimum key from the tree.
	 @effects removes the node with the smallest value from the tree and reconfigures the subtree
	 @modifies the subtree under the node with the minimum value (including the node itself)
	 @param None
	 @return void
	 @requires None
	 @throws ItemNotFoundException if root == null
	 */
	public void removeMin() {
		checkRep();
		root = removeMin(root);
		checkRep();
	}

	/**
	 * Find the smallest key in the tree.
	 @effects returns the key of the node with the smallest value
	 @modifies None
	 @param None
	 @return the key of the node with the smallest value
	 @requires None
	 @throws ItemNotFoundException if the root node is null
	 */
	public int findMin() {
		if (findMin(root) == null)
			throw new ItemNotFoundException();
		return elementAt(findMin(root));
	}

	/**
	 * Find the largest key in the tree.
	 @effects returns the key of the node with the largest value
	 @modifies None
	 @param None
	 @return the key of the node with the largest value
	 @requires None
	 @throws ItemNotFoundException if the root node is null
	 */
	public int findMax() {
		if (findMax(root) == null)
			throw new ItemNotFoundException();
		return elementAt(findMax(root));
	}

	/**
	 * Find an item in the tree.
	 * 
	 * Note: For now, this method just returns the input, or throws an
	 * exception. But this method could be generalized for different kinds of
	 * keys.
	 @effects returns the value of the node at the specified key location
	 @modifies None
	 @param key int value of node for which to obtain value
	 @return the value of the node at the specified key
	 @requires None
	 @throws ItemNotFoundException if the key is not found in the tree
	 */
	public int find(int key) {
		if (find(key, root) == null)
			throw new ItemNotFoundException();
		return elementAt(find(key, root));
	}

	/**
	 * Queries whether an item is present in the tree.
	 @effects returns boolean value indicating whether a key exists in the tree
	 @modifies None
	 @param key int value to search for in the tree
	 @return true if the key exists anywhere in the tree. false otherwise
	 @requires root != null
	 @throws None
	 */
	public boolean contains(int key) {
		return find(key, root) != null;
	}

	/**
	 * Make the tree logically empty.
	 @effects sets the root node of the tree to null
	 @modifies root node of the tree and subtree if one existed previously
	 @param None
	 @return void
	 @requires None
	 @throws None
	 */
	public void makeEmpty() {
		root = null;
		checkRep();
	}

	/**
	 * Test if the tree is logically empty.
	 @effects returns a boolean value
	 @modifies None
	 @param None
	 @return true if the root node of the tree is null. False otherwise
	 @requires None
	 @throws None
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/*---------------------------------------------------------------*/

	/**
	 * Internal method to get element field.
	 @effects returns int value of node
	 @modifies None
	 @param node BinaryNode to get key value of
	 @returns int value of node
	 @requires node is not null
	 @throws None
	 */
	protected int elementAt(BinaryNode node) {
		return node.key;
	}

	/**
	 * Internal method to insert into a subtree.
	   @effects adds new BinaryNode with value key into correct position in the subtree under
	   BinaryNode node. Throws DuplicateItemException if key is already in subtree.
	   @modifies subtree under BinaryNode node
	   @param key   int to insert into subtree
	   @param node  BinaryNode that new node must be inserted under
	   @return BinaryNode that is either node passed in to function or new node with value key
	   @throws DuplicateItemException if key is already in subtree
	 */
	protected BinaryNode insert(int key, BinaryNode node) {
		if (node == null)
			node = new BinaryNode(key);
		else if (key < node.key)
			node.left = insert(key, node.left);
		else if (key > node.key)
			node.right = insert(key, node.right);
		else
			throw new DuplicateItemException("Key:" + key); // Duplicate
		return node;
	}

	/**
	 * Internal method to remove from a subtree.
	  @effects removes BinaryNode with value key from the subtree under
	  BinaryNode node. Throws ItemNotFoundException if key is not in subtree.
	  @modifies subtree under BinaryNode node
	  @param key   int to remove from subtree
	  @param node  BinaryNode under which to search for key
	  @return BinaryNode that is removed
	  @throws ItemNotFoundException if key is not in subtree
	 */
	protected BinaryNode remove(int key, BinaryNode node) {
		if (node == null)
			throw new ItemNotFoundException("Key: " + key);
		if (key < node.key)
			node.left = remove(key, node.left);
		else if (key > node.key)
			node.right = remove(key, node.right);
		else if (node.left != null && node.right != null) // Two children
		{
			node.key = findMin(node.right).key;
			node.right = removeMin(node.right);
		} else
			node = (node.left != null) ? node.left : node.right;
		return node;
	}

	/**
	 * Internal method to remove minimum item from a subtree.
	  @effects removes minimum item from subtree under BinaryNode node. 
	  Throws ItemNotFoundException if subtree is empty.
	  @modifies subtree under BinaryNode node
	  @param node BinaryNode under which to search for minimum node
	  @return BinaryNode that has minimum value in subtree
	  @throws ItemNotFoundException if subtree is empty
	 */
	protected BinaryNode removeMin(BinaryNode node) {
		if (node == null)
		{
			throw new ItemNotFoundException();
		}
		else if (node.left != null)
		{
			node.left = removeMin(node.left);
			return node;
		} 
		else
		{
			return node.right;
		}
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	  @param node BinaryNode under which to search for minimum node
	  @return BinaryNode that has minimum value in subtree
	  @effects returns minimum value node in subtree, or null if tree is empty
	 */
	protected BinaryNode findMin(BinaryNode node) {
		if (node != null)
			while (node.left != null)
				node = node.left;

		return node;
	}

	/**
	 * Internal method to find the largest item in a subtree.
	  @param node BinaryNode under which to search for maximum node
	  @return BinaryNode that has maximum value in subtree
	  @effects returns maximum value node in subtree, or null if subtree is empty
	 */
	protected BinaryNode findMax(BinaryNode node) {
		if (node != null)
			while (node.right != null)
				node = node.right;

		return node;
	}

	/**
	 * Internal method to find an item in a subtree.
	  @param node BinaryNode under which to search for key
	  @return BinaryNode that has value key in subtree
	  @effects returns node with value of key in subtree, or null if key is not in subtree
	 */
	protected BinaryNode find(int key, BinaryNode node) {
		while (node != null) {
			if (key < node.key)
				node = node.left;
			else if (key > node.key)
				node = node.right;
			else
				return node; // Match
		}

		return null; // Not found
	}



	// Two important properties of the invariant are:
	// 1. Each node (n) must have n.right.key != n.key != n.left.key
	// 2. Each node (n) must have n.right.key > n.key and n.left.key < n.key
	// new 3. No key in the left subtree of a node may be greater than that node's key and
	// no key in the right subtree may be less than that node's key
	
	// As you design your invariant, think of the answers to the following questions:
	
	// Are the above two properties sufficient for correctness of a BST? No.
	// Does your checkRep() work correctly for the following tree?
	//  				7
	// 				   /
	//                3
	//               / \
	//               1 8

	// This method should throw a RuntimeException() if the representation
	// invariant is violated.

	/**
	 * Verifies that the representation of the binary tree is valid
	 @effects verifies the representation of the binary tree structure
	 @modifies None
	 @param Node the root of the tree
	 @return void
	 @requires None
	 @throws RuntimeException
	 */
	protected void checkRep()
	{		
		checkRepHelper(root);		
	}
	
	private void checkRepHelper(BinaryNode node)
	{
		if(node == null)
		{
			//Root is null. tree is empty
			return;
		}

		//Depth first search/check!
		
		//if a left node exists...
		if(node.left != null)
		{	
			//Verify node.left.key must be < node.key. May not be >=!
			if(!(node.left.key < node.key))
			{
				throw new RuntimeException("Rep invariant check failed!\n node.left.key: " + node.left.key + " NOT < node.key: " + node.key + "!");
			}			
			//Verify the maximum key in the subtree is still less than node.key
			if(!(findMax(node.left).key < node.key))
			{
				throw new RuntimeException("Rep invariant check failed!\n Key in left subtree found to have value greater than or equal to node.key!");
			}
			//and push it onto the stack
			checkRepHelper(node.left); 
			//pop on return
		}

		//if a right node exists...
		if(node.right != null)
		{
			//Verify node.right.key must be > node.key. May not be <=!
			if(!(node.right.key > node.key))
			{
				throw new RuntimeException("Rep invariant check failed!\n node.right.key: " + node.right.key + " NOT > node.key: " + node.key + "!");
			}
			//Verify the minimum key in the subtree is still greater than node.key
			if(!(findMin(node.right).key > node.key))
			{
				throw new RuntimeException("Rep invariant check failed!\n Key in right subtree found to have value less than or equal to node.key!");
			}
			
			 //and push it onto the stack...
			checkRepHelper(node.right); 
			//pop on return
		}
	}
}
