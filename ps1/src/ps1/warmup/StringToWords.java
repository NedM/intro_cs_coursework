package ps1.warmup;

public class StringToWords
{
	private String[] strArray;
	private int index;
	private int endIndex;
	
	public StringToWords (String input) throws java.io.IOException 
	{				
		if(null == input)
		{
			throw new java.io.IOException("\"input\" cannot be null!");
		}	
		
		index = 0; //Set the index to zero
		strArray = new String[input.length()];		
		
		strArray = input.split(" "); //Split the string at each space char
		
		endIndex = (strArray.length - 1); //Set the end of the array index to the length less one
	}
	
	public String next() throws java.io.IOException
	{
		String rString;
		if(index > endIndex)
		{
			throw new java.io.IOException("Current index is " + index + ", max index is " + endIndex + ".");
		}
		
		do
		{
			rString = strArray[index]; 			//Obtain the string at the current index
			index++; 							//increment the index
		}while(rString.equalsIgnoreCase(""));	//if the string at the current index is empty, try again

		return rString;
	}
	
	public boolean hasNext()
	{
		boolean rBool = true;
		
		//If the index is at the end, there is no next string: return false
		if(index > endIndex)
		{
			rBool = false;
		}
		return rBool;
	}
}
