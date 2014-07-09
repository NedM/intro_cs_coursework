package ps1.web.cache;

import java.net.URL;

import ps1.web.Page;
import ps1.web.Web;

/*
 * Cache represents a cache of page as an array.
 */
public class Cache 
{
    private Page[] cache = new Page[100]; // contains Pages and null references
    private int cachePointer = 0; // index of next slot available for a page
        
    /*
     * Returns a cached Page object p such that p.getURL() is url.
     * Returns null if no page for url found in the cache.
     */
    public Page get(URL url)
    {
        for (Page p : cache) {
            if (p == null) continue;
            else if (p.getURL().equals(url)) return p;
        }
        return null;
    }
    
    /*
     * If a page is found in the cache, refreshes the content of that page at the location in the cache
     * If the page is not found in the cache, no action is taken
     */
    public void update(URL url)
    {
    	for(int i = 0; i < this.cache.length; i++)
    	{
    		if(null == this.cache[i])
    		{
    			continue;
    		}
    		else if(this.cache[i].getURL().equals(url))
    		{    			
    			this.updateCacheLocation(i);
    		}
    	}
    }
    
    public void update(Page newPage)
    {
    	URL url = newPage.getURL();
    	for(int i = 0; i < this.cache.length; i++)
    	{
    		if(null == this.cache[i])
    		{
    			continue;
    		}
    		else if(this.cache[i].getURL().equals(url))
    		{
    			this.cache[i] = newPage;
    		}    			
    	}
    }
    
    /*
     * Store page in the cache.
     */
    public void put(Page page) 
    {
        cache[cachePointer] = page;
        ++cachePointer;
        if (cachePointer >= cache.length) cachePointer = 0;
    }

    /*
     * Updates the content of every page in the cache
     */
    public void refreshCache() 
    {    	    	
    	for(int i = 0; i < this.cache.length; i++)
    	{
    		if(null == this.cache[i])
    		{
    			continue;
    		}
    		else
    		{
    			updateCacheLocation(i);
    		}
    	}
    }
    
    /*
     * Updates the content of the page at the indicated index in the cache
     * If an exception is encountered during page retrieval, that location in the cache is set to null
     */
    private void updateCacheLocation(int index)
    {
    	String newContent;
    	URL url;
    	
    	//validate input
    	if((index < 0) || (index >= this.cache.length))
    	{
    		//index out of bounds. return immediately.
    		return;
    	}
    	
    	//Skip null entries
    	if(null == this.cache[index]) 
    	{
    		//Cache location is empty. return immediately
    		return;
    	}
		
		url = this.cache[index].getURL();
		try
		{    			
			newContent = Web.fetch(url);
			this.cache[index].setContent(newContent);
		}
		catch(java.io.IOException ex)
		{
			System.out.println("Failed to obtain the content for url = " + url + ". Could not update cache at index = " + index + ". Setting to null.");
			this.cache[index] = null;
		}
		return;
    }
}
