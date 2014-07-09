package grades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/** 
 * A GradeManager will create a command-line prompt that will let someone add grades
 * and display grades in histogram format.
 * 
 */

public class GradeManager 
{
	
	// Keeps track of the number of each grade this has
	private HashMap<LetterGrade,Integer> allGrades;

	/**
	 * Creates a new GradeManager.
	 */
	public GradeManager() 
	{
		// Create a new HashMap of the grades
		this.allGrades = new HashMap<LetterGrade,Integer>();
		
		// Add in all grades and set the occurance to 0
		for (LetterGrade gl : LetterGrade.values()) {
			allGrades.put(gl, 0);
		}
	}
		
	/**
	 * Adds grade to this GradeManager.
	 * @param grade - grade to add to this grad manager
	 */
	public void addGrade(String grade) throws InvalidGradeException 
	{
		int numValues = 0;
		if (grade.equals("a")) 
		{
			numValues = this.allGrades.get(LetterGrade.A);
			this.allGrades.put(LetterGrade.A, (numValues + 1));
		} 
		else if (grade.equals("b")) 
		{
			 numValues = this.allGrades.get(LetterGrade.B);
			this.allGrades.put(LetterGrade.B, (numValues + 1));
		} 
		else if (grade.equals("c")) 
		{
			numValues = this.allGrades.get(LetterGrade.C);
			this.allGrades.put(LetterGrade.C, (numValues + 1));
		} 		
		else if(grade.equals("d"))
		{
			numValues = this.allGrades.get(LetterGrade.D);
			this.allGrades.put(LetterGrade.D, (numValues + 1));
		}
		else if(grade.equals("f"))
		{
			numValues = this.allGrades.get(LetterGrade.F);
			this.allGrades.put(LetterGrade.F, (numValues + 1));
		}
		else
		{
			// If grade doesn't match a valid grade, throw an InvalidGradeException
			throw new InvalidGradeException("Expected one of the following values as a string: a, b, c, d, f. " + grade + " is not a valid grade.");
		}		
	}

	/**
	 * Prints out a histogram of the grades to the console.
	 *
	 */
	public void printHistogram() 
	{
		int numAs, numBs, numCs, numDs, numFs;
		numAs = this.allGrades.get(LetterGrade.A);
		numBs = this.allGrades.get(LetterGrade.B);
		numCs = this.allGrades.get(LetterGrade.C);
		numDs = this.allGrades.get(LetterGrade.D);
		numFs = this.allGrades.get(LetterGrade.F);
		
		for(int i = 0; i < numAs; i++)
		{
			System.out.print('A');
		}
		System.out.println();
		for(int i = 0; i < numBs; i++)
		{
			System.out.print('B');
		}
		System.out.println();
		for(int j = 0; j < numCs; j++)
		{
			System.out.print('C');
		}
		System.out.println();
		for(int i = 0; i < numDs; i++)
		{
			System.out.print('D');
		}
		System.out.println();
		for(int i = 0; i < numFs; i++)
		{
			System.out.print('F');
		}
	}
	
	/**
	 * Returns a string representation of the histogram of the grades.
	 * @return a string representation of the histogram of the grades.
	 */
	public String getHistString() 
	{
		StringBuffer sb = new StringBuffer();
		for (LetterGrade gl : LetterGrade.values()) {
			sb.append( gl+":");
			for (int i = 0; i < this.allGrades.get(gl); i++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Simple loop that accepts 3 commands from System.in:
	 *    add <some grade> : for example, "add a" or "add b"
	 *                       adds the given grade to the GradeManager
	 *    print            : prints out all the grades in this GradeManager
	 *                       in a histogram format
	 *    exit             : exits the program
	 * @param args
	 */
	public static void main(String[]  args) 
	{
		GradeManager gm = new GradeManager();
		
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Starting the grade manager");
		
		while (true) 
		{
			String input;
			try
			{
				input = cin.readLine();
				if (input.startsWith("add")) 
				{
					//Get the sub string starting after "add "
					gm.addGrade(input.substring("add ".length()).trim());
				} 
				else if (input.equals("print"))
				{
					gm.printHistogram();
				}  
				else if (input.equals("exit"))
				{
					break;
				}
			}
			catch(java.io.IOException ex)
			{
				System.out.println("Error! Failed to read input!\n Exception: " + ex.getMessage());				
			}	
			catch(InvalidGradeException igEx)
			{
				System.out.println("Error! Invalid grade!\nException: " + igEx.getMessage());
			}
			
		}
	}

}