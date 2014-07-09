package message;

import java.util.*;

import client.ABSBoardSquare;

public class ABSTargetMessage extends ABSMessage
{
	private List<ABSBoardSquare> listTargets;
	
	public ABSTargetMessage()
	{
		this.listTargets = new ArrayList<ABSBoardSquare>();
	}
	
	public ABSTargetMessage(String rawMessage)
	{
		this();
		ABSBoardSquare newTarget;
		
		String[] splitRawMessage = rawMessage.split(" ");
		if ( splitRawMessage.length < 2 )
		{
			throw new IllegalArgumentException("Expected at least 2 space separated message blobs but found " + splitRawMessage.length);
		}
		
		for(int i = 1; i < splitRawMessage.length; i++)
		{
			if ( !splitRawMessage[i].startsWith("(") || !splitRawMessage[i].endsWith(")") )
			{
//				continue;
				throw new IllegalArgumentException("Expected space separated blob " + i + " to be surrounded by \"(\" and \")\". Instead blob was \"" + splitRawMessage[i] + "\"");
			}
			String[] posToks = splitRawMessage[i].substring(1, splitRawMessage[i].length() - 1).split(",");
			if ( posToks.length != 2 )
			{
//				continue;
				throw new IllegalArgumentException("Expected space separated blob " + i + " to have format \"(row#, column#)\". Instead was " + splitRawMessage[i]);
			}
			
			try
			{
				newTarget = new ABSBoardSquare(Integer.parseInt(posToks[0]), Integer.parseInt(posToks[1]));				
			}
			catch(NumberFormatException nfEx)
			{
//				continue;
				throw new IllegalArgumentException("Expected space separated blob " + i + " to contain valid (row#,column#) coordinates. " + posToks[0] + " and/or " + posToks[1] + " is not a valid integer!");
			}		
			
			this.listTargets.add(newTarget);
		}
	}
	
	@Override
	public MsgType getMessageType()
	{
		return MsgType.TARGET;
	}
	
	@Override
	public String toString()
	{
		String rString = "target";
		for(ABSBoardSquare sq : this.listTargets)
		{
			rString += " (" + sq.getRow() + "," + sq.getColumn() + ")";
		}
		
		return rString;
	}

	public void setTargets(List<ABSBoardSquare> targets)
	{
		this.listTargets = new ArrayList<ABSBoardSquare>(targets);
	}
	
	public void setSingleTarget(ABSBoardSquare sq)
	{
		List<ABSBoardSquare> tempList = new ArrayList<ABSBoardSquare>();
		tempList.add(sq);
		setTargets(tempList);
	}
	
	public void addTargets(List<ABSBoardSquare> additionalTargets)
	{
		this.listTargets.addAll(additionalTargets);
	}
	
	public void addSingleTarget(ABSBoardSquare sq)
	{
		this.listTargets.add(sq);
	}
	
	public List<ABSBoardSquare> getListOfTargets()
	{
		List<ABSBoardSquare> rList = new ArrayList<ABSBoardSquare>(this.listTargets);
		
		return rList;
	}
	
	public ABSBoardSquare getSingleTarget()
	{
		ABSBoardSquare rSquare = this.listTargets.get(0);
		
		return rSquare;
	}
}
