package ps2.bst_arbitrary_keys;

/**
 * Class representing an MIT student with a name and a student ID.
 */
public class Student implements BinaryTreeItem {

	private int mitID;
	private String name;

	public Student(int mitID, String name) {
		this.mitID = mitID;
		this.name = name;
	}
	
	public Student CopyThisStudent()
	{
		return new Student(this.getMitID(), this.getName());
	}

	public void setMitID(int mitID) {
		this.mitID = mitID;
	}

	public int getMitID() {
		return mitID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(BinaryTreeItem other) 
	{
		int stringCompRes = this.nameCompare(((Student)other).getName());
		
		return (stringCompRes == 0) ? this.mitIdCompare(((Student)other).getMitID()) : stringCompRes;
	}
	
	@Override
	public String toString()
	{
		return "Student [" + this.getName() + ", " + this.getMitID() + "]";	
	}
	
	private int nameCompare(String otherName)
	{
		int rInt;
		int stringCompRes = this.getName().compareToIgnoreCase(otherName);
		
		if(stringCompRes > 0)
		{
			rInt = 1;
		}
		else if(stringCompRes < 0)
		{
			rInt = -1;
		}
		else if(stringCompRes == 0)
		{
			rInt = 0;
		}
		else
		{
			throw new RuntimeException("ERROR! Sould not have hit this code in Student.nameCompare()");
		}
		
		return rInt;
	}
	
	private int mitIdCompare(int otherId)
	{
		int rInt;
		
		if(this.getMitID() > otherId)
		{
			rInt = 1;
		}
		else if(this.getMitID() < otherId)
		{
			rInt = -1;
		}
		else if(this.getMitID() == otherId)
		{
			rInt = 0;
		}
		else
		{
			throw new RuntimeException("ERROR! Sould not have hit this code in Student.mitIdCompare()");
		}
		return rInt;
	}

}
