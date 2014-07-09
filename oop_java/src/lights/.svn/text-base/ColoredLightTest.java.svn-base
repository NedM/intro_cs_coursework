package lights;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

public class ColoredLightTest
{
	@Test public void MakeOffColoredLight()
	{
		ColoredLight cLight = new ColoredLight(Color.red);
		Assert.assertFalse(cLight.isOn());
	}
	
	@Test public void MakeOnColoredLight()
	{
		ColoredLight cLight = new ColoredLight(true, Color.red);
		Assert.assertTrue(cLight.isOn());
	}
		
	@Test public void MakeRedColoredLight()
	{
		ColoredLight cLight = new ColoredLight(Color.red);
		Assert.assertTrue(Color.red.equals(cLight.getColor()));
	}
	
	@Test public void TurnOnColoredLight()
	{
		ColoredLight cLight = new ColoredLight(Color.red);
		cLight.setOn(true);
		
		Assert.assertTrue(cLight.isOn());
	}
	
	@Test public void TurnOffColoredLight()
	{
		ColoredLight cLight = new ColoredLight(true, Color.red);
		cLight.setOn(false);
		
		Assert.assertFalse(cLight.isOn());
	}
	
	@Test public void TurnOnOnlyOneLight()
	{
		ColoredLight firstLight = new ColoredLight(Color.red);
		ColoredLight secondLight = new ColoredLight(Color.blue);
		
		firstLight.setOn(true);
		
		Assert.assertTrue(firstLight.isOn());
		Assert.assertFalse(secondLight.isOn());
	}
	
	@Test public void ChangeLightColorToBlue()
	{
		ColoredLight cLight = new ColoredLight(Color.red);
		Assert.assertTrue(Color.red.equals(cLight.getColor()));
		
		cLight.setColor(Color.blue);
		Assert.assertTrue(Color.blue.equals(cLight.getColor()));	
	}
	
	@Test public void TestRandomChange() 
	{
		ColoredLight cLight = new ColoredLight(false, Color.red);
		// Call randomChange up to 100 times.
		// Probabilistically, should turn on at some point.
		boolean lightChanged = false;
		for (int i = 0; i < 100; i++) 
		{
			cLight.randomChange();
			if ((cLight.isOn()) || (!Color.red.equals(cLight.getColor())))
			{
				lightChanged = true;
				break;
			}
		}
		Assert.assertTrue(lightChanged);
		
		// Make sure it can change the other way
		cLight = new ColoredLight(true, Color.black);
		// Call randomChange up to 100 times.
		// Probabilistically, should turn on at some point.
		lightChanged = false;
		for (int i = 0; i < 100; i++)
		{
			cLight.randomChange();
			if (!cLight.isOn() || (!Color.black.equals(cLight.getColor())))
			{
				lightChanged = true;
				break;
			}
		}
		Assert.assertTrue(lightChanged);
	}
	
}
