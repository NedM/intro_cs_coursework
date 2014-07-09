package lights;

import org.junit.Assert;
import org.junit.Test;

public class MyHolidayLightsTests 
{	
	@Test public void MakeHolidayLights()
	{
		MyHolidayLights myHL = new MyHolidayLights(5);
		int numLights = myHL.getRunningLights().size();
		Assert.assertTrue(numLights == 5);
	}
	
	@Test public void MakeHolidaysLightsOff()
	{
		MyHolidayLights myHL = new MyHolidayLights(5);
		for(Light l : myHL.getRunningLights())
		{
			Assert.assertFalse(l.isOn());			
		}
	}
	
	@Test public void Make10Lights()
	{
		MyHolidayLights myHL = new MyHolidayLights(10);
		Assert.assertTrue(10 == myHL.getNumberOfLights());
	}
}
 