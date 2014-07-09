package ps2.bst_arbitrary_keys;

// Basic node stored in unbalanced binary search trees

// Ideally, this should be a nested class inside BST,
// but we haven't covered nested classes yet.

public class BinaryNode {
	BinaryTreeItem key;
	BinaryNode left;
	BinaryNode right;

	// Constructor
	BinaryNode(BinaryTreeItem key) {
		this.key = key;
		this.left = this.right = null;
	}
}