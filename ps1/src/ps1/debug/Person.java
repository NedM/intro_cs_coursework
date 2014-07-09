package ps1.debug;

public class Person 
{
	private String name;

	public Person(String name)
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}

	public boolean hasSameName(Person person)
	{
		//Split into 2 lines for easier debug
		boolean nameIsSame = person.name.equalsIgnoreCase(this.name);
		return nameIsSame;
	}
	
	@Override
	public String toString()
	{
		return "[Name: " + this.name + "]";
	}
}
