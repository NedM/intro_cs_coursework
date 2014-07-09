package ps2.bst_augmented_abstract;

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
	 */
	public BinarySearchTree() {
		root = null;

	}

	/**
	 * Insert key into the tree.
	 */
	public void insert(int key) {
		root = insert(key, root);
	}

	/**
	 * Remove key from the tree.
	 */
	public void remove(int key) {
		root = remove(key, root);
	}

	/**
	 * Remove minimum key from the tree.
	 */
	public void removeMin() {
		root = removeMin(root);
	}

	/**
	 * Find the smallest key in the tree.
	 */
	public int findMin() {
		if (findMin(root) == null)
			throw new ItemNotFoundException();
		return elementAt(findMin(root));
	}

	/**
	 * Find the largest key in the tree.
	 */
	public int findMax() {
		if (findMax(root) == null)
			throw new ItemNotFoundException();
		return elementAt(findMax(root));
	}

	/**
	 * Find an item in the tree.
	 */
	public int find(int key) {
		if (find(key, root) == null)
			throw new ItemNotFoundException();
		return elementAt(find(key, root));
	}

	/**
	 * Queries whether an item is present in the tree.
	 */
	public boolean contains(int key) {
		return find(key, root) != null;
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		root = null;

	}

	/**
	 * Test if the tree is logically empty.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/*---------------------------------------------------------------*/

	/**
	 * Internal method to get element field.
	 */
	protected int elementAt(BinaryNode t) {
		return t.key;
	}

	/**
	 * Internal method to insert into a subtree.
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
			node.key = (findMin(node.right).key);
			node.right = removeMin(node.right);
		} else
			node = (node.left != null) ? node.left : node.right;
		return node;
	}

	/**
	 * Internal method to remove minimum item from a subtree.
	 */
	protected BinaryNode removeMin(BinaryNode node) {
		if (node == null)
			throw new ItemNotFoundException();
		else if (node.left != null) {
			node.left = removeMin(node.left);
			return node;
		} else {
			return node.right;
		}
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 */
	protected BinaryNode findMin(BinaryNode node) {
		if (node != null)
			while (node.left != null)
				node = node.left;

		return node;
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 */
	protected BinaryNode findMax(BinaryNode node) {
		if (node != null)
			while (node.right != null)
				node = node.right;

		return node;
	}

	/**
	 * Internal method to find an item in a subtree.
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

	// Two important invariants:
	// 1. Each node (n) must have n.right.key != n.key != n.left.key
	// 2. Each node (n) must have n.right.key > n.key and n.left.key < n.key
	
	// Are these sufficient for correctness of a BST:
	// Hint: Should your checkRep() work for:
	//                    7
	//                   /
	//                  3
	//                 / \
	//                1   8

	// This method should throw a RuntimeException() if the representation
	// invariant is violated.
	protected void checkRep() {
		// TODO: Your Code Goes Here
	}
}
