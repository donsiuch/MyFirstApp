package Task;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class TaskList {
	
	// Formatters for string representation of the list
	// NOTE: CURRENTLY UNUSED
	static final String LEFT_BRACKET = "[";
	static final String RIGHT_BRACKET = "]";
	static final String SPACE = " ";

	// List of tasks in sorted order
	private LinkedList<Task> taskList;
	
	/**
	 * Default constructor. Initializes a new LinkedList<Task>
	 */
	public TaskList(){
		taskList = new LinkedList<Task>();
	}
	
	/**
	 * Get the number of tasks
	 * 
	 * @return Number of tasks
	 */
	public int getSize(){
		return taskList.size();
	}
	
	/**
	 * Add a new task to the list
	 * 
	 * @param task
	 * @return True if add is successful, else false
	 */
	public boolean add(Task task){
		boolean isSuccessful = taskList.add(task);
		if (isSuccessful)
			Collections.sort(taskList);
		return isSuccessful;
	}
	
	/**
	 * Deletes a given task
	 * 
	 * @param task
	 * @return True if deleted successfully, else false
	 */
	public boolean remove(Task task){
		return taskList.remove(task);
	}
	
	/**
	 * Returns a string representation of the records file.
	 * The records file will contain this text. The importance
	 * is the addition of the new line character at the end of each
	 * record. This new line character is required for proper task
	 * extraction from the records file.
	 * @return A single string that itself is comprised of the results of
	 * each task's toString() method concatenated together.
	 */
	public String toString(){
		//return taskList.toString();
		String recordsFileString = "";
		Iterator<Task> i = taskList.iterator();
		while (i.hasNext()){
			Task task = i.next();
			recordsFileString += task.toString() + "\n";
		}
		return recordsFileString;
	}
	
	/***
	 * For each task in the taskList, invoke its toString() method and store
	 * the result in an array.
	 * @return An array of each tasks toString() method results
	 */
	public String[] toAdapterStringFormat(){
		Iterator<Task> i = taskList.iterator();
		String[] taskListStringForm = new String[taskList.size()];
		int index = 0;
		while (i.hasNext()){
			Task task = i.next();
			taskListStringForm[index] = task.toString();
			index += 1;
		}
		return taskListStringForm;
	}
}
