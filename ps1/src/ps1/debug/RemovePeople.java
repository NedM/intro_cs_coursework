package ps1.debug;

import java.util.ArrayList;
import java.util.Iterator;

public class RemovePeople 
{
	public static void main(String[] args) 
	{
		Person ben = new Person("Ben");
		ArrayList<Person> persons = new ArrayList<Person>();
		
		persons.add(new Person(new String("Ben")));
		persons.add(new Person(new String("Alyssa")));
		persons.add(new Person(new String("Alice")));
		persons.add(new Person(new String("George")));
		persons.add(new Person(new String("Ralph")));
		persons.add(new Person(new String("Naomi")));		
		
		//Use iterator to prevent java.util.ConcurrentModificationException being thrown
		for(Iterator<Person> itr = persons.iterator(); itr.hasNext(); )
		{
			Person nextPerson = itr.next();
			if(nextPerson.hasSameName(ben))
			{
				itr.remove();
				System.out.println("Removed " + nextPerson.getName() + " from the list.");
			}
		}
		System.out.println(persons);
	}
}
