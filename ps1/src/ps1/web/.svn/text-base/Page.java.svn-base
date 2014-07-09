package ps1.web;

import java.io.IOException;
import java.net.URL;

import ps1.web.cache.Cache;

/**
 * Page represents a downloaded web page.
 * Page objects are immutable (never change once created).
 */
public class Page 
{
    // fields
    private URL url;
    private String content;
    // the same cache is shared across every Page instance
    private static Cache cache = new Cache();

    // constructor
    /**
     * Make a new Page for a URL, downloading it immediately. Throws IOException
     * if an error occurs accessing the web server.
     */
    public Page(URL url) throws IOException 
    {
        this.url = url;
        ConstructionHelper();
    }
    
    public Page(URL url, boolean refresh) throws IOException 
    {
    	this.url = url;
    	
    	if(refresh)
    	{
    		try
    		{
	    		//If refresh is specified, get the new content
	    		this.content = Web.fetch(this.url);
    		}
    		catch(IOException ex)
    		{
    			System.out.println("Error! Failed to obtain content from " + this.url + ". Aborting construction.");
    			throw ex;
    		}
    		
    		//Check to see if its already in the cache
    		Page p = cache.get(this.url);
    		
    		if(null == p)
    		{    			
    			//if the page is not in the cache, put it in
    			cache.put(this);
    		}
    		else
    		{
    			//if the page is in the cache, update the instance in the cache
    			cache.update(this);
    		}
    	}
    	else
    	{
    		//If refresh is not specified, use the regular construction method
    		ConstructionHelper();
    	}    	
    }
    
    // methods
    public URL getURL() 
    {
        return url;
    }

    public String getContent() 
    {
        return content;
    }

    public void setURL(URL url) 
    {
    	this.url = url;
    }
    
    public void setContent(String content) 
    {
    	this.content = content;
    }
    
    public static Cache getCache() 
    {
        return cache;
    }

    public static void setCache(Cache cache) 
    {
        Page.cache = cache;
    }

    /**
     * Downloads the content from the URL of this page, or retrieves it from the
     * cache if it has already been downloaded.
     * 
     * @return true if the page was downloaded, false if loaded from the cache
     * @throws IOException
     */
    protected boolean download() throws IOException 
    {
        Page p = cache.get(url);
        if (p != null) 
        {
        	//Page was found in cache
            this.content = p.content;
            return false;
        } 
        else 
        {
        	//Page was not found in cache
            this.content = Web.fetch(url);
            return true;
        }
    }
    
    /*
     * Performs constructor duties common to both constructors
     */
    private void ConstructionHelper() throws IOException
    {
    	try
    	{
	    	if(download())
	    	{
	    		cache.put(this);
	    	}
    	}
    	catch(java.io.IOException ex)
    	{
    		System.out.println("Error! Encountered exception while retrieving page: " + ex.getMessage());
    		throw ex;
    	}
    }
}
