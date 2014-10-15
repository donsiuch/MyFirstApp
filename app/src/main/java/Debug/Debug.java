/**
 * Author: Donald Siuchninski
 * Date: October 2014
 * Description: Logs debug statements.
 */

package Debug;

import FileManager.FileManager;

public class Debug {

    public static final long DEBUG_TIME = 0x00000001;
    //public static final long xxx = 0x00000002;
    //public static final long xxx = 0x00000004;
    //public static final long xxx = 0x00000008;
    //public static final long xxx = 0x0000000F;
    //public static final long xxx = 0x00000010;
    //public static final long xxx = 0x00000020;
    //public static final long xxx = 0x00000040;
    //public static final long xxx = 0x00000080;
    //public static final long xxx = 0x000000F0;
    //public static final long xxx = 0x00000100;
    //public static final long xxx = 0x00000200;
    //public static final long xxx = 0x00000400;
    //public static final long xxx = 0x00000800;
    //public static final long xxx = 0x00000F00;

    public static final long DEBUG_NONE = 0x00000000;
    public static final long DEBUG_ALL = 0xFFFFFFFF;

    private final String INTERNAL_ERROR = "INTERNAL ERROR: ";
    private final String NEWLINE = "\n";
    private final String TAB = "\t";

    private long flags;

    private String absoluteFilePath;

    /**
     * Constructor
     */
    public Debug(){
        flags = DEBUG_NONE;
        absoluteFilePath = null;
    }

    /**
     * Constructor
     * @param flags Which debug statements are turned on
     * @param absoluteFilePath The name of the file to output statements to
     */
    public Debug(long flags, String absoluteFilePath){
        this.flags |= flags;
        this.absoluteFilePath = absoluteFilePath;
    }

    /**
     * Change the output filename
     * @param filename The file to write debug statements to
     */
    public void setFilename (String filename){
        this.absoluteFilePath = filename;
    }

    /**
     * Modify which debug statements to print
     * @param flags The debug statements to print
     */
    public void setFlags(long flags){
        this.flags |= flags;
    }

    /**
     * Write an internal error. Typical usage is for completely unexpected critical errors.
     * @param message The debug message to output.
     */
    public void internalError(String function, String message){
        output (DEBUG_ALL, function, INTERNAL_ERROR + message);
    }

    /**
     * Write a simple error. Typical usage are common simple errors.
     * @param flags The debug statement type.
     * @param message The debug message to print.
     */
   public void output(long flags, String function, String message){
        if ((this.flags &= flags) > DEBUG_NONE){
            writeDebugInfoToFile(function, message);
        }
    }

    /**
     * Sends the entire debug string to a file writer for output.
     * @param function Name of the function the debug statement refers to.
     * @param message The debug message to output.
     * @return True if the write succeeded, else false.
     */
    private boolean writeDebugInfoToFile(String function, String message){
        if (function == null && message == null && absoluteFilePath == null)
            return false;
        FileManager fm = new FileManager(absoluteFilePath);
        return fm.writeToFile(function + NEWLINE + TAB + message + NEWLINE);
    }
}
