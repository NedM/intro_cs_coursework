package message;

import java.util.*;


public class ABSResultsMessage extends ABSMessage
{
	private List<ResultType> outcomes;
	public ABSResultsMessage()
	{
		this.outcomes = new ArrayList<ResultType>();
	}
	
	public ABSResultsMessage(String rawMessage)
	{
		this();
		ResultType tempResult;
		String outcome;
		String[] splitRawMessage = rawMessage.split(" ");
		
		if ( splitRawMessage.length < 2 )
		{
			throw new IllegalArgumentException("Expected at least 2 space separated message blobs but found " + splitRawMessage.length);
		}
		
		for(int i = 1; i < splitRawMessage.length; i++)
		{
			outcome = splitRawMessage[i];
			if ( outcome.equals("water") )
			{
				tempResult = ResultType.MISS;
			}
			else if ( outcome.equals("fire") )
			{
				tempResult = ResultType.HIT;
			}
			else if ( outcome.equals("sunk") )
			{
				tempResult = ResultType.SUNK;
			}
			else
			{
//				continue;
				throw new IllegalArgumentException("Expected space separated message blob " + i + " to be one of \"water, fire, sunk\" but was " + splitRawMessage[i]);
			}
			this.outcomes.add(tempResult);
		}			
	}

	@Override
	public MsgType getMessageType()
	{
		return MsgType.RESULTS;
	}
	
	@Override
	public String toString()
	{
		String resultsAns = "results";
		for ( ResultType outcome : this.outcomes )
		{
			switch ( outcome )
			{
				case MISS:
					resultsAns += " water";
					break;
				case HIT:
					resultsAns += " fire";
					break;
				case SUNK:
					resultsAns += " sunk";
					break;
			}
		}
		return resultsAns;
	}
	
	/**
	 * Sets the outcomes to a new list of results
	 * @author nedmurp
	 * @param outcomes a list of ResultTypes that represents the outcomes to store
	 */
	public void setOutcomes(List<ResultType> outcomes)
	{
		this.outcomes = new ArrayList<ResultType>(outcomes);
	}
	
	public void setSingleOutcome(ResultType outcome)
	{
		List<ResultType> tempList = new ArrayList<ResultType>();
		tempList.add(outcome);
		setOutcomes(tempList);
	}
	
	/**
	 * Appends outcomes to the list of outcomes already set
	 * @author nedmurp
	 * @param additionalOutcomes a list of ResultTypes that represents outcomes to add
	 */
	public void addOutcomes(List<ResultType> additionalOutcomes)
	{
		this.outcomes.addAll(additionalOutcomes);
	}
	
	public void addSingleOutcome(ResultType additionalOutcome)
	{
		this.outcomes.add(additionalOutcome);
	}
	
	/**
	 * Gets a list of outcomes resulting from a Salvo attack
	 * @author nedmurp
	 * @return A list of ResultTypes
	 */
	public List<ResultType> getOutcomes()
	{
		List<ResultType> rList = new ArrayList<ResultType>(this.outcomes);
		
		return rList;
	}
	
	/**
	 * Gets a single outcome resulting from a single attack
	 * @author nedmurp
	 * @return A ResultType
	 */
	public ResultType getOutcome()
	{
		ResultType rType = this.outcomes.get(0);
		
		return rType;
	}
}
