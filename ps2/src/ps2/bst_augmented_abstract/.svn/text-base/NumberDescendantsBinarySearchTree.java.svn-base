package ps2.bst_augmented_abstract;

public class NumberDescendantsBinarySearchTree extends
		AugmentedBinarySearchTree {

	public NumberDescendantsBinarySearchTree() {
		super();
	}

	@Override
	protected void updateAugmentedValue(AugmentedBinaryNode node)
	{
		if (node == null)
			return;

		int count = 0; // This stores the number of descendants for node
		
		if (node.left != null) 
		{
			// We add 1 (for the left child), and use
			// the augmented value from the left child
			count += 1 + ((AugmentedBinaryNode) node.left).augmentedValue;
		}

		if (node.right != null) 
		{
			// We add 1 (for the right child), and use
			// the augmented value from the right child
			count += 1 + ((AugmentedBinaryNode) node.right).augmentedValue;
		}

		node.augmentedValue = count;
	}

	private int find_children(AugmentedBinaryNode root) 
	{
		if (root == null)
			return 0;
		else 
		{
			int count = 0;
			if (root.right != null) 
			{
				count++;
				count += find_children((AugmentedBinaryNode) root.right);
			}
			
			if (root.left != null)
			{
				count++;
				count += find_children((AugmentedBinaryNode) root.left);
			}
			return count;
		}
	}

	private void checkRep(AugmentedBinaryNode root)
	{
		if (root == null)
			return;
		
		// We use a helper function that counts
		// the number of children from the root.
		int count = find_children(root);
		if (root.augmentedValue != count)
		{
			throw new RuntimeException("Depth Doesn't Match!");
		}

		//recursion
		checkRep((AugmentedBinaryNode) root.right);
		checkRep((AugmentedBinaryNode) root.left);
	}

	@Override
	protected void checkRep() {
		super.checkRep();
		checkRep((AugmentedBinaryNode) this.root);
	}

}
