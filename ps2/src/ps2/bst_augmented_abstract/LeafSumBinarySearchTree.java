package ps2.bst_augmented_abstract;

import java.util.ArrayList;
import java.util.List;

public class LeafSumBinarySearchTree extends AugmentedBinarySearchTree {

	public LeafSumBinarySearchTree() {
		super();
	}

	@Override
	protected void updateAugmentedValue(AugmentedBinaryNode node)
	{
		int addVal = 0;
				
		if(null == node)
			return;
		
		node.augmentedValue = 0;  //Zero augmented value before each op.
		
		if(null != node.left)
		{			
			if((null == node.left.left) && (null == node.left.right))
			{
				addVal = node.left.key;
			}
			else
			{
				addVal = ((AugmentedBinaryNode)node.left).augmentedValue;
			}
			node.augmentedValue += addVal;
		}
		
		if(null != node.right)
		{
			if((null == node.right.left) && (null == node.right.right))
			{
				addVal = node.right.key;
			}
			else
			{
				addVal = ((AugmentedBinaryNode)node.right).augmentedValue;
			}
			node.augmentedValue += addVal;
		}
		System.out.println("Node key = " + node.key + " Node AugVal = " + node.augmentedValue);
		
		return;
	}

	@Override
	protected void checkRep() 
	{
		super.checkRep();
		checkRepHelper((AugmentedBinaryNode)root);
	}

	private void checkRepHelper(AugmentedBinaryNode node)
	{
		List<AugmentedBinaryNode> listOfLeaves = new ArrayList<AugmentedBinaryNode>();
		int sum = 0;
			
		if(null == node)
		{
			return;
		}
		this.getLeafSumFromNode((AugmentedBinaryNode)node.left, listOfLeaves);
		this.getLeafSumFromNode((AugmentedBinaryNode)node.right, listOfLeaves);
		
		for(AugmentedBinaryNode n : listOfLeaves)
		{
			sum += n.key;
		}
		
		
		if(node.augmentedValue != sum)
		{
			throw new RuntimeException("Augmented value mismatch! Node key = " + node.key + 
					".\n Expected leaf sum of " + sum + " but actual sum was " + node.augmentedValue);
		}
		
		checkRepHelper((AugmentedBinaryNode)node.left);
		checkRepHelper((AugmentedBinaryNode)node.right);
	}
	
	private void getLeafSumFromNode(AugmentedBinaryNode node, List<AugmentedBinaryNode> listOfLeaves)
	{
		if(null == node)
			return;
		
		if(null != node.left)
		{
			getLeafSumFromNode((AugmentedBinaryNode)node.left, listOfLeaves);
		}
		
		if(null != node.right)
		{
			getLeafSumFromNode((AugmentedBinaryNode)node.right, listOfLeaves);
		}
		
		if((null == node.right) && (null == node.left) && (node.parent != null))
		{
			listOfLeaves.add(node);
		}
	}
}
