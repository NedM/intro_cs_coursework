package img;

import javax.swing.*;

/**
 * 
 * Image library for GUI
 * 
 * @author JVen
 *
 */

public class ImageLibrary
{

	public static final int BOARD_ICON_SIZE = 60;
	
	private static final int NUM_UNHIT_WATER_ICONS = 1;
	private static final int NUM_HIT_WATER_ICONS = 1;
	private static final int NUM_UNHIT_SHIP_ICONS = 6;
	private static final int NUM_HIT_SHIP_ICONS = 2;
	private static final int NUM_SUNK_SHIP_ICONS = 1;
	private static final int NUM_UNKNOWN_ICONS = 1;
	
	private static ImageIcon createImageIcon ( String path, String description )
	{
		java.net.URL imgURL = ImageLibrary.class.getResource(path);
		return new ImageIcon(imgURL, description);
	}
	
	public static ImageIcon getUnhitWaterIcon()
	{
		int imgNum = 1 + (int)((NUM_UNHIT_WATER_ICONS - 1) * Math.random());
		return getUnhitWaterIcon(imgNum);
	}
	
	public static ImageIcon getUnhitWaterIcon ( int imgNum )
	{
		imgNum = (imgNum % NUM_UNHIT_WATER_ICONS ) + 1;
		return createImageIcon("unhit_water_" + imgNum + ".gif", " ");
	}
	
	public static ImageIcon getHitWaterIcon()
	{
		int imgNum = 1 + (int)((NUM_HIT_WATER_ICONS - 1) * Math.random());
		return getHitWaterIcon(imgNum);
	}
	
	public static ImageIcon getHitWaterIcon ( int imgNum )
	{
		imgNum = (imgNum % NUM_HIT_WATER_ICONS) + 1; 
		return createImageIcon("hit_water_" + imgNum + ".gif", "~");
	}
	
	public static ImageIcon getUnhitShipIcon()
	{
		int imgNum = 1 + (int)((NUM_UNHIT_SHIP_ICONS - 1) * Math.random());
		return getUnhitShipIcon(imgNum);
	}
	
	public static ImageIcon getUnhitShipIcon ( int imgNum )
	{
		imgNum = (imgNum % NUM_UNHIT_SHIP_ICONS) + 1;
		return createImageIcon("unhit_ship_" + imgNum + ".gif", "o");
	}
	
	public static ImageIcon getHitShipIcon()
	{
		int imgNum = 1 + (int)((NUM_HIT_SHIP_ICONS - 1) * Math.random());
		return getHitShipIcon(imgNum);
	}
	
	public static ImageIcon getHitShipIcon ( int imgNum )
	{
		imgNum = (imgNum % NUM_HIT_SHIP_ICONS) + 1;
		return createImageIcon("hit_ship_" + imgNum + ".gif", "x");
	}
	
	public static ImageIcon getSunkShipIcon()
	{
		int imgNum = 1 + (int)((NUM_SUNK_SHIP_ICONS - 1) * Math.random());
		return getSunkShipIcon(imgNum);
	}
	
	public static ImageIcon getSunkShipIcon ( int imgNum )
	{
		imgNum = (imgNum % NUM_SUNK_SHIP_ICONS) + 1;
		return createImageIcon("sunk_ship_" + imgNum + ".gif", "-");
	}
	
	public static ImageIcon getUnknownIcon()
	{
		int imgNum = 1 + (int)((NUM_UNKNOWN_ICONS - 1) * Math.random());
		return getUnknownIcon(imgNum);
	}
	
	public static ImageIcon getUnknownIcon ( int imgNum )
	{
		imgNum = (imgNum % NUM_UNKNOWN_ICONS) + 1;
		return createImageIcon("unknown_" + imgNum + ".gif", "?");
	}
	
	public static ImageIcon getTitle()
	{
		return createImageIcon("title.jpg", "ANTIBATTLESHIP");
	}
	
	public static ImageIcon getExitIcon()
	{
		return createImageIcon("exit.jpg", "exit");
	}
	
	public static ImageIcon getVictoryImg()
	{
		return createImageIcon("victory.jpg", "victory");
	}
	
	public static ImageIcon getDefeatImg()
	{
		return createImageIcon("defeat.jpg", "defeat");
	}
	
	public static ImageIcon getErrorImg()
	{
		return createImageIcon("error.jpg", "error");
	}
	
	public static ImageIcon getWaitImg()
	{
		return createImageIcon("wait.jpg", "wait");
	}
	
	public static ImageIcon getJasperImg()
	{
		return createImageIcon("jasper.jpg", "jasper");
	}
	
	public static ImageIcon getOpponentImg()
	{
		return createImageIcon("opponent.jpg", "opponent");
	}
	
	public static ImageIcon getJasperStateImg ( String color )
	{
		return createImageIcon("jasper_state_" + color + ".jpg", "jasper state " + color);
	}
	
	public static ImageIcon getNumHitsImg()
	{
		return createImageIcon("numHits.jpg", "numHits");
	}
	
}
