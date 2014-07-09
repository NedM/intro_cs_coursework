package ps2.bst_augmented_abstract;

import ps2.bst_exception.DuplicateItemException;
import ps2.bst_exception.ItemNotFoundException;

/**
 * This abstract class extends the functionality of BinarySearchTree with
 * integer augmented values.
 * 
 * The class expects subclasses to implement updateAugmentedValue correctly --
 * it uses the "propagate upwards" approach to update augmented values when
 * nodes are inserted/removed in the BST.
 * 
 */
public abstract class AugmentedBinarySearchTree extends BinarySearchTree {

	public AugmentedBinarySearchTree() {
		super();
	}

	/**
	 * This method updates the augmented value stored in the node, assuming the
	 * stored values for the children are correct.
	 */
	protected abstract void updateAugmentedValue(AugmentedBinaryNode node);

	@Override
	protected BinaryNode insert(int key, BinaryNode n) {
		AugmentedBinaryNode node = (AugmentedBinaryNode) n;
		AugmentedBinaryNode parent = null;

		while (true) {
			if (node == null)
			{
				if(n instanceof RangeAugmentedBinaryNode)
				{
					node = new RangeAugmentedBinaryNode(key, 0, parent);
				} 
				else
				{
					node = new AugmentedBinaryNode(key, 0, parent);
				}
				if (parent != null)
				{
					if (parent.key > node.key) 
					{
						parent.left = node;
					} 
					else 
					{
						parent.right = node;
					}
					updateAugmentation(node);
				}
				break;
			}
			if (key < node.key) 
			{
				parent = node;
				node = (AugmentedBinaryNode) node.left;

			}
			else if (key > node.key) 
			{
				parent = node;
				node = (AugmentedBinaryNode) node.right;
			} 
			else 
			{
				throw new DuplicateItemException("Key:" + key); // Duplicate
			}
		}

		while (node != null && node.parent != null)
			node = (AugmentedBinaryNode) node.parent;
		return node;
	}

	@Override
	protected BinaryNode removeMin(BinaryNode n) {
		AugmentedBinaryNode node = (AugmentedBinaryNode) n;
		AugmentedBinaryNode parent = null;
		boolean rootHadLeftChild = false;
		while (true) 
		{
			if (node == null)
				throw new ItemNotFoundException();
			else if (node.left != null) 
			{
				if (node == n)
					rootHadLeftChild = true;
				parent = node;
				node = (AugmentedBinaryNode) node.left;
			} 
			else
			{
				node = (AugmentedBinaryNode) node.right;
				if (node != null)
					node.parent = parent;
				if (parent != null)
					parent.left = node;
				break;
			}
		}

		updateAugmentation(node == null ? parent : node);

		if (rootHadLeftChild)
			return n;
		else
			return n.right;
	}


	@Override
	protected BinaryNode remove(int key, BinaryNode n) {
		AugmentedBinaryNode node = (AugmentedBinaryNode) n;
		AugmentedBinaryNode parent = null;

		while (true) {
			if (node == null) {
				throw new ItemNotFoundException("Key: " + key);
			}
			if (key < node.key) {
				parent = node;
				node = (AugmentedBinaryNode) node.left;
			} else if (key > node.key) {
				parent = node;
				node = (AugmentedBinaryNode) node.right;
			} else {
				if (node.left != null && node.right != null) {
					node.key = findMin(node.right).key;
					node.right = removeMin(node.right);
					if (node.right != null) {
					    ((AugmentedBinaryNode) node.right).parent = node;
					}
					updateAugmentation(node);
					break;
				} else {
					node = (AugmentedBinaryNode) ((node.left != null) 
	 								  ? node.left
								          : node.right);
					if (node != null)
						node.parent = parent;

					if (parent != null) {
						if (key > parent.key) {
							parent.right = node;
						} else if (key < parent.key) {
							parent.left = node;
						} else {
							throw new RuntimeException("");
						}
					}
					if (node == null)
						node = parent;
					updateAugmentation(node);
					break;
				}
			}
		}

		if (node == null)
			node = parent;

		while (node != null & node.parent != null)
			node = (AugmentedBinaryNode) node.parent;
		return node;
	}


	@Override
	protected void checkRep() {
		super.checkRep();
		checkRep((AugmentedBinaryNode) root);

	}

	private void checkRep(AugmentedBinaryNode root) {
		if (root == null)
			return;

		if (root.parent != null) {
			if (root.key > root.parent.key) {
				if (root.parent.right != root)
					throw new RuntimeException("Parent Pointer Wrong!");

			} else if (root.key < root.parent.key) {
				if (root.parent.left != root)
					throw new RuntimeException("Parent Pointer Wrong!");
			} else {
				throw new RuntimeException("Parent Pointer Duplicate!");
			}
		}

		checkRep((AugmentedBinaryNode) root.right);
		checkRep((AugmentedBinaryNode) root.left);
	}

	private void updateAugmentation(AugmentedBinaryNode node) 
	{
		if (node == null)
			return;
		updateAugmentedValue(node);
		updateAugmentation((AugmentedBinaryNode) node.parent);
	}
}
