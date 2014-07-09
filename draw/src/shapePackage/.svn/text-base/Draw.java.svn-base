package shapePackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Ned
public class Draw
{
	private static final String defaultFileName = "Shapes.ps";
	private static PrintStream prntStream = null;
	
	/**
	 * Empty constructor for the Draw class
	 * This class will be static
	 * @effects empty constructor
	 * 
	 */
	public Draw()
	{
		//Empty Constructor
	}
	
	/**
	 * Creates a file from a string with the default name
	 * @author nedmurp
	 * @effects Creates a file named with the default file name containing the string specified
	 * @param postScriptString String: The PostScript code to write to file as a string
	 */
	public static void CreateFile_PostScript(String postScriptString)
	{
		Draw.CreateFile_PostScript(postScriptString, Draw.defaultFileName);
	}
	
	/**
	 * Creates a file from a list of strings with the default file name
	 * @author nedmurp
	 * @effects Creates a file named with the default file name containing the strings specified
	 * @param postScriptStrings List<String> : a list of strings to write to file
	 */
	public static void CreateFile_PostScript(List<String> postScriptStrings)
	{
		Draw.CreateFile_PostScript(postScriptStrings, Draw.defaultFileName);
	}
	
	/**
	 * Creates a file with the specified name containing the specified string
	 * @param postScriptString String : a String to write to file
	 * @param filename String : The full path of the file to create terminated with a .ps to indicate that it contains postscript
	 */
	public static void CreateFile_PostScript(String postScriptString, String filename)
	{
		ArrayList<String> stringArray = new ArrayList<String>();
		stringArray.add(postScriptString);
		Draw.CreateFile_PostScript(stringArray, filename);
	}
	
	/**
	 * Creates a file with the specified name containing the specified strings
	 * @param postScriptStrings List<String>: A list of Strings to write to file
	 * @param filename String : The full path of the file to create terminated with a .ps to indicate that it contains postscript
	 */
	public static void CreateFile_PostScript(List<String> postScriptStrings, String filename)
	{
		try
		{
			Draw.openFileStream(filename);
			Draw.writeStrings(postScriptStrings);
			System.out.println("Successfully created and wrote to file \"" + filename + "\".");
		}
		catch(IOException ioEx)
		{
			System.out.println("Failed to create and write to file named \"" + filename + "\"." );
		}
		finally
		{
			if(Draw.prntStream != null)
				Draw.prntStream.close();
		}
	}
	
	/**
	 * Compares two text files and returns true if they are equal. If both files are empty. False is returned
	 * @author nedmurp
	 * @requires Both files should exist
	 * @param filename1 String : Path to the first file for comparison. File should exist and be readable
	 * @param filename2 String : Path to the second file for comparison. File should exist and be readable
	 * @return true if the files have content and contain the same strings. false if both files are empty or the files do not contain the same strings
	 */
	public static boolean CompareFiles(String filename1, String filename2)
	{
		String strFile1 = Draw.ReadTextFile(filename1);
		String strFile2 = Draw.ReadTextFile(filename2);
		
		if(strFile1.isEmpty() && strFile2.isEmpty())
			return false;
		
		return (0 == strFile1.compareTo(strFile2)) ? true : false;
	}
	
	/**
	 * Reads a text file and converts it to one string
	 * @author nedmurp
	 * @requires file should exist
	 * @param filename String : Path to the file to read. File should exist and be readable.
	 * @return A string of the files contents
	 */
	private static String ReadTextFile(String filename)
	{
		FileReader reader = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		String rString = "";
		
		try
		{
			reader = new FileReader(filename);
			br = new BufferedReader(reader);
			sb = new StringBuffer();
			
			String line = "";
			
			while((line = br.readLine()) != null)
			{
				sb.append(line).append(System.getProperty("line.separator"));
			}
			
			rString = sb.toString();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(br != null)				
					br.close();
				
				
				if(reader != null)
					reader.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		return rString;
	}
	
	/**
	 * Opens a new stream with the full path specified
	 * @author nedmurp
	 * @effects Sets the print stream to a new file output stream
	 * @param filename String : The full path of where the file should be written
	 * @throws IOException if the stream could not be created
	 */
	private static void openFileStream(String filename) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(filename);
		Draw.prntStream = new PrintStream(fos);		
		return;
	}
	
	/**
	 * Writes a string to the stream. Each string is printed on a new line
	 * @author nedmurp
	 * @effects prints the specified string on a new line in the stream
	 * @param str String : the string to write to the stream
	 */
	private static void writeString(String str)
	{
		if(Draw.prntStream != null)
		{
			Draw.prntStream.println(str);
		}
	}
	
	/**
	 * Writes a list of strings to the stream. Each item in the list is written on its own line
	 * @author nedmurp
	 * @effects Writes each item in the list of strings to its own line in the stream
	 * @param listOfStrings List<String>: a list of strings to write to the stream
	 */
	private static void writeStrings(List<String> listOfStrings)
	{
		for(String str : listOfStrings)
		{
			Draw.writeString(str);
		}
	}
	//method executes for each translate program (i.e. here postScript)
	//take a list of strings (or file stream or whatever java likes) with linebreak characters
	//write stream to file
}
