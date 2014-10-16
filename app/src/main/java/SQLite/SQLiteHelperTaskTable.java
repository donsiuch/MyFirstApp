package SQLite;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import Task.Task;
import Task.TaskList;

public class SQLiteHelperTaskTable extends SQLiteOpenHelper {
	
	private static final int VERSION = 1;
	private static final String DATABASE = "ToDo";
	private static final String TABLE = "tasks";
	
	// Columns
	private static final String ID = "_id";
	private static final String MONTH = "month";
	private static final String DAY = "day";
	private static final String YEAR = "year";
	private static final String HOUR = "hour";
	private static final String MINUTE = "minute";
	private static final String DESCRIPTION = "description";
	
	public SQLiteHelperTaskTable(Context context) {
		super(context, DATABASE, null, VERSION);
	}

	@Override
    // TO DO -- I removed the restriction on task_id being unique.
    // need this to be unique!
    // TASK_ID + " UNIQUE NOT NULL, " +
    // Can we get rid of the taskId and use just the _id?
    // Might have to change the task comparison
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE +
				"(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				MONTH + " INTEGER NOT NULL, " +
				DAY + " INTEGER NOT NULL, " +
				YEAR + " INTEGER NOT NULL, " +
				HOUR + " INTEGER NOT NULL, " +
				MINUTE + " INTEGER NOT NULL, " +
				DESCRIPTION + " VARCHAR(256) NOT NULL)";
		db.execSQL(CREATE_TASK_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + TABLE + ";");
		this.onCreate(db);
	}
	
	/***
	 * Inserts a task into the database.
	 * @param task The task to be inserted
	 * @return The row of the inserted task, or -1 for failure
	 */
	public long insertTask (Task task){
		// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(MONTH, task.getMonth()); 
        values.put(DAY, task.getDay());
        values.put(YEAR, task.getYear());
        values.put(HOUR, task.getHour());
        values.put(MINUTE, task.getMinute());
        values.put(DESCRIPTION, task.getDescription());

        long rowId = db.insert(TABLE, null, values);
        db.close();
        return rowId;
	}

    /**
     * Give an _id, delete the row that corresponds to that _id
     * @param _id The _id in the table
     * @return The number of rows deleted
     */
    public long deleteTask (long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE, ID +" = " + _id, null);
        db.close();
        return rowsAffected;
    }
	
	public TaskList getTasks(){
		TaskList taskList = new TaskList();
		// 1. build the query
       String query = "SELECT  * FROM " + TABLE;
 
       // 2. get reference to writable DB
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery(query, null);
 
       // 3. go over each row, build book and add it to list
	   while (cursor.moveToNext()){
		   int _id = Integer.parseInt(cursor.getString(0));
		   int month = Integer.parseInt(cursor.getString(1));
		   int day = Integer.parseInt(cursor.getString(2));
		   int year = Integer.parseInt(cursor.getString(3));
		   int hour = Integer.parseInt(cursor.getString(4));
		   int minute = Integer.parseInt(cursor.getString(5));
		   String description = cursor.getString(6);
		   taskList.add(new Task(month, day, year, hour, minute, _id, description));
	   } 
       return taskList;
	}
}
