package testBDB;

import java.io.File;

import com.sleepycat.bind.tuple.IntegerBinding;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;
import com.sleepycat.je.Transaction;

public class myBDBTest {
	public static void runTestNoTransaction() {

	}

	/*
	 * 
	 * Cursor cursor = exampleDb.openCursor(null, null);
	 * 
	 * 
	 * while (cursor.getNext(keyEntry, dataEntry, LockMode.DEFAULT) ==
	 * OperationStatus.SUCCESS) { System.out.println("key=" +
	 * IntegerBinding.entryToInt(keyEntry) + " data=" +
	 * IntegerBinding.entryToInt(dataEntry));
	 * 
	 * } cursor.close();
	 */
	public static void delete(Environment ev, Database db, int key) {

		/* Use a binding to convert the int into a DatabaseEntry. */
		DatabaseEntry keyEntry = new DatabaseEntry();
		IntegerBinding.intToEntry(key, keyEntry);
	
		Transaction txn = ev.beginTransaction(null, null);

		OperationStatus status = db.delete(txn,keyEntry);
		
		System.out.println("Del status:" + status);

		txn.commit();

	}

	
	
	public static void put(Environment ev, Database db, int key, int value) {

		/* Use a binding to convert the int into a DatabaseEntry. */
		DatabaseEntry keyEntry = new DatabaseEntry();
		DatabaseEntry dataEntry = new DatabaseEntry();

		IntegerBinding.intToEntry(key, keyEntry);
		IntegerBinding.intToEntry(value, dataEntry);

		Transaction txn = ev.beginTransaction(null, null);

		OperationStatus status = db.put(null, keyEntry, dataEntry);
		
		if (status != OperationStatus.SUCCESS) {
			throw new RuntimeException("Data insertion got status " + status);
		}
		System.out.println("Put status:" + status);

		txn.commit();

	}

	public static int get(Environment ev, Database db, int key) {

		/* Use a binding to convert the int into a DatabaseEntry. */
		DatabaseEntry keyEntry = new DatabaseEntry();
		DatabaseEntry dataEntry = new DatabaseEntry();

		IntegerBinding.intToEntry(key, keyEntry);

		Transaction txn = ev.beginTransaction(null, null);

		OperationStatus status = db.get(null, keyEntry, dataEntry,
				LockMode.DEFAULT);
		if (status != OperationStatus.SUCCESS) {
			throw new RuntimeException("Data insertion got status " + status);
		}
		System.out.println("Get status:" + status);

		txn.commit();

		int getValue = IntegerBinding.entryToInt(dataEntry);

		return getValue;

	}

	public static void runTestTransaction() {

		/* Create a new, transactional database environment */
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setTransactional(true);
		envConfig.setAllowCreate(true);

		String envDirPath = "dbEn";
		File envDir = new File(envDirPath);
		Environment exampleEnv = new Environment(envDir, envConfig);

		/*
		 * Make a database within that environment
		 * 
		 * Notice that we use an explicit transaction to perform this database
		 * open, and that we immediately commit the transaction once the
		 * database is opened. This is required if we want transactional support
		 * for the database. However, we could have used autocommit to perform
		 * the same thing by simply passing a null txn handle to openDatabase().
		 */
	
		Transaction txn = exampleEnv.beginTransaction(null, null);
		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setTransactional(false);
		dbConfig.setAllowCreate(true);
		dbConfig.setSortedDuplicates(false);
		
		Database exampleDb = exampleEnv.openDatabase(null, "simpleDb3", dbConfig);
		txn.commit();

		int key = 2;
		int value = 10;
		
		System.out.println("put : K:" + key + "  V:" + value);
		put(exampleEnv, exampleDb, key, value);
		
		int getValue = get(exampleEnv, exampleDb, key);
		System.out.println("get result: K:" + key + "  V:" + getValue);
		//System.out.println("delete K:" + key );
		//delete(exampleEnv,exampleDb,key);
		//System.out.println("delete K:" + key );
		//delete(exampleEnv,exampleDb,key);
		
		 value = 11;
		 System.out.println("put : K:" + key + "  V:" + value);
		 put(exampleEnv, exampleDb, key, value);
			
		 getValue = get(exampleEnv, exampleDb, key);
		 System.out.println("get result: K:" + key + "  V:" + getValue);
		
		
		//System.out.println("put result: K:" + key + "  V:" + value);
		//put(exampleEnv, exampleDb, key, value);
		
		// getValue = get(exampleEnv, exampleDb, key);

		//System.out.println("get result: K:" + key + "  V:" + getValue);
		
		//System.out.println("delete K:" + key );
		//delete(exampleEnv,exampleDb,key);
		
		

		/*
		 * Note that put will throw a DatabaseException when error conditions
		 * are found such as deadlock. However, the status return conveys a
		 * variety of information. For example, the put might succeed, or it
		 * might not succeed if the record alread exists and the database was
		 * not configured for duplicate records.
		 */

		exampleDb.close();
		exampleEnv.close();
	}

	public static void main(String argvs[]) {
		runTestTransaction();
	}
}
