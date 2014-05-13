package com.gumtree.data.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import com.gumtree.controller.ContactController;
import com.gumtree.data.model.Contact;
import com.gumtree.data.model.Gender;

public class ContactControllerTest {

	@Rule
 	public ExpectedException thrown=ExpectedException.none();

	ContactController contactController = new ContactController();

	@Before
	public void setUp(){
		contactController.initController();
	}
	
	@Test
	public void testMissingControllerInit(){
		contactController.destroyController();
		thrown.expect(NullPointerException.class);
		contactController.getContactWithMinBirthday();
	}
	
	@Test
	public void testContactsByGender(){
		List<Contact> contacts = contactController.getContactsByGender(Gender.Male);
		assertNotNull(contacts);
		assertEquals(2, contacts.size());
	
		contacts = contactController.getContactsByGender(Gender.Female);
		assertEquals(0, contacts.size());		
	}	

	@Test
	public void testGetContactByMinBirthday(){
		Contact contact = contactController.getContactWithMinBirthday();
		assertNotNull(contact);
		assertEquals("Bill", contact.getFirstname());
	}

	@Test
	public void testGetContactByFirstName(){
		thrown.expect(NullPointerException.class);
		Contact contact = contactController.getContactByFirstName(null);
				
		contact = contactController.getContactByFirstName("");
		assertNull(contact);

		contact = contactController.getContactByFirstName("Paul");
		assertNotNull(contact);
		assertEquals("Paul", contact.getFirstname());
	}
	
	@Test
	public void testGetDeltaDayCountForContactBirthdays(){
		Contact paul = contactController.getContactByFirstName("Bill");
		Contact bill = contactController.getContactByFirstName("Paul");
		assertNotNull(paul);
		assertNotNull(bill);
		
		assertEquals(2862, contactController.getDeltaDayCountForContactBirthdays(paul, bill));
	}
}
