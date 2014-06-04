package main.com.conference.io;

import java.util.List;
import java.util.Map;

import main.com.conference.entity.TalkInfo;

/**
 * OutputProcessor : Handles the output logic 
 */
public class OutputProcessor {
	
	/**
	 * Method publishToConsole.
	 * @param scheduledTalkMap Map<Integer,List<TalkInfo>>
	 */
	public void publishToConsole(Map<Integer ,List<TalkInfo>> scheduledTalkMap){
		 
		if(scheduledTalkMap == null || scheduledTalkMap.isEmpty()){
			System.out.println("No talks to schedule");
			
		}else{
			for(Integer track : scheduledTalkMap.keySet()){
				
				System.out.println("Track " + track + ":");
				
				for(TalkInfo talk : scheduledTalkMap.get(track)){
					System.out.println(talk.getScheduledTime() + talk.getTitle());
				}
			}
		}
	}

}
