package friendly;


import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FriendlyTest {
	FriendlyClientDriver driver;

	@Before public void setup() throws IOException {
		driver = new FriendlyClientDriver("localhost", 4444);
	}

	@After public void teardown() throws IOException {
		if (driver != null) {
			driver.close();
		}
		driver = null;
	}

	@Test public void testResponses() throws IOException {
		assertEquals("Hello, my friend! How are you today?", driver.readLine());

		assertCorrectResponse("Hard day at work.","I see. Why is that?");
		assertCorrectResponse("Is that all you ever say?","Interesting question.");

		assertCorrectResponse("I think it's going to rain today.", "Nonsense! Let's go to the beach.");
		assertCorrectResponse("What's happening?","We need to talk about your TPS reports.");
		assertCorrectResponse("I think I'm gonna lose it.","Uh-oh. Sounds like somebody's got a case of the Mondays.");

		assertCorrectResponse("bye", "Well, bye then.");
	}

	private void assertCorrectResponse(String input, String response) throws IOException {
		driver.writeLine(input);
		assertEquals(response, driver.readLine());	
	}

}