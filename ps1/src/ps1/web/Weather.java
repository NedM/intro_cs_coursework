package ps1.web;

import java.io.IOException;
import java.net.URL;

import ps1.web.cache.Cache;

public class Weather extends Page 
{
    private String condition; // e.g. partly cloudy, fair
    private int temperature; // in degrees Fahrenheit
    // inherits:
    // url
    // content
    // getURL()
    // getContent()

    /**
     * Makes a Weather object for a US zipcode.
     * Requires zipcode to be a valid 5-digit zipcode.
     */
    public Weather(String zipcode) throws IOException 
    {
        super(new URL("http://weather.yahooapis.com/forecastrss?p=" + zipcode));        
    }

    public String getCondition() {
        return condition;
    }

    public int getTemperature() {
        return temperature;
    }
    
    @Override
    protected boolean download() throws IOException 
    {
        boolean cacheMiss = super.download();
        if (cacheMiss) 
        {
        	//If the page is not in the cache
        	populateWeatherConditions();
        }
        else 
        {
        	Cache c = getCache();
            Page p = c.get(this.getURL());
            if (p instanceof Weather) 
            {
                Weather w = (Weather) p;
                this.condition = w.condition;
                this.temperature = w.temperature;
            }
            else
            {           	
            	//Obtain data
            	populateWeatherConditions();           	
            	c.update(this);
            }
        }
        return cacheMiss;
    }
    
    private void populateWeatherConditions()
    {
    	String line = Match.between(this.getContent(), "<yweather:condition", "/>");
        
        this.condition = Match.between(line, "text=\"", "\"");
        this.temperature = Integer.valueOf(Match.between(line, "temp=\"", "\""));
    }    
}
