package Task;

import java.util.Calendar;

public class Task implements Comparable<Task>{
	
	private Calendar calendar;
	private String description;
	private int taskId;

	/**
	 * Default constructor. Creates a task with the current time and an empty 
	 * task description
	 */
	public Task(){
		calendar = Calendar.getInstance();
		description = "";
		taskId = -1;
	}
	
	/**
	 * Copy constructor
	 * @param task The task to copy
	 */
	public Task(Task task){
		calendar = (Calendar)task.getCalendar().clone();
		description = task.description;
		taskId = task.taskId;
	}
	
	/**
	 * Constructor. Given month, day, time, year, hour, minutes and a task description,
	 * populates the corresponding fields.
	 * @param month Month
	 * @param day Day
	 * @param year Year
	 * @param hourOfDay Hour
	 * @param minute Minute
	 * @param id Unique identification number for the new task
	 * @param newTaskDescription Task description
	 */
	public Task(int month, int day, int year, int hourOfDay, int minute, int id, String newTaskDescription){
		calendar = Calendar.getInstance();
		calendar.set(year, month, day, hourOfDay, minute);
		this.taskId = id;
		description = newTaskDescription;
	}

	/**
	 * Sets a new month, day, year, hour and minute for the calendar.
	 * @param month Month
	 * @param day Day
	 * @param year Year
	 * @param hourOfDay Hour
	 * @param minute Minute
	 * @return void
	 */
	public void setCalendar(int month, int day, int year, int hourOfDay, int minute){
		calendar.set(year, month, day, hourOfDay, minute);
	}
	
	/**
	 * Sets a new task description
	 * @param newTaskDescription String description of the new task
	 * @return void
	 */
	public void setTask(String newTaskDescription){
		description = newTaskDescription;
	}
	
	/**
	 * Set the task's unique id
	 * @param id
	 * @return void
	 */
	public void setId(int id){
		this.taskId = id;
	}
	
	/***
	 * Gets the task's month
	 * @return month
	 */
	public int getMonth(){
		return calendar.get(Calendar.MONTH);
	}
	
	/***
	 * Gets the task's day
	 * @return day
	 */
	public int getDay(){
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/***
	 * Gets the task's year
	 * @return year
	 */
	public int getYear(){
		return calendar.get(Calendar.YEAR);
	}
	
	/***
	 * Gets the task's hour
	 * @return hour
	 */
	public int getHour(){
		return calendar.get(Calendar.HOUR);
	}
	
	/***
	 * Gets the task's minute
	 * @return
	 */
	public int getMinute(){
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Returns the unique id of the task
	 * @return Task's id
	 */
	public int getTaskId(){
		return taskId;
	}
	
	/**
	 * Get's the task description.
	 * @return String description of the task
	 */
	public String getDescription(){
		return description;
	}
	
	/** 
	 * Gets the date and time for the task
	 * @return The calendar for the task
	 */
	public Calendar getCalendar(){
		return calendar;
	}
	
	/**
	 * Compares the unique id's of tasks
	 * 
	 * @param task
	 * @return True if id's are equal, else false
	 */
	public boolean equals(Task task){
		return taskId == task.getTaskId()? true: false;	
	}
	
	/**
	 * Compares this object with the specified object for order. 
	 * 
	 * @param task The task to compare this object to
	 * @return A negative integer, zero, or a positive integer as 
	 * this object is less than, equal to, or greater than the specified object.
	 */
	public int compareTo(Task task){		
		return calendar.compareTo(task.getCalendar());
	}
	
	/**
	 * Generates string representation: <calendar>: <task>
	 * 
	 * @return <calendar>: <task>
	 */
	public String toString(){
		return description;
		//return calendar.toString() + "ID=" + taskId + ",DESCRIPTION= " + description + ",";
	}
}
