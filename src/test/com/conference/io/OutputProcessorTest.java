package test.com.conference.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.com.conference.entity.TalkInfo;
import main.com.conference.io.OutputProcessor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OutputProcessorTest {
	
	@Test
	public void testPublishToConsole_1()
		throws Exception {
		OutputProcessor fixture = new OutputProcessor();
		Map<Integer ,List<TalkInfo>> scheduledTalkList = new HashMap<Integer ,List<TalkInfo>>();

		fixture.publishToConsole(scheduledTalkList);

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
		new org.junit.runner.JUnitCore().run(OutputProcessorTest.class);
	}
}