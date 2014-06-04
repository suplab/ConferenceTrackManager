package main.com.conference.exception;

/**
 * TalkSchedulerException : Custom exception for Conference Scheduler
 */
public class TalkSchedulerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for TalkSchedulerException.
	 * @param message String
	 */
	public TalkSchedulerException(String message) {
        super(message);
    }

}
