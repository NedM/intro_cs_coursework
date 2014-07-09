
public class TestCalculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Calculator myCalc = new Calculator();
		Calculator yourCalc = new Calculator();
		
		myCalc.Add(123, 456);
		yourCalc.Add(1, 1); 
		
		myCalc.PrintLastResult(); //Should print 579
		yourCalc.PrintLastResult();  //Should print 2
//		System.out.println(String.format("Result: %1s. %1s", result, "Yay!!"));
	}

}
