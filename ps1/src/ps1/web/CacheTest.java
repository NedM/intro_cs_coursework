package ps1.web;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import ps1.web.cache.Cache;

public class CacheTest {
	private final static String timeURL = "http://www.time.gov/timezone.cgi?Eastern/d/-5";
	
    public static void main(String[] args) {
    	try {   		
    		if (!TestRefreshConstructor()) {
    			System.out.println("Refresh constructor failed!");
    		} else if (!TestRefreshCache()) {
    			System.out.println("refreshCache() failed!");
    		} else {
    			System.out.println("Success!");
    		}
    	} catch (IOException e) 
    	{
    		System.out.println(e.getMessage());
    	} catch (InterruptedException e) 
    	{
    		System.out.println(e.getMessage());
    	}
    }
    
    public static boolean TestRefreshCache() throws IOException, InterruptedException {
    	// Clear the cache
    	Page.setCache(new Cache());
    	
    	// Ensure that the caching behavior works. Get a time, wait a second, get a time again.
    	// Should be the same time for both.
    	int old_seconds = TimePageToSeconds(new Page(new URL(timeURL)));
    	Thread.sleep(1000);
    	int new_seconds = TimePageToSeconds(new Page(new URL(timeURL)));
    	if (old_seconds == -1 || new_seconds == -1 || old_seconds != new_seconds) {
    		System.out.println("Proper caching behavior not observed! " +
    						   "Old seconds: " + old_seconds + " New seconds: " + new_seconds);
    	}
    	
    	// Refresh the cache
    	Page.getCache().refreshCache();
    	
    	// Get the time again. Should be new.
    	new_seconds = TimePageToSeconds(new Page(new URL(timeURL)));
    	if (new_seconds == -1 || old_seconds == new_seconds) {
    		System.out.println("Cache refresh failure! " +
    						   "Old seconds: " + old_seconds + " New seconds: " + new_seconds);
    		return false;
    	}
    	return true;
    }
    
    public static boolean TestRefreshConstructor() throws IOException, InterruptedException {
    	// Clear the cache
    	Page.setCache(new Cache());
    	
    	// Ensure that the caching behavior works; get a time, wait a second, get a time again.
    	// Should be the same time returned for both.
    	int old_seconds = TimePageToSeconds(new Page(new URL(timeURL), false));
    	if (old_seconds == -1) {
    		System.out.println("Bad time returned!");
    		return false;
    	}
    	// Sleep for a second, just in case
    	Thread.sleep(1000);
    	int new_seconds = TimePageToSeconds(new Page(new URL(timeURL)));
    	if (new_seconds == -1) {
    		System.out.println("Bad time returned!");
    		return false;
    	}
    	if (old_seconds != new_seconds) {
    		System.out.println("Expected caching behavior, but instead got new time! " +
    						   "Old time: " + old_seconds + " New time: " + new_seconds);
    		return false;
    	}
    	
    	// Check that the new refresh flag works. Set it to true, we should get a new time back
    	// rather than the old cached time.
    	new_seconds = TimePageToSeconds(new Page(new URL(timeURL), true));
    	if (new_seconds == -1) {
    		System.out.println("Bad time returned!");
    		return false;
    	}
    	if (old_seconds == new_seconds) { 
    		System.out.println("Expected no caching behavior, but instead got old time! " +
					           "Old time: " + old_seconds + " New time: " + new_seconds);
    		return false;
    	}

    	return true;
    }
    
    /**
     * Assuming that the input page is fetched from the page provided by www.time.gov,
     * try to find the current time in seconds. Returns a value between 0 and 59, inclusive,
     * if successful. Otherwise, returns -1.
     * 
     * @param p
     *            the Page object that contains time information
     * @return the current seconds, as an integer
     */
    private static int TimePageToSeconds(Page p) {
    	if (p == null) return -1;
    	String content = p.getContent();
    	if (content == null) return -1;
    	
    	Pattern time_pattern = Pattern.compile("<b>\\d{2}:\\d{2}:(\\d{2})<br>");
    	Matcher time_matcher = time_pattern.matcher(content);
    	if (!time_matcher.find()) {
    		return -1;
    	}
    	return Integer.valueOf(time_matcher.group(1));
    }
}
