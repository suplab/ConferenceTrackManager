package main.com.conference.utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.com.conference.constants.ConferenceCommonConstants;
import main.com.conference.entity.TalkInfo;
import main.com.conference.exception.TalkSchedulerException;
import main.com.conference.io.InputProcessor;
import main.com.conference.io.OutputProcessor;

/**
 * ConferenceSchedulerUtility : Handles the logic of creating Talks from input and scheduling them
 */
public class ConferenceSchedulerUtility {
	
	
	
	/**
	 * Method conferenceSchedule.
	 * @throws Exception
	 */
	public void conferenceSchedule() throws Exception {

		List<String> inputTalkList = null;
		Map<Integer ,List<TalkInfo>> scheduledTalkMap = null;
		InputProcessor inProcessor = new InputProcessor();
		OutputProcessor outProcessor = new OutputProcessor();
		
		//Prepare Input
		inputTalkList = inProcessor.fetchTalkList();
		
		if(inputTalkList!=null && !inputTalkList.isEmpty()){
			
			List<TalkInfo> parsedTalksList = parseTalks(inputTalkList);
			scheduledTalkMap = prepareScheduledTracks(parsedTalksList);
		}

		if(scheduledTalkMap!=null && !scheduledTalkMap.isEmpty()){
			//Prepare output
			outProcessor.publishToConsole(scheduledTalkMap);
		}

	}

	/**
	 * Method prepareScheduledTracks.
	 * @param talksList List<TalkInfo>
	 * @return Map<Integer,List<TalkInfo>>
	 * @throws Exception
	 */
	private Map<Integer ,List<TalkInfo>> prepareScheduledTracks(List<TalkInfo> talksList)  throws Exception {

		int totalTalkTimeToSchedule = totalTalkTime(talksList);
		int requiredDays = totalTalkTimeToSchedule/ConferenceCommonConstants.PER_DAY_MIN_TIME_AVAILABLE;


		List<TalkInfo> tempTalkList = new ArrayList<TalkInfo>(); 

		//Adding all talks to be scheduled
		tempTalkList.addAll(talksList);
		
		//Schedule Morning Sessions
		List<List<TalkInfo>> ListOfTalkListForMorning = possibleTalksPerSession(tempTalkList, requiredDays, true);

		// Remove scheduled talks from morning session
		for(List<TalkInfo> talkList : ListOfTalkListForMorning) {
			tempTalkList.removeAll(talkList);
		}

		//Schedule Evening Sessions
		List<List<TalkInfo>> ListOfTalkListForEvening = possibleTalksPerSession(tempTalkList, requiredDays, false);

		// Remove scheduled talks from evening session
		for(List<TalkInfo> talkList : ListOfTalkListForEvening) {
			tempTalkList.removeAll(talkList);
		}

		// There might be talks that could not be scheduled,trying to fit them in evening session 
		if(!tempTalkList.isEmpty()) {
			List<TalkInfo> scheduledTalkList = new ArrayList<TalkInfo>();
			for(List<TalkInfo> talkList : ListOfTalkListForEvening) {
				int totalTime = totalTalkTime(talkList);

				for(TalkInfo talk : tempTalkList) {
					int talkTime = talk.getDuration();

					if(talkTime + totalTime <= ConferenceCommonConstants.MAX_SESSION_LIMIT) {
						talkList.add(talk);
						talk.setScheduled(true);
						scheduledTalkList.add(talk);
					}
				}

				tempTalkList.removeAll(scheduledTalkList);
				if(tempTalkList.isEmpty())
					break;
			}
		}

		if(!tempTalkList.isEmpty())
		{
			throw new TalkSchedulerException("All talks could not be scheduled");
		}

		// Place the talks in tracks.
		return generateTracks(ListOfTalkListForMorning, ListOfTalkListForEvening);
	}

	/**
	 * Method generateTracks.
	 * @param ListOfTalkListForMorning List<List<TalkInfo>>
	 * @param ListOfTalkListForEvening List<List<TalkInfo>>
	 * @return Map<Integer,List<TalkInfo>>
	 */
	private Map<Integer ,List<TalkInfo>> generateTracks(List<List<TalkInfo>> ListOfTalkListForMorning, List<List<TalkInfo>> ListOfTalkListForEvening) {
		
		Map<Integer ,List<TalkInfo>> scheduledTalksList = new HashMap<Integer ,List<TalkInfo>>();
        int tracks = ListOfTalkListForMorning.size();
        
        for(int count = 0; count < tracks; count++) {
            List<TalkInfo> talkList = new ArrayList<TalkInfo>();
            
            // Create a date initialised to time 09:00 AM.
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat (ConferenceCommonConstants.DEFAULT_FORMAT);
             
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            
            Date time = cal.getTime();
            String scheduledTime = dateFormat.format(time);
            
            
            // schedule for Morning Session.
            List<TalkInfo> morningSessionTalkList = ListOfTalkListForMorning.get(count);
            for(TalkInfo talk : morningSessionTalkList) {
                talk.setScheduledTime(scheduledTime);
                scheduledTime = generateTimeFromDuration(time, talk.getDuration());
                talkList.add(talk);
            }
            
           
            
            // Scheduled Lunch Time for 60 mins.
            TalkInfo lunchTalk = new TalkInfo(ConferenceCommonConstants.EVENT_LUNCH, ConferenceCommonConstants.EVENT_LUNCH_DURATION);
            lunchTalk.setScheduledTime(scheduledTime);
            talkList.add(lunchTalk);
           
            
            // evening session scheule.
            scheduledTime = generateTimeFromDuration(time, ConferenceCommonConstants.EVENT_LUNCH_DURATION);
            List<TalkInfo> eveningSessionTalkList = ListOfTalkListForEvening.get(count);
            for(TalkInfo talk : eveningSessionTalkList) {
                talk.setScheduledTime(scheduledTime);
                talkList.add(talk);
               scheduledTime = generateTimeFromDuration(time, talk.getDuration());
            }
            
            // Scheduled Networking Event at the end of session.
            TalkInfo networkingTalk = new TalkInfo(ConferenceCommonConstants.EVENT_NETWORKING, ConferenceCommonConstants.EVENT_NETWORKING_DURATION);
            networkingTalk.setScheduledTime(scheduledTime);
            talkList.add(networkingTalk);
            
            
            scheduledTalksList.put(count+1, talkList);
        }   
            
            
		return scheduledTalksList;
	}
	
	

	/**
	 * Method generateTimeFromDuration.
	 * @param time Date
	 * @param duration int
	 * @return String
	 */
	private String generateTimeFromDuration(Date time, int duration) {
		
		long timeInLong  = time.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat (ConferenceCommonConstants.DEFAULT_FORMAT);
        
        long timeDurationInLong = duration * 60 * 1000; //minutes to milliseconds
        long newTimeInLong = timeInLong + timeDurationInLong;
        
        time.setTime(newTimeInLong);
        String str = dateFormat.format(time);
        return str;
	}

	/**
	 * Method parseTalks.
	 * @param talkList List<String>
	 * @return List<TalkInfo>
	 * @throws Exception
	 */
	private List<TalkInfo> parseTalks(List<String> talkList) throws Exception {
		
		if(talkList == null)
			   throw new TalkSchedulerException("No talks to schedule");
		
		List<TalkInfo> createdTalkList = new ArrayList<TalkInfo>();
		
		for(String talk : talkList){
			
			int lastWhiteSpacePos = talk.lastIndexOf(" ");
			
			//validate the talk
			if(lastWhiteSpacePos == -1)
				throw new TalkSchedulerException("Invalid talk format. Either title or duration is missing: " + talk);
			
			String title = talk.substring(0, lastWhiteSpacePos);
			String duration = talk.substring(lastWhiteSpacePos+1);
			
			if(title == null || title.trim().isEmpty()){
				throw new TalkSchedulerException("Invalid talk title: " + talk);
				
			}else if(!duration.endsWith(ConferenceCommonConstants.TALK_DURATION_SUFFIX_MIN) && !duration.endsWith(ConferenceCommonConstants.TALK_DURATION_SUFFIX_LIGHTNING))
				   throw new TalkSchedulerException("Invalid talk duration. Time must be in min or in lightning: " + talk);
			
			
			int time = 0;
			   // Parse time from the time string .
			   try{
				   if(duration.endsWith(ConferenceCommonConstants.TALK_DURATION_SUFFIX_MIN)) {
					   time = Integer.parseInt(duration.substring(0, duration.indexOf(ConferenceCommonConstants.TALK_DURATION_SUFFIX_MIN)));
				   }
				   else if(duration.endsWith(ConferenceCommonConstants.TALK_DURATION_SUFFIX_LIGHTNING)) {
					   time =  ConferenceCommonConstants.LIGHTNING_SESSION_DURATION;
				   }
			   }catch(NumberFormatException nfe) {
				   throw new TalkSchedulerException("Failed to parse duration for talk: " + talk);
			   }

			   
			   createdTalkList.add(new TalkInfo(title, time));
			
		}
		
		
		return createdTalkList;
	}
	

	/**
	 * Method totalTalkTime.
	 * @param talksList List<TalkInfo>
	 * @return int
	 */
	private int totalTalkTime(List<TalkInfo> talksList)
    {
        if(talksList == null || talksList.isEmpty())
            return 0;
        
        int totalTime = 0;
        for(TalkInfo talk : talksList) {
            totalTime = totalTime + talk.getDuration();
        }
        return totalTime;
    }
	
	
	/**
	 * Method possibleTalksPerSession.
	 * @param talksListForOperation List<TalkInfo>
	 * @param totalPossibleDays int
	 * @param isMorningSession boolean
	 * @return List<List<TalkInfo>>
	 */
	private List<List<TalkInfo>> possibleTalksPerSession(List<TalkInfo> talksListForOperation, int totalPossibleDays, boolean isMorningSession)
    {
        int minSessionTimeLimit = ConferenceCommonConstants.MIN_SESSION_LIMIT;
        int maxSessionTimeLimit = ConferenceCommonConstants.MAX_SESSION_LIMIT;
        
        if(isMorningSession)
            maxSessionTimeLimit = minSessionTimeLimit;	//9am to 12pm, hence always 3 hours
        
        int talkListSize = talksListForOperation.size();
        List<List<TalkInfo>> possibleCombinationsOfTalks = new ArrayList<List<TalkInfo>>();
        int possibleCombinationCount = 0;
        
        // Loop to get combination for total possible days.
        // Check one by one from each talk to get possible combination.
        for(int count = 0; count < talkListSize; count++) {
            int startPoint = count;
            int totalTime = 0;
            List<TalkInfo> possibleCombinationList = new ArrayList<TalkInfo>();
            
            // Loop to get possible combination.
            while(startPoint != talkListSize) {
                int currentCount = startPoint;
                startPoint++;
                TalkInfo currentTalk = talksListForOperation.get(currentCount);
                if(currentTalk.isScheduled())
                    continue;
                int talkTime = currentTalk.getDuration();
                // If the current talk time is greater than maxSessionTimeLimit or 
                // sum of the current time and total of talk time added in list  is greater than maxSessionTimeLimit.
                // then continue.
                if(talkTime > maxSessionTimeLimit || talkTime + totalTime > maxSessionTimeLimit) {
                    continue;
                }
                
                possibleCombinationList.add(currentTalk);
                totalTime += talkTime;
                
                // If total time is completed for this session than break this loop.
                if(isMorningSession) {
                    if(totalTime == maxSessionTimeLimit)
                        break;
                }else if(totalTime >= minSessionTimeLimit)
                    break;
            }
            
            // Valid session time for morning session is equal to maxSessionTimeLimit.
            // Valid session time for evening session is less than or eqaul to maxSessionTimeLimit and greater than or equal to minSessionTimeLimit.
            boolean validSession = false;
            if(isMorningSession)
                validSession = (totalTime == maxSessionTimeLimit);
            else
                validSession = (totalTime >= minSessionTimeLimit && totalTime <= maxSessionTimeLimit);
            
            // If session is valid than add this session in the possible combination list and set all added talk as scheduled.
            if(validSession) {
                possibleCombinationsOfTalks.add(possibleCombinationList);
                for(TalkInfo talk : possibleCombinationList){
                    talk.setScheduled(true);
                }
                possibleCombinationCount++;
                if(possibleCombinationCount == totalPossibleDays)
                    break;
            }
        }
        
        return possibleCombinationsOfTalks;
    }

}
