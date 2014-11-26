package com.salesforce.cce.interview.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * 
 * Class that initiates and maintains a connection to the Database
 * 
 * @author cjohn
 * 
 */
public class DBManager {

	private static final Logger logger = Logger.getLogger(DBManager.class);

	public static DB connect(String dbName) {
		return connect(false, dbName);
	}

	public static DB connect(boolean authenticate, String dbName) {

		MongoClient client = null;
		DB db = null;
		try {
			if (authenticate) {
				String username = "cceuser";
				String password = "password";

				MongoCredential cred = MongoCredential.createMongoCRCredential(
						username, dbName, password.toCharArray());
				client = new MongoClient(new ServerAddress(),
						Arrays.asList(cred));
			} else {
				client = new MongoClient();
			}
			db = client.getDB(dbName);
		} catch (UnknownHostException e) {
			logger.error(e);
		}

		return db;
	}

}
