class Scrambler {

    static String reverse(String input)
    {
        char[] tempArray = new char[input.length()];
        int index = 0;
        for(int i = input.length(); i > 0; i--)
        {        	
        	tempArray[index] = input.charAt(i - 1);
        	index++;
        }
        return new String(tempArray);
    }    
    
	static String reverseWords(String input)
	{		
        String flippedString = "";
        String[] strArray = new String[input.length()];
        strArray = input.split(" ");
        
        for(int i = strArray.length; i > 0; i--)
        {        	
        	flippedString = flippedString.concat(strArray[i - 1] + " ");        	
        }
        return flippedString.trim();
	}

    static boolean isWordPalindrome(String input) 
    throws java.io.IOException
    {
    	boolean rBool = true;
    	
    	if(input == null)
    	{
    		throw new java.io.IOException("input cannot be null!");
    	}
    	
    	if(false == input.equals(reverseWords(input)))
    	{
    		rBool = false;
    	}
    	
    	return rBool;
    }
    
	public static void main(String args[]) {
        String input, output;

        // reverse
        input = "Three blind mice";
        output = reverse(input);
        System.out.println("Should be: 'ecim dnilb eerhT'");
        System.out.println("Actual is: '" + output + "'");

        input = "level madam level";
        output = reverse(input);
        System.out.println("\nShould be: 'level madam level'");
        System.out.println("Actual is: '" + output + "'");
        
        // reverseWords
		input = "To be or not to be, that is the question.";
		output = reverseWords(input);
        System.out.println("\nShould be: 'question. the is that be, to not or be To'");
        System.out.println("Actual is: '" + output + "'");

		input = "Stressed spelled backwards is Desserts";
		output = reverseWords(input);
        System.out.println("\nShould be: 'Desserts is backwards spelled Stressed'");
        System.out.println("Actual is: '" + output + "'");
        
        boolean boolOutput = true;
        try
		{			
	        // isWordPalindrome
	        input = "forever eating cheese";
	        boolOutput = isWordPalindrome(input);
	        System.out.println("\n'" + input + "'" + (boolOutput ? " is " : " is not ") + "a word palindrome");
		}
		catch(java.io.IOException ex)
		{
			System.out.println("\n" + ex.getMessage());
		}
	       
		try
		{
	        input = "fall leaves when leaves fall";
	        boolOutput = isWordPalindrome(input);
	        System.out.println("\n'" + input + "'" + (boolOutput ? " is " : " is not ") + "a word palindrome");
		}
		catch(java.io.IOException ex)
		{
			System.out.println("\n" + ex.getMessage());
		}   
		try
		{
			input = null;
	        boolOutput = isWordPalindrome(input);
	        System.out.println("\n" + input + (boolOutput ? " is " : " is not ") + "a word palindrome");
		}
		catch(java.io.IOException ex)
		{
			System.out.println("\n" + ex.getMessage());
		}
	}
}


