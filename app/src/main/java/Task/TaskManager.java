package Task;

import android.content.Context;

import SQLite.SQLiteHelperTaskTable;

public class TaskManager {

	private TaskList tasks;
	
	public TaskManager(){
		tasks = new TaskList();
	}
	
	/**
	 * Discards tasks and creates a new emty task list
	 * @return null
	 */
	private void discardTasks(){
		tasks = null;
		tasks = new TaskList();
	}
	
	/**
	 * Creates a new task and adds to the task list. Each time a new task is created,
	 * the task is stored in the SQLite database.
	 * 
	 * @param month Month
	 * @param day Day
	 * @param year Year
	 * @param hourOfDay Hour of day
	 * @param minute Minutes
	 * @param id Unique identification number for the new task
	 * @param newTaskDescription String description of the new task
	 * @return void
	 */
	public void createNewTask(Context context, int month, int day, int year, int hourOfDay, int minute, int id, String newTaskDescription){
		Task newTask = new Task(month, day, year, hourOfDay, minute, id, newTaskDescription);
		if (saveTask(context, newTask))
			tasks.add(newTask);
	}
	
	/***
	 * Writes a new task to the database
	 * @param context Application's context
	 * @param task The task to write to database
	 * @return Returns true if item successfully written to database
	 */
	public boolean saveTask(Context context, Task task){
		SQLiteHelperTaskTable worker = new SQLiteHelperTaskTable(context);
		if (worker.insertTask(task) >= 0)
			return true;
		return false;
	}
	
	/***
	 * Loads all tasks stored in the database
	 * @param context The app's current context
	 */
	public void loadTasks(Context context){
		discardTasks();
		SQLiteHelperTaskTable worker = new SQLiteHelperTaskTable(context);
		tasks = worker.getTasks();
	}
	
	/** MUST IMPLEMENT
	 * Removes the given task
	 * @param task The task to delete
	 * @return 
	 */
	public void deleteTask(Task task){
		tasks.remove(task);
	}
	
	/**
	 * Retrieve a shallow copy to the current task list
	 * 
	 * @return Shallow copy to the current task list
	 */
	public int getSize(){
		return tasks.getSize();
	}
	
	/**
	 * Returns the current task list
	 * @return The current tasklist or null
	 */
	public TaskList getTaskList(){
		return tasks;
	}
}
