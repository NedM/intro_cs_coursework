package ps2.bst_augmented_abstract;

import java.util.ArrayList;
import java.util.List;

public class RangeBinarySearchTree extends AugmentedBinarySearchTree {

	public RangeBinarySearchTree() {
		super();
	}
	
	@Override
	protected BinaryNode insert(int key, BinaryNode node) 
	{
		if (node == null) 
		{
			node = new RangeAugmentedBinaryNode(key, 0, null);
			return node;
		} 
		else
		{
			return super.insert(key, node);
		}

	}
	@Override
	protected void updateAugmentedValue(AugmentedBinaryNode node) 
	{
		//Augmented value = subTreeMaxVal - subTreeMinVal
		int subTreeMaxAugVal = 0;
		int leftMax = Integer.MIN_VALUE;
		int rightMax = Integer.MIN_VALUE;
		int subTreeMinAugVal = 0;
		int leftMin = Integer.MAX_VALUE;
		int rightMin = Integer.MAX_VALUE;
		
		if(null == node)
			return;
		
		//Get values for left and right sub tree maxima
		leftMax = getMaxValueInSubtree((RangeAugmentedBinaryNode)node.left);
		rightMax = getMaxValueInSubtree((RangeAugmentedBinaryNode)node.right);
		//Get value for subTree maximum
		subTreeMaxAugVal = (leftMax >= rightMax) ? leftMax : rightMax;
		
		//Get values for left and right sub tree minima
		leftMin = getMinValueInSubtree((RangeAugmentedBinaryNode)node.left);
		rightMin = getMinValueInSubtree((RangeAugmentedBinaryNode)node.right);
		//Get value for sub tree minimum
		subTreeMinAugVal = (leftMin <= rightMin) ? leftMin : rightMin;
		//Validate
		if(subTreeMaxAugVal < subTreeMinAugVal)
			throw new RuntimeException("ERROR! subTreeMax: " + subTreeMaxAugVal + " less than subTreeMin: " + subTreeMinAugVal);
		
		((RangeAugmentedBinaryNode)node).maximumValueInSubtree = subTreeMaxAugVal;
		((RangeAugmentedBinaryNode)node).minimumValueInSubtree = subTreeMinAugVal;
		node.augmentedValue = (subTreeMaxAugVal - subTreeMinAugVal);
	}
	
	private int getMaxValueInSubtree(RangeAugmentedBinaryNode node)
	{
		int max = Integer.MIN_VALUE;
		
		List<RangeAugmentedBinaryNode> subTreeList = new ArrayList<RangeAugmentedBinaryNode>();
		populateListOfSubtreeItems(node, subTreeList);
		
		if(subTreeList.isEmpty())
			max = 0;
		
		for(RangeAugmentedBinaryNode n : subTreeList)
		{
			if(n.maximumValueInSubtree > max)
				max = n.maximumValueInSubtree;
		}
		
		return max;
	}
	
	private int getMinValueInSubtree(RangeAugmentedBinaryNode node)
	{
		int min = Integer.MAX_VALUE;
		List<RangeAugmentedBinaryNode> subTreeList = new ArrayList<RangeAugmentedBinaryNode>();
		populateListOfSubtreeItems(node, subTreeList);
		
		if(subTreeList.isEmpty())
			min = 0;
		
		for(RangeAugmentedBinaryNode n : subTreeList)
		{
			if(n.minimumValueInSubtree < min)
				min = n.minimumValueInSubtree;
		}
		
		return min;
	}
	
	private void populateListOfSubtreeItems(RangeAugmentedBinaryNode node, List<RangeAugmentedBinaryNode> subTreeList)
	{
		if(null == node)
			return;
		
		//Add the current node
		subTreeList.add(node);
		
		//Depth First: FIFO
		if(null != node.left)
		{			
			populateListOfSubtreeItems((RangeAugmentedBinaryNode)node.left, subTreeList);
		}
		
		if(null != node.right)
		{			
			populateListOfSubtreeItems((RangeAugmentedBinaryNode)node.right, subTreeList);
		}
	}
	
	
	@Override
	protected void checkRep() 
	{
		super.checkRep();
		checkRep((RangeAugmentedBinaryNode)root);
	}
	
	private void checkRep(RangeAugmentedBinaryNode node)
	{
		if(null == node)
			return;
		
		int subTreeMax = getMaxValueInSubtree(node);
		int subTreeMin = getMinValueInSubtree(node);
		
		//Check rep:
		if(node.maximumValueInSubtree != subTreeMax)
			throw new RuntimeException("CheckRep Failure at node with key: "+ node.key + 
					"!\n Expected maxValInSubTree: " + subTreeMax + 
					", actual maxValInSubtree: " + node.maximumValueInSubtree);
		
		if(node.minimumValueInSubtree != subTreeMin)
			throw new RuntimeException("CheckRep Failure at node with key: "+ node.key + 
					"!\n Expected minValInSubTree: " + subTreeMin + 
					", actual minValInSubtree: " + node.minimumValueInSubtree);
		
		if(node.augmentedValue != (subTreeMax - subTreeMin))
			throw new RuntimeException("CheckRep Failure at node with key: "+ node.key + 
					"!\n Expected augmentedValue: " + (subTreeMax - subTreeMin) + 
					", actual augmentedValue: " + node.augmentedValue);
		
		//Recursion
		if(null != node.left)
			checkRep((RangeAugmentedBinaryNode)node.left);
		
		if(null != node.right)
			checkRep((RangeAugmentedBinaryNode)node.right);
	}

}

