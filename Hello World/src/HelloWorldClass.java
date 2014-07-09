import java.io.*;

public class HelloWorldClass 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{			
		String name = "world";
		
		try
		{
			name = HelloWorldClass.GetName();
			HelloWorldClass.SayHello(name);
		}
		catch(Exception e)
		{
			System.out.println("Couldn't get input!");
		}		
		
//		HelloWorldClass myHelloWorld = new HelloWorldClass();
//		myHelloWorld.HelloToYou("Ned");
	}

	/**
	 * Says Hello to the person you specify
	 * @param whomToSayHelloTo - The person you want to say hello to
	 */
	private static void SayHello(String whomToSayHelloTo)
	{
		//Say "hello" to someone
		System.out.println("Hello, " + whomToSayHelloTo + "!\n");
	}
	
	private static String GetName() throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("What is your name?");
		return reader.readLine();		
	}
	
	private void HelloToYou(String whoAreYou)
	{
		System.out.println("Well, hello to " + whoAreYou + " indeed!");
	}
}
