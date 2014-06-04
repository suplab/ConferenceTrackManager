package main.com.conference.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * InputProcessor : Processes and handles the input for the system
 */
public class InputProcessor {
	
	
	/**
	 * Method fetchTalkList.
	 * @return List<String>
	 */
	public List<String> fetchTalkList() {
		
		List<String> inputTalkList = null;
		
		System.out.println("------------Interactive Conference Scheduler------------");
		
		Scanner scanIn = new Scanner(System.in);
		System.out.println("1. Enter input from Console");
		System.out.println("2. Enter input from File");
		System.out.print("Enter Choice: ");
		int choice = scanIn.nextInt();
		switch(choice){
		case 1: inputTalkList = fetchTalkListFromConsole();
		break;
		case 2: inputTalkList = fetchTalkListFromFile();
		break;
		default : System.out.println("Incorrect input provided!");
		}
		
		return inputTalkList;
	}
	
	
	
	/**
	 * Method fetchTalkListFromFile.
	 * @return List<String>
	 */
	private List<String> fetchTalkListFromFile() {
        List<String> talkList = new ArrayList<String>();
        
        Scanner scanIn = new Scanner(System.in);
        BufferedReader br = null;
        
        try{
        	System.out.println();
        	System.out.print("Enter file path---> ");
        	String filePath = scanIn.nextLine();
        	
        	br = new BufferedReader(new FileReader(filePath));
        	String strLine = br.readLine();
        	//Read File Line By Line
        	while (strLine != null)   {
        		talkList.add(strLine);
        		strLine = br.readLine();
        	}

        }catch (FileNotFoundException fe){
          System.err.println("File not found at location: " + fe.getMessage());
          
        }catch (Exception e){
          System.err.println("Error: " + e.getMessage());
          
        }finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				System.err.println("Error: " + ex.getMessage());
			}
		}
        
        return talkList;
    }
	
	
	
	/**
	 * Method fetchTalkListFromConsole.
	 * @return List<String>
	 */
	private List<String> fetchTalkListFromConsole() {
		List<String> talkList = new ArrayList<String>();
		
		Scanner scanIn = new Scanner(System.in);
		
		
		try{
			while(true){
				String talkBuilder = null;
				
				System.out.print("Enter Track Title: ");
				talkBuilder = scanIn.nextLine();
				
				//Adding single whitespace for to separate title from time
				talkBuilder += " ";
				
				System.out.print("Enter Track duration (min/ lightning): ");
				talkBuilder+= scanIn.nextLine();
				
				talkList.add(talkBuilder);
				
				System.out.print("Continue (y/n)?");
				
				if(!scanIn.nextLine().equalsIgnoreCase("y"))
					break;
			}
			
		}catch(Exception e)
		{
			System.out.println("Oops! Looks like there has been an invalid input: " + e);

		}finally{
			scanIn.close();
		}
		
		return talkList;
	}

}
