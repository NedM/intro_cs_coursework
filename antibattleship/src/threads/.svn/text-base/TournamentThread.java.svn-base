package threads;

import players.NetworkPlayer;
import client.ABSGame;

public class TournamentThread implements Runnable
{

	public enum NetworkPlayerType
	{
		INITIATING,
		RECEIVING,
		NONE
	}
	
	private final ABSGame game;
	private final NetworkPlayer player;
	private final NetworkPlayerType type;
	private final String hostname;
	private final int port;
	
	public TournamentThread ( ABSGame game )
	{
		this(game, null, NetworkPlayerType.NONE, null, -1);
	}
	
	public TournamentThread ( ABSGame game, NetworkPlayer p, NetworkPlayerType t, String hostname, int port )
	{
		this.game = game;
		this.player = p;
		this.type = t;
		this.hostname = hostname;
		this.port = port;
	}
	
	@Override
	public void run()
	{
		if ( this.type == NetworkPlayerType.RECEIVING )
			this.player.ConnectAsClient(hostname, port);
		else if ( this.type == NetworkPlayerType.INITIATING )
			this.player.HostGame(port);
		
		// gl hf
		
		game.play();
		
		// be a good boy scout
		
		if ( this.player != null )
			this.player.cleanUpNetworkPlayer();
	}
	
}
