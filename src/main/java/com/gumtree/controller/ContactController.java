package com.gumtree.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.gumtree.data.access.ContactDAO;
import com.gumtree.data.model.Contact;
import com.gumtree.data.model.Gender;

public class ContactController {
	
	private List<Contact> _contacts;
	
	public void initController(){
		ContactDAO addressBookDAO = new ContactDAO();
		_contacts = addressBookDAO.readAll();
	}

	public List<Contact> getContactsByGender(final Gender gender){
		Preconditions.checkNotNull(_contacts, "Contact list not initialized");
		
		List<Contact> results = new ArrayList<Contact>();
		Iterable<Contact> contactGenderMatches = Iterables.filter(_contacts, new Predicate<Contact>(){
			public boolean apply(Contact input) {
				return input.getGender() == gender;
			}			
		});
		
		for(Contact contactMatch : contactGenderMatches){
			results.add(contactMatch);
		}
		return results;
	}
	
	public Contact getContactWithMinBirthday(){
		Preconditions.checkNotNull(_contacts, "Contact list not initialized");
		
		Ordering<Contact> ordering = new Ordering<Contact>() {
			
			@Override
			public int compare(Contact left, Contact right) {
				return Ints.compare(left.getAge(), right.getAge());
			}
		};
		return ordering.max(_contacts);
	}
	
	public Contact getContactByFirstName(final String firstname){
		Preconditions.checkNotNull(_contacts, "Contact list not initialized");
		Preconditions.checkNotNull(firstname, "firstname cannot be null");
		
		return Iterables.find(_contacts, new Predicate<Contact>(){

			@Override
			public boolean apply(Contact input) {
				return input.getFirstname().compareToIgnoreCase(firstname) == 0;
			}
			
		}, null);
	}
	
	public int getDeltaDayCountForContactBirthdays(Contact leftContact, Contact rightContact){
		Preconditions.checkNotNull(_contacts, "Contact list not initialized");
		Preconditions.checkNotNull(leftContact, "left contact cannot be null");
		Preconditions.checkNotNull(rightContact, "right contact cannot be null");
		
		return Days.daysBetween(leftContact.getBirthday(), rightContact.getBirthday()).getDays();
	}
	
	public void destroyController(){
		_contacts = null;
	}
}
