package com.salesforce.cce.interview.mongodb;

import junit.framework.TestCase;

import com.mongodb.DB;

public class TestDBManager extends TestCase {

	public static void testConnect() {
		DB db = DBManager.connect(false, "cce");
		assertNotNull(db);
	}

	public static void testConnectAuthenticated() {
		DB db = DBManager.connect(true, "ccesecure");
		assertNotNull(db);
	}

}
