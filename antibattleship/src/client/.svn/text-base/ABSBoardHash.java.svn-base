package client;

import java.security.*;

/**
 * 
 * Contains static methods for use in generating board hashes
 * 
 * @author 6005StaffSP11
 *
 */

public class ABSBoardHash
{

	private static String convertToHex ( byte[] data )
	{
		StringBuffer buf = new StringBuffer();
		for ( int i = 0; i < data.length; i++ )
		{

			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;

			do
			{

				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));

				halfbyte = data[i] & 0x0F;

			}
			while ( two_halfs++ < 1 );
		}
		return buf.toString();
		
	}

	public static String computeBoardHash ( String boardRep, int rows, int columns, int salt )
	{
		MessageDigest md = null;
		try
		{
			 md = MessageDigest.getInstance("SHA");
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			System.exit(-1);
		}
		String board = boardRep + rows + columns + salt;
		byte[] bytes = board.getBytes();
		md.update(bytes);
		return convertToHex(md.digest());
	}
	
	/*
	public static void main ( String[] args )
	{
		System.out.println(computeBoardHash("1100000000000000000011100000000000000000111000000000000000001111000000000000000011111000000000000000", 10, 10, 13));
	}
	*/
	
}