package com.salesforce.cce.interview.mongodb;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoURI;

/**
 * 
 * Class that initiates and maintains a connection to the Database
 * 
 * @author cjohn
 * 
 */
public class DBManager {

	private static final Logger logger = Logger.getLogger(DBManager.class);
	private static String MONGO_URI = "mongodb://cce:Salesforce2016@ds013599.mlab.com:13599/ccetestdb";

	@SuppressWarnings("deprecation")
	public static DB connect() {

		MongoClient client = null;
		DB db = null;

		MongoURI mongoURI = new MongoURI(MONGO_URI);
		try {
			db = mongoURI.connectDB();
			db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
		} catch (UnknownHostException e) {
			logger.error(e);
		}
		return db;
	}

}
