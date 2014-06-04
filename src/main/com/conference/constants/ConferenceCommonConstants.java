package main.com.conference.constants;

/**
 * ConferenceCommonConstants : Defines Constants to be used by the project
 */

public class ConferenceCommonConstants {
	
	public ConferenceCommonConstants(){
		
	}
	
	public static final String DEFAULT_FORMAT = "hh:mma ";
	
	public static final int PER_DAY_MIN_TIME_AVAILABLE = 6 * 60; // 9am to 12pm and 1pm to 4pm
	
	public static final int MAX_SESSION_LIMIT = 240; //4 hours
	
	public static final int MIN_SESSION_LIMIT = 180; //3 hours
	
	public static final String EVENT_LUNCH = "Lunch";
	
	public static final int EVENT_LUNCH_DURATION = 60;
	
	public static final String EVENT_NETWORKING = "Networking Event";
	
	public static final int EVENT_NETWORKING_DURATION = 60;
	
	public static final String TALK_DURATION_SUFFIX_MIN = "min";
	
	public static final String TALK_DURATION_SUFFIX_LIGHTNING = "lightning";
	
	public static final int LIGHTNING_SESSION_DURATION = 5;

}
