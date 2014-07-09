package leaderboard;

import java.util.*;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.simpledb.*;
import com.amazonaws.services.simpledb.model.*;

/**
 * 
 * Connects to database that maps usernames to win/loss record
 * 
 * @author JVen
 *
 */

public class Leaderboard
{
	
	public static List<UserStats> getStats()
	{
		
		AmazonSimpleDB sdb = null;
		
		// connect to leaderboard
		
		try
		{
			sdb = new AmazonSimpleDBClient(new PropertiesCredentials(Leaderboard.class.getResourceAsStream("AwsCredentials.properties")));
		}
		catch ( Exception e )
		{
			System.err.println("Could not connect to leaderboard.");
			return new ArrayList<UserStats>();
		}
		
		// get domain
		
		String domain = "Leaderboard";
		sdb.createDomain(new CreateDomainRequest(domain));
		
		// get previous entry, if it exists
		
		String selectExpression = "select * from `" + domain + "`";
        SelectRequest selectRequest = new SelectRequest(selectExpression);
        
        // generate UserStats
        
        List<UserStats> allStats = new ArrayList<UserStats>();
        
        for ( Item item : sdb.select(selectRequest).getItems() )
        {
        	String username = item.getName();
        	int wins = 0;
        	int losses = 0;
    		for ( Attribute attribute : item.getAttributes() )
    		{
    			if ( attribute.getName().equals("Wins") )
    				wins = Integer.parseInt(attribute.getValue());
    			else if ( attribute.getName().equals("Losses") )
    				losses = Integer.parseInt(attribute.getValue());
            }
    		UserStats userStats = new UserStats(username, wins, losses);
    		allStats.add(userStats);
        }
        
        return new ArrayList<UserStats>(allStats);
		
	}
	
	public static void reportWin ( String username )
	{
		
		AmazonSimpleDB sdb = null;
		
		// connect to leaderboard
		
		try
		{
			sdb = new AmazonSimpleDBClient(new PropertiesCredentials(Leaderboard.class.getResourceAsStream("AwsCredentials.properties")));
		}
		catch ( Exception e )
		{
			System.err.println("Could not connect to leaderboard.");
			return;
		}
		
		// get domain
		
		String domain = "Leaderboard";
		sdb.createDomain(new CreateDomainRequest(domain));
		
		// get previous entry, if it exists
		
		int prevWins = 0;
		
		String selectExpression = "select * from `" + domain + "`";
        SelectRequest selectRequest = new SelectRequest(selectExpression);
        
        for ( Item item : sdb.select(selectRequest).getItems() )
        {
        	if ( item.getName().equals(username) )
        	{
        		for ( Attribute attribute : item.getAttributes() )
        		{
        			if ( attribute.getName().equals("Wins") )
        				prevWins = Integer.parseInt(attribute.getValue());
                }
        	}
        }
        
        // replace it with new entry
		
		List<ReplaceableAttribute> replaceableAttributes = new ArrayList<ReplaceableAttribute>();
        replaceableAttributes.add(new ReplaceableAttribute("Wins", "" + (prevWins + 1), true));
        sdb.putAttributes(new PutAttributesRequest(domain, username, replaceableAttributes));
        
        // wait to update
        
        try
        {
			Thread.sleep(1000);
		}
        catch ( InterruptedException e )
        {
        	System.err.println("Could not connect to leaderboard.");
			return;
		}
        
	}
	
	public static void reportLoss ( String username )
	{
		
		AmazonSimpleDB sdb = null;
		
		// connect to leaderboard
		
		try
		{
			sdb = new AmazonSimpleDBClient(new PropertiesCredentials(Leaderboard.class.getResourceAsStream("AwsCredentials.properties")));
		}
		catch ( Exception e )
		{
			System.err.println("Could not connect to leaderboard.");
			return;
		}
		
		// get domain
		
		String domain = "Leaderboard";
		sdb.createDomain(new CreateDomainRequest(domain));
		
		// get previous entry, if it exists
		
		int prevLosses = 0;
		
		String selectExpression = "select * from `" + domain + "`";
        SelectRequest selectRequest = new SelectRequest(selectExpression);
        
        for ( Item item : sdb.select(selectRequest).getItems() )
        {
        	if ( item.getName().equals(username) )
        	{
        		for ( Attribute attribute : item.getAttributes() )
        		{
        			if ( attribute.getName().equals("Losses") )
        				prevLosses = Integer.parseInt(attribute.getValue());
                }
        	}
        }
        
        // replace it with new entry
		
		List<ReplaceableAttribute> replaceableAttributes = new ArrayList<ReplaceableAttribute>();
        replaceableAttributes.add(new ReplaceableAttribute("Losses", "" + (prevLosses + 1), true));
        sdb.putAttributes(new PutAttributesRequest(domain, username, replaceableAttributes));
        
        // wait to update
        
        try
        {
			Thread.sleep(1000);
		}
        catch ( InterruptedException e )
        {
        	System.err.println("Could not connect to leaderboard.");
			return;
		}
        
	}
	
	public static void main ( String[] args )
	{
		reportWin("jven");
	}
	
}
