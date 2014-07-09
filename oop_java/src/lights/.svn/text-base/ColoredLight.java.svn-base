package lights;

import java.awt.Color;

public class ColoredLight extends Light
{
	
	private Color lightColor;
	/**
	 * Creates a new colored light. isOn == false
	 * @param color - color of this light.
	 */
	public ColoredLight(Color color) 
	{
		this(false, color);
	}
	
	/**
	 * Creates a new colored light.
	 * @param isOn - Whether the light is on
	 * @param color - the color of the light
	 */
	public ColoredLight(boolean isOn, Color color)
	{
		super(isOn);
		this.lightColor = color;
	}
	
	/**
	 * Returns the color of this light.
	 * @return color of this light.
	 */
	public Color getColor() {
		return this.lightColor;
	}
	
	/**
	 * Changes the color of this light to be c.
	 */
	public void setColor(Color c) {
		this.lightColor = c;
	}
	
	/**
	 * Randomly changes this light to be on or off and its color.
	 */
	@Override
	public void randomChange()
	{		
		// Take advantage of Light.randomChange
		super.randomChange();
		this.lightColor = ColoredLight.getRandomColor();		
	}
	
	public static Color getRandomColor()
	{
		Color randColor;
		int rVal = (int)(Math.random() * 255);
		int gVal = (int)(Math.random() * 255);
		int bVal = (int)(Math.random() * 255);
		randColor = new Color(rVal, gVal, bVal);
		return randColor;
	}
	
}