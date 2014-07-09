package ps1.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherTest {
    public static void main(String[] args) {
        try {
            downloadPage("http://www.google.com");
            downloadPage("http://www.google.com");
            downloadPage("http://weather.yahooapis.com/forecastrss?p=02139");
            downloadWeather("02139");
            downloadWeather("02139");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Downloads the page at the given URL and prints some timing and debugging
     * information. Will sometimes also return a cached copy of the page.
     * 
     * @param url
     *            the URL of the page to fetch
     * @return the page object
     * @throws MalformedURLException
     * @throws IOException
     */
    private static Page downloadPage(String url) throws MalformedURLException,
            IOException {
        long startTime = System.currentTimeMillis();
        Page page = new Page(new URL(url));
        long duration = System.currentTimeMillis() - startTime;
        String content = page.getContent();
        System.out.println("Length of page " + page.getURL() + " is "
                + content.length());
        System.out.println("Page retrieved in: " + duration + "ms");
        System.out.println();
        return page;
    }

    /**
     * Downloads the weather at the given zipcode and prints it with some timing
     * and debugging information. Will sometimes also return a cached copy of
     * the page.
     * 
     * @param zipcode
     *            the zipcode where the weather will be fetched from
     * @return the weather object
     * @throws IOException
     */
    private static Weather downloadWeather(String zipcode) throws IOException {
        long startTime = System.currentTimeMillis();
        Weather weather = new Weather(zipcode);
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Weather in " + zipcode + " is: "
                + weather.getCondition() + ", " + weather.getTemperature()
                + " degrees");
        System.out.println("Page retrieved in: " + duration + "ms");
        System.out.println();
        return weather;
    }
}
