package ps1.warmup;

public class StringToWordsTester 
{
	public static void main(String args[]) 
	{
		try
		{
			StringToWords stw = new StringToWords("  This is 6.005.   ");
			while (stw.hasNext())
				System.out.println("<"+stw.next()+">");
		}
		catch(java.io.IOException ex)
		{
			System.out.println("ERROR! " + ex.getMessage());
		}
	}
}
