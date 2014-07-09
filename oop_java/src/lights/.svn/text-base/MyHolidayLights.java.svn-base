package lights;

import java.util.ArrayList;
import java.util.List;

public class MyHolidayLights  implements HolidayLights
{
	private List<ColoredLight> lightArray;
	
	/**
	 * Creates new running holiday lights.
	 * @param length - length of this set of lights.
	 */
	public MyHolidayLights(int length)
	{		
		this.lightArray = new ArrayList<ColoredLight>();
		for(int i = 0; i < length; i++)
		{
			this.lightArray.add(new ColoredLight(false, ColoredLight.getRandomColor()));
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
		List<Light> changingLights = new ArrayList<Light>();
		for(ColoredLight cL : this.lightArray)
		{
			cL.randomChange();			
			changingLights.add(cL);
		}
				
		return changingLights;
	}

}
