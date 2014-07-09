
class FinancialCalc {

    public static void main(String[] args) {
        double principal = 1000.00; // $1000 initial investment
        double interestRate = 0.035; // 3.5% interest rate
        int numOfYears = 7; // investment length is 7 years

        double finalValue = 0.0;
        //finalValue = principal * Math.pow((1 + interestRate), numOfYears);
        finalValue = computeFinalValue(principal, interestRate, numOfYears);
        System.out.println("Investing $" + principal
                + " at an interest rate of " + (interestRate * 100) + "%"
                + " for " + numOfYears + " years"
                + " will have a final worth of $" + finalValue);
        
        //Computer principal
        finalValue = 1000;
    	interestRate = 0.05;
    	numOfYears = 4;
    	principal = 0;
    	
        try
        {        	
        	principal = computePrincipal(finalValue, interestRate, numOfYears);
        	System.out.println("To make $" + finalValue + " after " + numOfYears 
            		+ " years investing at an interest rate of " + (interestRate * 100) 
            		+ "%, you should invest a principal of $" + principal);           
        }
        catch(java.io.IOException ex)
        {
        	System.out.println("Failed to compute principal due to divide by zero error!");
        }        
        
        //Compute Interest rate
        finalValue = 525;
        principal = 500;
        numOfYears = 3;
        interestRate = 0;
        try
        {
        	interestRate = computeInterestRate(finalValue, principal, numOfYears);
        	System.out.println("To make $" + finalValue + " after " + numOfYears 
            		+ " investing a principal of $" + principal + ", you will need to invest at a rate of " 
            		+ (interestRate * 100) + "%");
        }
        catch(java.io.IOException ex)
        {
        	System.out.println("Failed to compute interest rate due to divide by zero error!");
        }
        
        //Compute number of years
        finalValue = 150;
        principal = 100;        
        interestRate = 0.044;
        double dNumYears = 0.0;
        try
        {
        	dNumYears = computeNumOfYears(finalValue, principal, interestRate);
        	System.out.println("To make $" + finalValue
            		+ " investing a principal of $" + principal + " at a rate of " 
            		+ (100 * interestRate) + "%, you will need to wait " + dNumYears + " years for maturity.");
        }
        catch(java.io.IOException ex)
        {
        	System.out.println("Failed to compute interest rate due to divide by zero error!");
        }
    }
    
    static double computeFinalValue(double principal, double interestRate, int numOfYears)
    {
      //V = P (1 + I)^Y
      return (principal * Math.pow((1 + interestRate), numOfYears));
    }
    
    static double computePrincipal(double finalValue, double interestRate, int numOfYears)
    throws java.io.IOException
    {
        //P = V/((1 + I)^Y)
    	double denominator = Math.pow((1 + interestRate), numOfYears);
    	
    	if(0 == denominator)
    	{
    		throw new java.io.IOException("Error! Cannot divide by zero!");
    	}
    	
        return finalValue/denominator;
    }

    static double computeInterestRate(double finalValue, double principal, int numOfYears) 
    throws java.io.IOException
    {
        //I = (V/P)^(1/Y) - 1
    	//Validate input to prevent divide by zero
    	if(0 == principal)
    	{
    		throw new java.io.IOException("Error! the principal cannot be zero!");
    	}
    	if(0 == numOfYears)
    	{
    		throw new java.io.IOException("Error! the number of years cannot be zero!");
    	}
    	
    	double exponent = (1/((double)numOfYears));
    	//I = (V/P)^(1/Y) - 1
        return (Math.pow((finalValue/principal), exponent) - 1);
    }

    static double computeNumOfYears(double finalValue, double principal, double interestRate) 
    throws java.io.IOException
    {
    	//V = P (1 + I)^Y
        //(1 + I)^Y = V/P
    	//Log[1 + I](V/P) = Y
    	//Y = Ln(V/P)/Ln(1 + I)
    	if(0 == principal)
    	{
    		throw new java.io.IOException("Error! the principal cannot be zero!");
    	}
    	
    	double numerator = Math.log(finalValue/principal);
    	double denominator = Math.log(1 + interestRate);
    	
        return numerator/denominator;
    }

}
