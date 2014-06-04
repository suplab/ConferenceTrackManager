package main.com.conference.start;

import main.com.conference.exception.TalkSchedulerException;
import main.com.conference.utility.ConferenceSchedulerUtility;

/**
 * ConferenceBuilder : The main entry of the entire program
 */
public class ConferenceBuilder {

	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {

		ConferenceSchedulerUtility scheduler = new ConferenceSchedulerUtility();

		try {

			//Prepare Schedule
			scheduler.conferenceSchedule();

		} catch (TalkSchedulerException e) {

			e.printStackTrace();
		} catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
