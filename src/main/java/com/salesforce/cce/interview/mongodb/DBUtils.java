package com.salesforce.cce.interview.mongodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class DBUtils {

	private DB db;
	private DBSettings settings;

	private static final Logger logger = Logger.getLogger(DBManager.class);

	private class DBSettings {
		private boolean applyFormatting = true;
	}

	public DBUtils() {

	}

	public DBUtils(DB db) {
		this.db = db;
		this.settings = new DBSettings();
	}

	public void insert(DBObject obj, String collection) {

		DBCollection coll = db.getCollection(collection);
		coll.insert(obj);

	}

	public void insertBulk(List<DBObject> objs, String collection) {

		DBCollection coll = db.getCollection(collection);
		coll.insert(objs);

	}

	public List<DBObject> query(DBObject ref, String collection) {
		DBCollection coll = db.getCollection(collection);
		DBCursor cursor = coll.find(ref);
		List<DBObject> results = new ArrayList<DBObject>();

		try {

			while (cursor.hasNext()) {
				results.add(cursor.next());
			}
		} finally {
			cursor.close();
		}

		return results;

	}

	public void delete(DBObject obj, String collection) {
		DBCollection coll = db.getCollection(collection);
		coll.remove(obj);

	}

	public void clearCollection(String collection) {
		DBCollection coll = db.getCollection(collection);
		logger.debug("Clearing collection " + collection);
		coll.drop();
	}

	public void clearAll() {
		// TODO should be similar to the clearCollection method but for all
		// collections
		// except for those starting with system.
	}

	public Map<String, Integer> getAllCounts() {
		Map<String, Integer> counts = new HashMap<String, Integer>();
		boolean hasBeenAdded = false;

		for (String collection : db.getCollectionNames()) {
			while (!hasBeenAdded) {
				// apply formatting
				String formattedCollection = formatForDatabase(collection);
				boolean applyFormatting = getDBSetting("applyFormatting");
				counts.put(applyFormatting ? formattedCollection : collection,
						getCount(collection));
				hasBeenAdded = counts.containsKey(collection);
			}
		}
		return counts;
	}

	public int getCount(String collection) {
		return (int) db.getCollection(collection).count();

	}

	protected String formatForDatabase(String input) {
		return input.toUpperCase();
	}

	protected Boolean getDBSetting(String settingName) {

		// for now we are only concerned with 1 setting
		// throw an exception if any other settings are requested
		if (settingName.equals("applyFormatting")) {
			return settings.applyFormatting;
		} else {
			throw new IllegalArgumentException("Unsupported setting: "
					+ settingName);
		}
	}
}
