package com.gumtree.main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.PropertiesUtil;

import com.gumtree.controller.ContactController;
import com.gumtree.data.access.ContactDAO;
import com.gumtree.data.access.DataConstants;
import com.gumtree.data.model.Contact;
import com.gumtree.data.model.Gender;

import static java.lang.System.*;
import static com.gumtree.main.ConsoleConstants.*;

public class ConsoleApp {
	
	private static final Logger logger = LogManager.getLogger(ConsoleApp.class.getName());
	private final static PropertiesUtil accessProperties = new PropertiesUtil(PROP_FILE_STRINGS);
		
	public static void main(String[] args) {
		try {
	        InputStreamReader input = new InputStreamReader(in);
			BufferedReader bufInput = new BufferedReader(input);
			ContactController contactController = new ContactController();
			contactController.initController();
			
	    	String line;
        	
        	out.println(accessProperties.getStringProperty(PROP_STRINGS_INTRO_TEXT));

    		while ((line = bufInput.readLine()) != null) {
    		    out.println(line);

    		    String txt;
    		    switch (line) {
    		    	case COMMAND_1: showMaleCount(contactController); break;
	    		    case COMMAND_2: showOldestContact(contactController); break;
	    		    case COMMAND_3: showAgedDayDifferenesBetweenBillAndPaul(contactController); break;
	    		    case COMMAND_ALL: 
	    		    	showMaleCount(contactController);
	    		    	showOldestContact(contactController);
	    		    	showAgedDayDifferenesBetweenBillAndPaul(contactController); 
	    		    	break;
    		    	case COMMAND_QUIT: 
    		    		out.println(accessProperties.getStringProperty(PROP_STRINGS_COMMAND_QUIT));
    		    		break;
    		    	default:
						txt = MessageFormat.format(accessProperties.getStringProperty(PROP_STRINGS_BAD_COMMAND), line);
						out.println(txt);
						break;
				}
    		    
    		    if(line.equals(COMMAND_QUIT)){
    		    	break;    		    	
		    	}
    		}
    		bufInput.close();
    	} catch (Exception e) {
    		logger.error("error while running console app", e);
    	}
	}

	private static void showMaleCount(ContactController contactController) {
		//How many males are in the address book?
	    List<Contact> contacts = contactController.getContactsByGender(Gender.Male);
	    String txt = MessageFormat.format(accessProperties.getStringProperty(PROP_STRINGS_COMMAND1_QA), contacts.size());
		out.println(txt);
	}

	private static void showOldestContact(ContactController contactController) {
		//Who is the oldest person in the address book?
		Contact contact = contactController.getContactWithMinBirthday();
	    String txt = MessageFormat.format(accessProperties.getStringProperty(PROP_STRINGS_COMMAND2_QA), contact);
		out.println(txt);
	}

	private static void showAgedDayDifferenesBetweenBillAndPaul(ContactController contactController) {
		//How many days older is Bill than Paul?
		Contact bill = contactController.getContactByFirstName("Bill");
		Contact paul = contactController.getContactByFirstName("Paul");
		String anwserTxt = "";
		
		if(bill == null){
			anwserTxt += "Bill not found ";
		}
		if(paul == null){
			anwserTxt += "Paul not found";
		}
		if(bill != null && paul != null){
			anwserTxt = Integer.toString(contactController.getDeltaDayCountForContactBirthdays(bill, paul));
		}
		
		String txt = MessageFormat.format(accessProperties.getStringProperty(PROP_STRINGS_COMMAND3_QA), anwserTxt);
		out.println(txt);
	}


}
