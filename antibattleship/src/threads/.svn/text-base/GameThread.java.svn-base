package threads;

import players.ABSPlayer;
import players.NetworkPlayer;
import client.ABSGame;

public class GameThread implements Runnable
{

	private final ABSGame game;
	private final ABSPlayer player;
	
	public GameThread ( ABSGame game, ABSPlayer player )
	{
		this.game = game;
		this.player = player;
	}
	
	@Override
	public void run()
	{
		game.play();
		if(this.player instanceof NetworkPlayer)
		{
			((NetworkPlayer)this.player).cleanUpNetworkPlayer();
		}
	}
	
}
