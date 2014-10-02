/*
 * CURRENTLY UNUSED. OLD CODE
 */

package FileManager;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import android.content.Context;

public class FileWorker {
	
	private Context context;
	private BufferedReader reader;
	private String filepath;
	
	public FileWorker(){
		reader = null;
		filepath = null;
	}
	
	public FileWorker(Context context, String filepath){
		this.context = context;
		reader = null;
		this.filepath = filepath;
	}
	
	/**
	 * Reads a line from a file. If the file isn't open for reading, open it.
	 * An internal pointer is maintained and thus for every subsequent call to readLine(), 
	 * the next line of the file is read.
	 * 
	 * @param filepath The path to the file to read.
	 * @return The line read or null if no more lines to read
	 */
	public String readLine(){
		String raw = null;
		boolean readyToRead = false;
		
		if (reader == null){
			if (openForReading())
				readyToRead = true;
		}
		else
			readyToRead = true;
		
		if (readyToRead){
			try {
				raw = reader.readLine();
			} catch (IOException e){
				PrintWriter pw = null;
				e.printStackTrace(pw);
				String error = pw.toString();
			}
		}
		return raw;
	}
	
	/**
	 * Opens a file via filepath for reading
	 * 
	 * @param filepath The path to the file of interest
	 * @return True if both FileReader, BufferedReader and filepath are successfully populated. Else false
	 */
	private boolean openForReading (){	
		boolean result = true;
		try {
			FileReader fr = new FileReader(context.getFilesDir() + "/" + filepath);
			reader = new BufferedReader(fr);
		} catch (IOException e){
			reader = null;
			result = false;
			PrintWriter pw = null;
			e.printStackTrace(pw);
			String error = pw.toString();
			int test = 0;
		}
		return result;
	}
}
