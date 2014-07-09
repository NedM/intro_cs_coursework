package threads;

import gui.*;
import java.io.*;
import java.net.*;

/**
 * 
 * Makes a Wait message in a new thread
 * 
 * @author JVen
 *
 */

public class WaitThread implements Runnable 
{

	private WaitMessage waitMessage;
	private final String msg;
	private boolean ready;
	
	public WaitThread ( String msg )
	{
		this.msg = msg;
		this.ready = false;
	}
	
	@Override
	public void run()
	{
		waitMessage = new WaitMessage(msg);
		
		// wait a little bit
		
		try
		{
			Thread.sleep(200);
		}
		catch ( Exception e )
		{
		}
		
		this.ready = true;
	}
	
	public boolean isReady()
	{
		return this.ready;
	}
	
	public WaitMessage getWaitMessage()
	{
		return this.waitMessage;
	}
	
}
