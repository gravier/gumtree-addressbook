package com.gumtree.data.access;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.common.collect.Iterables;
import com.gumtree.data.model.Contact;
import com.gumtree.data.model.Gender;

public class ContactDAOTest {

	IContactDAO addressBookDAO = new ContactDAO();
	
	@Test
	public void testReadAllNotNull() {
		assertNotNull(addressBookDAO.readAll());
	}
	
	@Test
	public void testReadAllQty2() {
		assertEquals(2, addressBookDAO.readAll().size());
	}
	
	@Test
	public void testReadAllIsDataCorrect() {
		List<Contact> addressList = addressBookDAO.readAll();
		Contact billAddress = Iterables.getFirst(addressList, null);
		assertNotNull(billAddress);
		assertEquals("Bill", billAddress.getFirstname());
		assertEquals("McKnight", billAddress.getLastname());
		assertEquals(Gender.Male, billAddress.getGender());
		assertEquals(new DateTime(1977, 3, 16, 0, 0).toString("dd/MM/yyyy"), billAddress.getBirthday().toString("dd/MM/yyyy"));
	}
}
