package lights;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class RunningHolidayLightsTest 
{	
	@Test public void MakeHolidayLights()
	{
		RunningHolidayLights rHL = new RunningHolidayLights(5);
		int numLights = rHL.getRunningLights().size();
		Assert.assertTrue(numLights == 5);
	}
	
	@Test public void MakeHolidaysLightsOff()
	{
		RunningHolidayLights rHL = new RunningHolidayLights(5);
		for(Light l : rHL.getRunningLights())
		{
			Assert.assertFalse(l.isOn());			
		}
	}
	
	@Test public void Make10Lights()
	{
		RunningHolidayLights HL = new RunningHolidayLights(10);
		Assert.assertTrue(10 == HL.getNumberOfLights());
	}
	
	@Test public void TestNextLightSequence()
	{
		int numLights = 10;
		RunningHolidayLights rHL = new RunningHolidayLights(numLights);	
		List<Light> Lights;
		
		for(int i = 0; i < numLights; i++)
		{			
			Lights = rHL.nextLightSequence();
			Assert.assertTrue(Lights.get(i).isOn());
			if((i - 1) >= 0)
			{
				Assert.assertFalse(Lights.get(i - 1).isOn());
			}
			
			if((i + 1) < rHL.getNumberOfLights())
			{
				Assert.assertFalse(Lights.get(i + 1).isOn());
			}
		}
	}
	
	
}
 