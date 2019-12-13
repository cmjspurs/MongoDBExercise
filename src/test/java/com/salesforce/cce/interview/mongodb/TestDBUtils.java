package com.salesforce.cce.interview.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

import junit.framework.TestCase;

public class TestDBUtils extends TestCase {

	public static void testInsert() {
		String collection = "testobj";

		DB db = DBManager.connect();

		DBUtils utils = new DBUtils(db);

		// clear the data
		utils.clearCollection(collection);

		utils.insert(createTestDoc("name1", "test"), collection);

		assertTrue("Expected 1 but got " + utils.getCount(collection),
				utils.getCount(collection) == 1);

	}

	public static void testQuery() {

		// create 10 test docs
		List<DBObject> testDocs = createTestDocs(10);
		DB db = DBManager.connect();
		DBUtils utils = new DBUtils(db);
		String collection = "testdoc";

		// clear out the data first
		utils.clearCollection(collection);

		// insert new test docs
		utils.insertBulk(testDocs, collection);

		// get a total count
		int count = utils.getCount(collection);
		assertEquals(count, 10);

		// try and get one result
		DBObject toFind = new BasicDBObject("name", "testDoc4");
		List<DBObject> results = utils.query(toFind, collection);

		assertTrue(results.size() == 1);

	}

	public static void testGetAllCounts() {
		DB db = DBManager.connect();
		DBUtils utils = new DBUtils(db);

		// TODO uncomment below to see hang
//		Map<String, Integer> counts = utils.getAllCounts();
//
//		assertTrue(counts != null);

	}

	public static void testGetDBSetting() {

		DBUtils utils = new DBUtils();
		Boolean applyFormatting = utils.getDBSetting("applyFormatting");
		assertTrue(applyFormatting);

	}

	public static void testGetDBSettingNull() {
		DBUtils utils = new DBUtils();
		Boolean applyFormatting = utils.getDBSetting(null);
		// we should expect a null value back if we pass in null
		assertNull(applyFormatting);
	}

	/**
	 * Helper method to create a document for testing.
	 * 
	 * @param name
	 *            name of the doc
	 * @param type
	 *            type of doc
	 * @return the created Document
	 */
	private static DBObject createTestDoc(String name, String type) {
		BasicDBObject doc = new BasicDBObject("name", name)
				.append("type", type);

		return doc;
	}

	private static List<DBObject> createTestDocs(int count) {

		List<DBObject> testDocs = new ArrayList<DBObject>();

		for (int i = 0; i < count; i++) {
			testDocs.add(createTestDoc("testDoc" + i, "test"));
		}

		return testDocs;
	}
}
