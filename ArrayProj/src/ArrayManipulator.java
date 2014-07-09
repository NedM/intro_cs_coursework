public class ArrayManipulator {

	/**
	 * @param args 
	 */
	public static void main(String[] args) 
	{
		String printString = "Original: ["; 
		int[] myArray = {-3, -2, -1, 0, 1, 2, 3};
		
		for(int i : myArray)
		{
			printString = printString.concat(i + ", ");
		}
		printString = printString.substring(0, printString.length() - 2) + "]";
		System.out.println(printString);
		
		boolean madeSomePositive = ArrayManipulator.makeArrayPositive(myArray);
		printString = "Modified: [";
		for(int j : myArray)
		{
			printString = printString.concat(String.format("%1$d, ", j));			
		}
		printString = printString.substring(0, printString.length() - 2) + "]";
		
		if(madeSomePositive)
		{			
			printString = printString.concat("\nMade some values positive");
		}
		
		System.out.println(printString);
	}
	
	/**
	 * Takes an array and takes the absolute value of any negative members of the array
	 * such that all members of the array are positive at the end of execution
	 * @param array the array to make all positive
	 * @return A boolean value indicating whether any negative values were converted
	 * True indicates that at least one negative member was found and converted.
	 */
	public static boolean makeArrayPositive(int[] array)
	{
		boolean madeAtLeastOnePositive = false;
		for(int i = 0; i < array.length; i++)
		{
//			madeAtLeastOnePositive = madeAtLeastOnePositive || makePositive(array, i);  //Short circuit evaluation. Only makes the first negative value positive
//			madeAtLeastOnePositive = makePositive(array, i) || madeAtLeastOnePositive;  //Still short circuit but OK because it will still make all the values positive
			madeAtLeastOnePositive |= makePositive(array, i);  //Same as above but more compact
		}
		return madeAtLeastOnePositive;
	}

	/**
	 * Takes a single entry of an array and stores its absolute value if it is negative
	 * @param array the array to index into
	 * @param index the position of the current member
	 * @return a boolean value indicating whether the member was made positive
	 * True is returned if the value was negative and made positive. False otherwise.
	 */
	public static boolean makePositive(int[] array, int index)
	{
		boolean madeItPositive = false;
		int current = array[index];
		if(current < 0)
		{
			array[index] = Math.abs(current);
			madeItPositive = true;
		}
		return madeItPositive;		
	}
}
