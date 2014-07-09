package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import message.*;

import org.junit.Test;

import client.ABSBoardSquare;
import client.ABSGame;


public class ABSTest 
{		
	private int makeRandomInt(int min, int max)
	{
		return (int)((Math.random() * (max - min)) + min);
	}	
	
	private List<Integer> makeShipSizeList(int numShips, int numRows, int numCols)
	{		
		int maxShipLength = ((numRows > numCols) ? numCols : numRows);
		List<Integer> shipSizeList = new ArrayList<Integer>();
		
		for(int i = 0; i < numShips; i++)
		{
			int length = makeRandomInt(1, maxShipLength);
			shipSizeList.add(length);
		}
		
		return shipSizeList;
	}
	
	@Test
	public void testCreateAcceptVictoryMessage()
	{
		ABSAcceptVictoryMessage avm = new ABSAcceptVictoryMessage();
		try
		{
			ABSMessage m = ABSMessage.CreateMessage("accept-victory");
			Assert.assertNotNull(m);
			Assert.assertEquals(avm.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); // Should not reach here
		}
	}
	
	@Test
	public void testCreateBoardHashMessage()
	{
		String hash = "abcdefghijklmnopqrstuvwxyz1234567890";
		ABSBoardHashMessage bhm = new ABSBoardHashMessage();
		bhm.setBoardHash(hash);
		try
		{
			ABSMessage m = ABSMessage.CreateMessage("board-hash " + hash);
			Assert.assertNotNull(m);
			Assert.assertEquals(bhm.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); // Should not reach here
		}
	}
	
	@Test
	public void testCreateGameAcceptMessage()
	{		
		ABSGameAcceptMessage gam = new ABSGameAcceptMessage();
		
		try
		{
			ABSMessage m = ABSMessage.CreateMessage("accept-game");
			Assert.assertNotNull(m);
			Assert.assertEquals(gam.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); // Should not reach here
		}
	}
	
	@Test
	public void testCreateGameDenyMessage()
	{		
		ABSGameDenyMessage gdm = new ABSGameDenyMessage();
		
		try
		{
			ABSMessage m = ABSMessage.CreateMessage("deny-game");
			Assert.assertNotNull(m);
			Assert.assertEquals(gdm.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); // Should not reach here
		}
	}
	
	@Test
	public void testCreateGameErrorMessage()
	{		
		String error = "blahblah";
		ABSGameErrorMessage gem = new ABSGameErrorMessage();
		gem.setErrorMessage(error);
		try
		{
			ABSMessage m = ABSMessage.CreateMessage("game-error " + error);
			Assert.assertNotNull(m);
			Assert.assertEquals(gem.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); // Should not reach here
		}
	}
	
	@Test
	public void testCreateInitGameMessage()
	{
		int rows = makeRandomInt(1, 10000);
		int cols = makeRandomInt(1, 10000);
		int numShips = makeRandomInt(1, 10000);
		int maxShipLength = ((rows > cols) ? cols : rows);
		String shipLengths = "";
		List<Integer> shipSizeList = new ArrayList<Integer>();
		
		for(int i = 0; i < numShips; i++)
		{
			int length = makeRandomInt(1, maxShipLength);
			shipSizeList.add(length);
			shipLengths = shipLengths.concat("" + length);
			if(i + 1 < numShips)
			{
				shipLengths = shipLengths.concat(",");
			}
		}
		
		try 
		{
			ABSInitGameMessage igm = new ABSInitGameMessage();
			igm.setNumColumns(cols);
			igm.setNumRows(rows);
			igm.setShipSizeList(shipSizeList);
			
			ABSMessage m = ABSMessage.CreateMessage("init-game " + rows + "x" + cols + " [" + shipLengths + "]");
			Assert.assertNotNull(m);
			Assert.assertEquals(igm.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); //Should not reach here
		}
	}
	
	@Test
	public void testCreateRejectVictoryMessage()
	{		
		ABSRejectVictoryMessage rvm = new ABSRejectVictoryMessage();

		try
		{
			ABSMessage m = ABSMessage.CreateMessage("reject-victory");
			Assert.assertNotNull(m);
			Assert.assertEquals(rvm.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); // Should not reach here
		}
	}
	
	@Test
	public void testCreateResultsMessage()
	{		
		ABSResultsMessage rvm = new ABSResultsMessage();
		int numResults = this.makeRandomInt(1, 10000);
		List<ResultType> results = new ArrayList<ResultType>();
		String resultsString = "";
		for(int i = 0; i < numResults; i++)
		{
			int result = this.makeRandomInt(0, 3);
			if(result == 0)
			{
				results.add(ResultType.HIT);
				resultsString = resultsString.concat(" fire");
			}
			else if(result == 1)
			{
				results.add(ResultType.SUNK);
				resultsString = resultsString.concat(" sunk");
			}
			else
			{
				results.add(ResultType.MISS);
				resultsString = resultsString.concat(" water");
			}
		}		
		rvm.addOutcomes(results);
		
		try
		{
			ABSMessage m = ABSMessage.CreateMessage("results" + resultsString);
			Assert.assertNotNull(m);
			Assert.assertEquals(rvm.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); // Should not reach here
		}
	}
	
	@Test
	public void testCreateSyntaxErrorMessage()
	{		
		ABSSyntaxErrorMessage sem = new ABSSyntaxErrorMessage();
		String error = "blahblahblah";
		sem.setErrorMessage(error);

		try
		{
			ABSMessage m = ABSMessage.CreateMessage("syntax-error " + error);
			Assert.assertNotNull(m);
			Assert.assertEquals(sem.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); // Should not reach here
		}
	}
	
	@Test
	public void testCreateTargetMessage()
	{
		ABSTargetMessage tm = new ABSTargetMessage();
		int numTargets = this.makeRandomInt(1, 10000);
		List<ABSBoardSquare> targets = new ArrayList<ABSBoardSquare>();
		String targetString = "";
		
		for(int i = 0; i < numTargets; i++)
		{
			ABSBoardSquare sq = new ABSBoardSquare(this.makeRandomInt(0, 1000), this.makeRandomInt(0, 1000));
			targets.add(sq);
			targetString = targetString.concat(" (" + sq.getRow() + "," + sq.getColumn() + ")");
		}
		tm.setTargets(targets);
		try
		{
			ABSMessage m = ABSMessage.CreateMessage("target" + targetString);
			Assert.assertNotNull(m);
			Assert.assertEquals(tm.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); // Should not reach here
		}
	}
	
	@Test
	public void testCreateVictoryMessage()
	{		
		ABSVictoryMessage vm = new ABSVictoryMessage();
		String boardState = "abcdefghijklmnopqrstuvwxyz1234567890";
		int salt = this.makeRandomInt(0, 100000);
		vm.setBoardState(boardState);
		vm.setSalt(salt);

		try
		{
			ABSMessage m = ABSMessage.CreateMessage("victory " + salt + " " + boardState);
			Assert.assertNotNull(m);
			Assert.assertEquals(vm.toString(), m.toString());
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); // Should not reach here
		}
	}
	
	@Test
	public void testMessageGarbage()
	{
		try 
		{
			ABSMessage m = ABSMessage.CreateMessage("sdllka;sldkj;laksdn");
			Assert.fail(); //Should not reach here
		} 
		catch (InvalidMessageException e) 
		{
			Assert.assertTrue(true); //Should reach here!
		}
		
	}
	
	@Test
	public void testMessageBadSyntaxGarbage()
	{				
		try 
		{			
			ABSMessage m = ABSMessage.CreateMessage("board-hash ");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("init-game lskdja;lksnv;laisiji");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("init-game 3y4 [2,3]");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("init-game 3x4x5 [2,3]");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("init-game 3x4 (2,3)");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("results sldljl");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("results earth sank");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("results ");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("target lskdja;lksnv;laisiji");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("target [1,2]");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("target (1,2,3)");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("target 1,2 2,3");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("target (1,3))");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("target ");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("victory lskdja;lksnv;laisiji");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("victory asdlkj 345");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("victory 23.4 asdlkljaskld");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("victory 123");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("victory asdlkj");
			Assert.assertNull(m);  //m should be null
			
			m = ABSMessage.CreateMessage("victory ");
			Assert.assertNull(m);  //m should be null
		} 
		catch (InvalidMessageException e) 
		{
			Assert.fail(); //Should not reach here
		}
	}
	
	public void testInitGame()
	{
		TestPlayer t1 = new TestPlayer();
		TestPlayer t2 = new TestPlayer();
		ABSGame game = new ABSGame(t1, t2);
		try 
		{
			ABSInitGameMessage init = new ABSInitGameMessage();
			init.setNumColumns(this.makeRandomInt(1, 1000));
			init.setNumRows(this.makeRandomInt(1, 1000));
			init.setSalvo(false);
			init.setShipSizeList(this.makeShipSizeList(this.makeRandomInt(1, 1000), init.getNumRows(), init.getNumColumns()));
			t1.sendMsgToPlayer(init);
			game.play();		
		} 
		catch (Exception e) 
		{
			Assert.fail(); //Should not reach here
		}
	}
}
