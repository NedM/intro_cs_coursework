package ps2.bst_arbitrary_keys;

/**
 * Represents an Item that can be stored in a Binary Search Tree.
 */
public interface BinaryTreeItem {

	/**
	 * Returns:
	 * 
	 * 0 if this == other,
	 * 
	 * -1 if this < other,
	 * 
	 * +1 if this > other.
	 @effects Compares two BinaryTreeItem nodes
	 @param Other BinaryTreeItem object. May be null
	 @requires other cannot be null and other must not be a different subclass of BinaryTreeItem than this
	 @returns and integer value: 0 if the objects are equal, 1 if this object is greater than other, -1 if this object is less than other
	 @modifies None
	 @throws ClassCastException
	 */
	int compareTo(BinaryTreeItem other);
}
