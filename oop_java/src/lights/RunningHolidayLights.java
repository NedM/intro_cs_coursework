package lights;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class RunningHolidayLights implements HolidayLights 
{
	private int currentIndex;
	private int previousIndex;
	private List<Light> lightArray;
	
	/**
	 * Creates new running holiday lights.
	 * @param length - length of this set of lights.
	 */
	public RunningHolidayLights(int length)
	{
		this.currentIndex = 0;	
		this.previousIndex = 0;
		
		this.lightArray = new ArrayList<Light>();
		for(int i = 0; i < length; i++)
		{
			this.lightArray.add(new ColoredLight(false, Color.red));
		}			
	}
	
	public List<Light> getRunningLights()
	{
		List<Light> rLights = new ArrayList<Light>(this.lightArray.size());
		rLights.addAll(this.lightArray);
		return rLights;
	}
	
	public int getNumberOfLights()
	{
		return this.lightArray.size();
	}	
	
	public List<Light> nextLightSequence()
	{		
		if(this.currentIndex != this.previousIndex)
		{
			if(previousIndex >= 0)
			{
				Light l = this.lightArray.get(previousIndex);
				if(l.isOn())
				{
					l.setOn(false);
				}
				else
				{
					System.out.println("Expected light at " + previousIndex + " to be on but it was off!");
				}			
			}
		}
		
		if(this.currentIndex >= 0)
		{
			Light l = this.lightArray.get(this.currentIndex);
			if(!l.isOn())
			{
				l.setOn(true);
			}
			else
			{
				System.out.println("Expected light at " + this.currentIndex + " to be off but it was on!");
			}
		}
		
		this.previousIndex = this.currentIndex;
		this.currentIndex++;
		if(this.currentIndex == this.lightArray.size())
		{
			this.currentIndex = 0;
		}
		
		return this.lightArray;
	}						
}