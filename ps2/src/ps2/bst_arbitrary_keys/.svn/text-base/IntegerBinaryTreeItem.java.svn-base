package ps2.bst_arbitrary_keys;

public class IntegerBinaryTreeItem implements BinaryTreeItem {

	private int value;

	public IntegerBinaryTreeItem(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public int compareTo(BinaryTreeItem o) 
	{
		int rInt;
		if(this.getValue() > ((IntegerBinaryTreeItem)o).getValue())
		{
			rInt = 1;
		}
		else if(this.getValue() < ((IntegerBinaryTreeItem)o).getValue())
		{
			rInt = -1;
		}
		else if(this.getValue() == ((IntegerBinaryTreeItem)o).getValue())
		{
			rInt = 0;
		}
		else
		{
			throw new RuntimeException("ERROR! Sould not have hit this code in IntegerBinaryTreeItem.compareTo()");
		}
		
		return rInt;
	}
}
