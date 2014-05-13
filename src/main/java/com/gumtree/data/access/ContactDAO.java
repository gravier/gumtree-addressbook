package com.gumtree.data.access;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.gumtree.data.model.Contact;
import com.gumtree.data.model.Gender;

public class ContactDAO implements IContactDAO {

	private static final Logger logger = LogManager.getLogger(ContactDAO.class.getName());
	private static final PropertiesUtil configProperties = new PropertiesUtil(DataConstants.PROP_FILE_CONFIG);
	private static final PropertiesUtil accessProperties = new PropertiesUtil(DataConstants.PROP_FILE_ACCESS);

	@Override
	public List<Contact> readAll() {
		List<Contact> addressList = new ArrayList<Contact>();
		List<String> dataLines = readFile(Contact.ENTITY_NAME);
		
		for(String dataLine : dataLines){
			// example format: John Doe, Male, 11/12/88
			StringTokenizer st = new StringTokenizer(dataLine, " ,");
		     
			String firstname = st.nextToken();
			String lastname = st.nextToken();
			Gender gender = Gender.valueOf(st.nextToken());
			
			String dateFormat = configProperties.getStringProperty(DataConstants.PROP_CONFIG_DATE_FORMAT);
			DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
			DateTime birthday = formatter.parseDateTime(st.nextToken());
			
			Contact ab = new Contact(firstname, lastname, gender, birthday);
			addressList.add(ab);
		}
		return addressList;
	}
	
	private List<String> readFile(String entityName){
		String dirPath = configProperties.getStringProperty(DataConstants.PROP_CONFIG_DATA_PATH);
		String fileName = accessProperties.getStringProperty(entityName);
		Path path = FileSystems.getDefault().getPath(dirPath, fileName);
		try {
			List<String> readLines = Files.readAllLines(path, Charset.defaultCharset());
			return readLines;
		} catch (IOException e) {
			logger.error("error accessing data file: " + entityName, e);
		}
		return new ArrayList<String>();
	}

}
