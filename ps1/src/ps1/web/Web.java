package ps1.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * Web is a library of methods for downloading data from the web.
 */
public class Web {

    public static void main(String[] args) {
        try {
        	Page page = new Page(new URL("http://www.amazon.com"));
        	System.out.println("Page " + page.getURL() + " has length " + page.getContent().length());

        	page = new Page(new URL("http://www.amazon.com"));
        	System.out.println("Page " + page.getURL() + " has length " + page.getContent().length());
        	
        	Weather w = new Weather("02139");
        	System.out.println("It's " + w.getCondition() + " and " + w.getTemperature() + " degrees F.");
        	
        	w = new Weather("02139");
        	System.out.println("It's " + w.getCondition() + " and " + w.getTemperature() + " degrees F.");
        } 
        catch (MalformedURLException e) {
            System.out.println("Bad URL: " + e);
        } 
        catch (IOException e) {
            System.out.println("IO error: " + e);
        }
    }
    
    
    /*
     * Returns the content of the web page identified
     * by the url object.
     */
    public static String fetch(URL url) throws IOException {
        // open a stream to the web server using url
        InputStream input = url.openStream();
        InputStreamReader reader = new InputStreamReader(input);
        
        // create a stream sending data to a String
        StringWriter writer = new StringWriter();
        
        // copy all data from web stream to string stream
        copyStream(reader, writer);
        
        // return the string we created
        return writer.toString();        
    }
    
    /*
     * Returns the content of the web page identified
     * by the url in urlString, which must be a valid URL.
     */
    public static String fetch(String urlString) throws IOException {
        URL url = new URL(urlString);
        return fetch(url);
    }
    
    /*
     * Copies all the data in the reader stream to the writer stream.
     * Closes both streams before exiting the method.
     */
    public static void copyStream(Reader reader, Writer writer) throws IOException {
        try {
            char[] buffer = new char[10000];
            while (true) {
                int n = reader.read(buffer);
                if (n == -1) break;
                writer.write(buffer, 0, n);
            }
        } finally {
            reader.close();
            writer.close();
        }
    }
}
