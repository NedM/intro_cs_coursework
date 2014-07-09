package players.jasper;

import java.io.*;
import client.*;

/**
 * 
 * Generate board hashes for given board sizes with only one 1x1 ship
 * WARNING: The files output by this method can get very large :)
 * 
 * @author JVen
 *
 */

public class HashBreak
{

	public static void main ( String[] args )
	{
		
		int minMaxRow = 3;
		int minMaxCol = 3;
		int maxMaxRow = 3;
		int maxMaxCol = 3;
		
		System.out.println("Generating board hashes...");
		
		for ( int maxRow = minMaxRow ; maxRow <= maxMaxRow ; maxRow++ )
		{
			for ( int maxCol = minMaxCol ; maxCol <= maxMaxCol ; maxCol++ )
			{
				
				System.out.println("");
				System.out.println("Board size: " + maxRow + "x" + maxCol);
				
				PrintStream printStream = null;
				
				try
				{
					String jasperPath = "/home/jven/Documents/abs/boardHashes/";
					String jvenPath = "C:\\Users\\Justin\\Documents\\My Dropbox\\6.005\\";
					
					// change this as necessary
					String path = jvenPath;
					printStream = new PrintStream(new FileOutputStream(path + maxRow + "x" + maxCol + ".txt"));
				}
				catch ( Exception e )
				{
					e.printStackTrace();
					System.exit(-1);
				}
				
				for ( int shipLoc = 0 ; shipLoc < maxRow * maxCol ; shipLoc++ )
				{
					
					// get boardRep
					
					String boardRep = "";
					for ( int zeroLoc = 0; zeroLoc < maxRow * maxCol ; zeroLoc++ )
						boardRep += "0";
					boardRep = boardRep.substring(0, shipLoc) + "1" + boardRep.substring(shipLoc + 1, maxRow * maxCol);
					
					System.out.println("");
					System.out.println("boardRep: " + boardRep);
					System.out.println("Percent complete...");
					
					int numSalts = 1000000; // divisible by 4 plz
					int currSaltNum = 0;
					
					// salts (minInt) to (minInt) + (numSalts / 4) - 1
					
					for ( int salt = Integer.MIN_VALUE ; salt < Integer.MIN_VALUE + (numSalts / 4) ; salt++ )
					{
						
						if ( currSaltNum % (numSalts / 100) == 0 )
						{
							if ( (currSaltNum / (numSalts / 100)) < 10 )
								System.out.print(" 0" + (currSaltNum / (numSalts / 100)));
							else
								System.out.print(" " + (currSaltNum / (numSalts / 100)));
							if ( (currSaltNum / (numSalts / 100)) % 10 == 9 )
								System.out.println("");
						}
						
						// write result as "boardRep salt boardHash"
						
						String boardHash = ABSBoardHash.computeBoardHash(boardRep, maxRow, maxCol, salt);
						try
						{
							printStream.println(boardRep + " " + salt + " " + boardHash);
						}
						catch ( Exception e )
						{
							printStream.close();
							e.printStackTrace();
							System.exit(-1);
						}
						
						currSaltNum++;
						
					}
					
					// salts -(numSalts / 4) to (numSalts / 4)
					
					for ( int salt = -(numSalts / 4) ; salt < (numSalts / 4) ; salt++ )
					{
						
						if ( currSaltNum % (numSalts / 100) == 0 )
						{
							if ( (currSaltNum / (numSalts / 100)) < 10 )
								System.out.print(" 0" + (currSaltNum / (numSalts / 100)));
							else
								System.out.print(" " + (currSaltNum / (numSalts / 100)));
							if ( (currSaltNum / (numSalts / 100)) % 10 == 9 )
								System.out.println("");
						}
						
						// write result as "boardRep salt boardHash"
						
						String boardHash = ABSBoardHash.computeBoardHash(boardRep, maxRow, maxCol, salt);
						try
						{
							printStream.println(boardRep + " " + salt + " " + boardHash);
						}
						catch ( Exception e )
						{
							printStream.close();
							e.printStackTrace();
							System.exit(-1);
						}
						
						currSaltNum++;
						
					}
					
					// salts (maxInt) - (numSalts / 4) + 1 to (maxInt)
					
					for ( int salt = Integer.MAX_VALUE - (numSalts / 4) + 1 ; salt <= Integer.MAX_VALUE && salt > 0 ; salt++ )
					{
						
						if ( currSaltNum % (numSalts / 100) == 0 )
						{
							if ( (currSaltNum / (numSalts / 100)) < 10 )
								System.out.print(" 0" + (currSaltNum / (numSalts / 100)));
							else
								System.out.print(" " + (currSaltNum / (numSalts / 100)));
							if ( (currSaltNum / (numSalts / 100)) % 10 == 9 )
								System.out.println("");
						}
						
						// write result as "boardRep salt boardHash"
						
						String boardHash = ABSBoardHash.computeBoardHash(boardRep, maxRow, maxCol, salt);
						try
						{
							printStream.println(boardRep + " " + salt + " " + boardHash);
						}
						catch ( Exception e )
						{
							printStream.close();
							e.printStackTrace();
							System.exit(-1);
						}
						
						currSaltNum++;
						
					}
					
				}
				
				System.out.println("");
				printStream.close();
				
			}
		}
		
	}
	
}
