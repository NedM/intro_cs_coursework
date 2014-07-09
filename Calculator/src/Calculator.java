
public class Calculator
{
	private int lastResult;
	
	public static void About()
	{
		System.out.println("Ned's Calculator copyright 2012");
	}
	
	public Calculator()
	{
		this.lastResult = 0;
	}
	
	public int Add(int a, int b)
	{
		int temp = a + b;
		this.lastResult = temp;
		return temp;
	}
	
	public void PrintLastResult()
	{
		System.out.println("Last Result: " + this.lastResult);
	}
}
