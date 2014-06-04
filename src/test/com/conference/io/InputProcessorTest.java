package test.com.conference.io;

import java.util.List;

import main.com.conference.io.InputProcessor;

import org.junit.*;

import static org.junit.Assert.*;

public class InputProcessorTest {

	@Test
	public void testFetchTalkList_1()
		throws Exception {
		InputProcessor fixture = new InputProcessor();

		List<String> result = fixture.fetchTalkList();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.util.NoSuchElementException
		//       at java.util.Scanner.throwFor(Unknown Source)
		//       at java.util.Scanner.next(Unknown Source)
		//       at java.util.Scanner.nextInt(Unknown Source)
		//       at java.util.Scanner.nextInt(Unknown Source)
		//       at main.com.conference.io.InputProcessor.fetchTalkList(InputProcessor.java:24)
		assertNotNull(result);
	}

	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(InputProcessorTest.class);
	}
}