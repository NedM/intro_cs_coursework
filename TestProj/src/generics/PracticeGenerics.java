package generics;

import java.util.*;

public class PracticeGenerics 
{
	List<String> listString = new ArrayList<String>();
	List<Object> listObj = new ArrayList<Object>();
	List<?> listWildcard = new ArrayList<String>();
	

	public void TestAddToList()
	{
//		listString.add("ABC");
//		listObj.add(listString);
//		listObj.addAll(listWildcard);
//		listObj.add(listWildcard.get(0));
		
//		boolean b = listString.equals(listObj);		
	}
	
	public void TestSet()
	{
		Set<Set> setOfSets = new HashSet<Set>();
		Set<Set> s = setOfSets;
		setOfSets.add(new HashSet<String>());
		System.out.println("setOfSets count: " + setOfSets.size());
		System.out.println("setOfSets contains s: " + setOfSets.contains(s));
		setOfSets.add(s);
		System.out.println("setOfSets count: " + setOfSets.size());
		System.out.println("setOfSets contains s: " + setOfSets.contains(s));
		setOfSets.add(setOfSets);
		System.out.println("setOfSets count: " + setOfSets.size());
		System.out.println("setOfSets contains s: " + setOfSets.contains(s));		
	}
	
	public <T extends Receiver<M, V>, M, V> T makeNewReceiver(Class<T> r) throws InstantiationException, IllegalAccessException
	{
		return r.newInstance();
	}
	
	public <T extends Receiver<M, V>, M extends T, V extends M> T makeNewChainedReceiver(M message, T r1, T r2) throws InstantiationException, IllegalAccessException
	{
		return r2.receive(r1.receive(message));		
	}
}
