class Primes {

    /**
     * Find all prime numbers up to a limit.
     * 
     * @param n
     *            Largest number to search
     * @param printPrimes
     *            If true, print the primes found to output.
     * 
     * @return number of primes <= n
     * @effects If printPrimes is true, prints all primes <= n to output.
     */
    static int findPrimes(int n, boolean printPrimes) {
        boolean isPrime = true;
        int numPrimes = 0;

        for (int i = 2; i <= n; i++) {
            isPrime = true;

            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                ++numPrimes;
                if (printPrimes) {
                    System.out.println(i);
                }
            }
        }
        return numPrimes;
    }

    static int findPrimesFaster(int n, boolean printPrimes) 
    {    	
        int numPrimes = 0;

        iteration:
        for (int i = 2; i <= n; i++) 
        {
            for (int j = 2; j <= Math.sqrt((double)i); j++) 
            {
            	if (i % j == 0)
            	{
            		//Not prime!
            		//Continue outer loop
            		continue iteration;            		
                }
            }
            
            ++numPrimes;
            if (printPrimes)
            {
            	System.out.println(i);
            }                        
        }
        return numPrimes;        
    }

    static int findPrimesEvenFaster(int n, boolean printPrimes) 
    {
        int[] primes = new int[n];
        primes[0] = 2;        
        int primeCount = 1;
                
        iteration:
            for (int i = 2; i <= n; i++) 
            {
            	double sqrt = Math.sqrt(i);
                for(int j = 0; j < primeCount; j++)
            	{	                
                	if (i % primes[j] == 0)
                	{
                		//Not prime!
                		//Continue outer loop
                		continue iteration;            		
                    }   
                	
                	if(primes[j] > sqrt)
                	{
                		break;
                	}
                }
                
                primes[primeCount] = i;
                primeCount++;
                if (printPrimes)
                {
                	System.out.println(i);
                }                        
            }
        return primeCount;
    }

    public static void main(String[] args) {
        int numPrimes = 0;
        int numFastPrimes = 0;
        int numFasterPrimes = 0;
        // Find and print all primes between 0 and 50.

        System.out.println("Running method findPrimes:");
        numPrimes = findPrimes(50, true);
        System.out.println(numPrimes + " primes <= 50");

        System.out.println("\nRunning method findPrimesFaster:");
        numFastPrimes = findPrimesFaster(50, true);
        System.out.println(numFastPrimes + " primes <= 50");
        if(numFastPrimes != numPrimes)
        {
        	System.out.println("### ERROR! Expected to find " + numPrimes + " primes but found " + numFastPrimes);
        }

        System.out.println("\nRunning method findPrimesEvenFaster:");
        numFasterPrimes = findPrimesEvenFaster(50, true);
        System.out.println(numFasterPrimes + " primes <= 50");
        if(numFasterPrimes != numPrimes)
        {
        	System.out.println("### ERROR! Expected to find " + numPrimes + " primes but found " + numFasterPrimes);
        }

        // Time how long it takes to find primes.

        long startTime = 0;
        long endTime = 0;

        System.out.print("\n\nTiming method findPrimes:");
        startTime = System.currentTimeMillis();
        numPrimes = findPrimes(40000, false);
        endTime = System.currentTimeMillis();
        System.out.println("  " + (endTime - startTime) + " milliseconds");
        System.out.println(numPrimes + " primes <= 40000");

        System.out.print("\nTiming method findPrimesFaster:");
        startTime = System.currentTimeMillis();
        numPrimes = findPrimesFaster(40000, false);
        endTime = System.currentTimeMillis();
        System.out.println("  " + (endTime - startTime) + " milliseconds");
        System.out.println(numPrimes + " primes <= 40000");

        System.out.print("\nTiming method findPrimesEvenFaster:");
        startTime = System.currentTimeMillis();
        numPrimes = findPrimesEvenFaster(40000, false);
        endTime = System.currentTimeMillis();
        System.out.println("  " + (endTime - startTime) + " milliseconds");
        System.out.println(numPrimes + " primes <= 40000");

        System.out.print("\n\nTiming method findPrimes:");
        startTime = System.currentTimeMillis();
        numPrimes = findPrimes(80000, false);
        endTime = System.currentTimeMillis();
        System.out.println("  " + (endTime - startTime) + " milliseconds");
        System.out.println(numPrimes + " primes <= 80000");

        System.out.print("\nTiming method findPrimesFaster:");
        startTime = System.currentTimeMillis();
        numPrimes = findPrimesFaster(80000, false);
        endTime = System.currentTimeMillis();
        System.out.println("  " + (endTime - startTime) + " milliseconds");
        System.out.println(numPrimes + " primes <= 80000");

        System.out.print("\nTiming method findPrimesEvenFaster:");
        startTime = System.currentTimeMillis();
        numPrimes = findPrimesEvenFaster(80000, false);
        endTime = System.currentTimeMillis();
        System.out.println("  " + (endTime - startTime) + " milliseconds");
        System.out.println(numPrimes + " primes <= 80000");
    }

}
