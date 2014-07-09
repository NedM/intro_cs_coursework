package ps2.bst_arbitrary_keys;

public class StringBinaryTreeItem implements BinaryTreeItem {

	private String value;

	public StringBinaryTreeItem(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(BinaryTreeItem o) 
	{
		int rInt;
		int stringCompRes = this.getValue().compareToIgnoreCase(((StringBinaryTreeItem)o).getValue());
		
		if(stringCompRes < 0)
		{
			rInt = -1;
		}
		else if(stringCompRes > 0)
		{
			rInt = 1;
		}
		else if(stringCompRes == 0)
		{
			rInt = 0;
		}
		else
		{
			throw new RuntimeException("ERROR! Sould not have hit this code in StringBinaryTreeItem.compareTo()");
		}
							
		return rInt;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
