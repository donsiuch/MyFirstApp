/*
 * CURRENTLY UNUSED. OLD CODE
 */

package FileManager;

import android.content.Context;
import java.io.File;

public class FileManager {

	/**
	 * Get the absolute path to the internal storage folder
	 * 
	 * @param context Which application
	 * @return Absolute path to the internal storage
	 */
	public String getFileSaveLocation(Context context){
		return context.getFilesDir().getAbsolutePath();
	}
	
	/**
	 * Creates a new file
	 * 
	 * @param context 
	 * @param filename Filename
	 * @return a handle to the new file
	 */
	public File createFile(Context context, String filename){
		File file = new File(context.getFilesDir(), filename);
		return file;
	}
}
