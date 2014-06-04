package main.com.conference.entity;

/**
 * TalkInfo : Describes the Talk Entity
 */
public class TalkInfo  {
	
	String title;
	int duration;
	boolean isScheduled = false;
	String scheduledTime;
	
	/**
	 * Constructor for TalkInfo.
	 * @param title String
	 * @param duration int
	 */
	public TalkInfo(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
	
	/**
	 * Method setScheduled.
	 * @param isScheduled boolean
	 */
	public void setScheduled(boolean isScheduled) {
        this.isScheduled = isScheduled;
    }
	
	/**
	 * Method isScheduled.
	 * @return boolean
	 */
	public boolean isScheduled() {
        return isScheduled;
    }

	/**
	 * Method getTitle.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method setTitle.
	 * @param title String
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method getDuration.
	 * @return int
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Method setDuration.
	 * @param duration int
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Method getScheduledTime.
	 * @return String
	 */
	public String getScheduledTime() {
		return scheduledTime;
	}

	/**
	 * Method setScheduledTime.
	 * @param scheduledTime String
	 */
	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	
	
}
