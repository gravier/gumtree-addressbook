package com.gumtree.data.access;

import java.util.List;

import com.gumtree.data.model.Contact;

public interface IContactDAO {

	public List<Contact> readAll();
}
