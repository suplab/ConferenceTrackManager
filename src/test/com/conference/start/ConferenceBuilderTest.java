package test.com.conference.start;

import main.com.conference.start.ConferenceBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConferenceBuilderTest {
	
	@Test
	public void testMain_1()
		throws Exception {
		String[] args = new String[] {};

		ConferenceBuilder.main(args);

		// add additional test code here
	}

	
	@Test
	public void testMain_2()
		throws Exception {
		String[] args = new String[] {};

		ConferenceBuilder.main(args);

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(ConferenceBuilderTest.class);
	}
}