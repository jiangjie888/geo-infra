package com.framework.mongodb;

import java.util.*;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

public class MongoDBCommonUtil {

	private static String ip = "";
	private static int port = 0;
	private static String username = "";
	private static String password = "";
	private static String databasename = "";

	private static MongoClient mongoClient = null;
	private static MongoDatabase database = null;
	private static ServerAddress serverAddress = null;
	private static MongoCredential credentials = null;
	private static List<ServerAddress> addressLists = new ArrayList<ServerAddress>();
	private static List<MongoCredential> credentialsLists = new ArrayList<MongoCredential>();

	@SuppressWarnings("deprecation")
	public static void init() {
		try {
			// mongoClient = new MongoClient("192.168.32.129",27017);
			serverAddress = new ServerAddress(ip, port);
			addressLists.add(serverAddress);
			// credentials = MongoCredential.createCredential("test1", "test",
			// "test1".toCharArray());
			credentials = MongoCredential.createMongoCRCredential(username, databasename, password.toCharArray());
			credentialsLists.add(credentials);
			mongoClient = new MongoClient(addressLists, credentialsLists);

		} catch (MongoException e) {
			System.out.println(e.toString());
		}
		if (null != mongoClient) {
			database = mongoClient.getDatabase(databasename);
		}
	}

	public static MongoClient getMongoClient() {
		if (null == mongoClient) {
			init();
		}

		return mongoClient;
	}

	/**
	 * 获取database
	 * 
	 * @return
	 */
	public static MongoDatabase getDatabase() {
		if (null == mongoClient) {
			init();
		}
		return database;
	}

	public static DB getDB() {
		Mongo mongo = new Mongo("127.0.0.1", 27017);
		DB db = mongo.getDB(databasename);

		/*
		 * boolean ok = ((Object)
		 * db).authenticate("username",password.toCharArray()); db.co if(ok){
		 * System.out.println("db connection success！");
		 * 
		 * }{ System.out.println("db connection fail ！"); }
		 */

		return db;
	}

	public static DBCollection getCollection(MongoDBUpdate mongoDBUpdate) {
		DBCollection result = null;
		return result;
	}
}
