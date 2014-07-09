package message;

import java.util.ArrayList;
import java.util.List;


public class ABSInitGameMessage extends ABSMessage
{
	private int numRows;
	private int numCols;
	private List<Integer> shipSizeList;
	private boolean isSalvo;
	
	public ABSInitGameMessage()
	{
		this.shipSizeList = new ArrayList<Integer>();
		this.isSalvo = false;
	}
	
	public ABSInitGameMessage(String rawMessage)
	{
		this();
		
		if ( rawMessage.endsWith(" salvo") )
		{
			isSalvo = true;
			rawMessage = rawMessage.substring(0, rawMessage.length() - 6); //chop off " salvo" from the raw
		}
		
		String[] splitMessage = rawMessage.split(" ");
		if ( splitMessage.length != 3 )
		{
			throw new IllegalArgumentException("Expected string with three space sparated chunks. Instead got " + splitMessage.length + " such chunks: \"" + rawMessage + "\"");
		}
		
		String[] boardSizeToks = splitMessage[1].split("x");
		if ( boardSizeToks.length != 2 )
		{
			throw new IllegalArgumentException("Expected two arguments for board dimensions dimensions. Instead found " + boardSizeToks.length + " arguments in \"" + splitMessage[1] + "\"");
		}
		try
		{
			this.numRows = Integer.parseInt(boardSizeToks[0]);
			this.numCols = Integer.parseInt(boardSizeToks[1]);
		}
		catch(NumberFormatException nfEx)
		{
			throw new IllegalArgumentException(boardSizeToks[0] + " and/or " + boardSizeToks[1] + " is not a valid integer!");
		}
		
		if ( !splitMessage[2].startsWith("[") || !splitMessage[2].endsWith("]") )
		{
			throw new IllegalArgumentException("Expected ship sizes to be surrounded by \"[\" and \"]\". Instead got " + splitMessage[2]);
		}
		
		String[] shipSizeToks = splitMessage[2].substring(1, splitMessage[2].length() - 1).split(",");		
		for ( String s : shipSizeToks )
		{
			try
			{
				this.shipSizeList.add(Integer.parseInt(s));
			}
			catch(NumberFormatException nfEx)
			{
				throw new IllegalArgumentException("String \"" + s + "\" in \"" + splitMessage[2] + "\" is not a valid integer!");
			}
		}
	}
	
	public ABSInitGameMessage(int numRows, int numCols, List<Integer> shipSizeList)
	{
		this(numRows, numCols, shipSizeList, false);
	}
	
	public ABSInitGameMessage(int numRows, int numCols, List<Integer> shipSizeList, boolean isSalvo)
	{
		this.numRows = numRows;
		this.numCols = numCols;
		this.shipSizeList = shipSizeList;
		this.isSalvo = isSalvo;
	}

	@Override 
	public MsgType getMessageType() 
	{
		return MsgType.INIT_GAME;
	}

	@Override
	public String toString()
	{
		String rString = "init-game " + this.numRows + "x" + this.numCols + " [";
		for ( int i = 0 ; i < this.shipSizeList.size() ; i++ )
		{
			rString += this.shipSizeList.get(i);
			if ( i < this.shipSizeList.size() - 1 )
				rString += (",");
		}
		rString += "]";
		
		if ( isSalvo )
			rString += " salvo";
		
		return rString;
	}
	
	public void setNumRows(int rows)
	{
		this.numRows = rows;
	}
	
	public int getNumRows()
	{
		return this.numRows;
	}
	
	public void setNumColumns(int columns)
	{
		this.numCols = columns;
	}
	
	public int getNumColumns()
	{
		return this.numCols;
	}
	
	public void setShipSizeList(List<Integer> shipSizes)
	{
		this.shipSizeList = new ArrayList<Integer>(shipSizes);  //T O D O: Is this Rep exposure? // no, makes a copy -JVen
	}
	
	public List<Integer> getShipSizeList()
	{
		List<Integer> rList = new ArrayList<Integer>(this.shipSizeList);
		
		return rList;
	}
	
	public void setSalvo(boolean isSalvo)
	{
		this.isSalvo = isSalvo;
	}
	
	public boolean getSalvo()
	{
		return this.isSalvo;
	}
}
