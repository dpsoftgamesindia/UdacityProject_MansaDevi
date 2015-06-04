package com.udacitycourse.udacityproject_mansadevi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase {

	/** for database */
	static final String DataBaseName = "NotificationDB";

	/** for employee table */
	static final String NotificationTable = "NotificationDatabase";
	public static final String ColID = "Id";
	public static final String ColNotification = "Notification";
	public static final String ColDate = "Date";

	public static final int DATABASE_VERSION = 1;

	private static final String NOTIFICATION_TABLE_CREATE = "Create table "
			+ NotificationTable + "(" + ColID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + ColNotification
			+ " VARCHAR(255) ," + ColDate + " VARCHAR(10));";

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public MyDataBase(Context mainActivity) {
		this.context = mainActivity;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DataBaseName, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(NOTIFICATION_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + NotificationTable);
			onCreate(db);
		}

	};

	public MyDataBase open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	public long insert(String notification, String get_Date) {
		ContentValues initialValues = new ContentValues();

		initialValues.put(ColNotification, notification);
		initialValues.put(ColDate, get_Date);

		return db.insert(NotificationTable, null, initialValues);
	}

	public Cursor getEmpValues() {
		Cursor mCursor = db.query(NotificationTable, null, null, null, null,
				null, null);
		return mCursor;
	}

	public boolean deleteEmpList(long rowId) {
		return db.delete(NotificationTable, ColID + " = " + rowId, null) > 0;
	}

	public boolean updateEmplist(String notification, Integer date,
			Integer rowid) {

		ContentValues initialValues = new ContentValues();

		initialValues.put(ColNotification, notification);
		initialValues.put(ColDate, date);

		try {
			int b = db.update(NotificationTable, initialValues, ColID + " = "
					+ rowid, null);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}