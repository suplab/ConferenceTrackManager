package test.com.conference.utility;

import main.com.conference.utility.ConferenceSchedulerUtility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConferenceSchedulerUtilityTest {

	@Test(expected = java.util.NoSuchElementException.class)
	public void testConferenceSchedule_1()
		throws Exception {
		ConferenceSchedulerUtility fixture = new ConferenceSchedulerUtility();

		fixture.conferenceSchedule();

		// add additional test code here
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testConferenceSchedule_2()
		throws Exception {
		ConferenceSchedulerUtility fixture = new ConferenceSchedulerUtility();

		fixture.conferenceSchedule();

		// add additional test code here
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testConferenceSchedule_3()
		throws Exception {
		ConferenceSchedulerUtility fixture = new ConferenceSchedulerUtility();

		fixture.conferenceSchedule();

		// add additional test code here
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testConferenceSchedule_4()
		throws Exception {
		ConferenceSchedulerUtility fixture = new ConferenceSchedulerUtility();

		fixture.conferenceSchedule();

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
		new org.junit.runner.JUnitCore().run(ConferenceSchedulerUtilityTest.class);
	}
}