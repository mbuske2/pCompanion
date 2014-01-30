package renamer.shared.file;

/**
 * @file FileData.java
 * @author Matt Buske
 * @version 0.2A
 * @date 01/02/2014
 */

/**
 * A Class Containing Constant Data used for the File Directory.
 */
public class FileData {
    /**
     * The Systems File Separator.
     */
    public static final String SEP = System.getProperty("file.separator");
    /**
     * The User's Home Directory
     */
    public static final String HOMEDIR = System.getProperty("user.home") + SEP;
    /**
     * The Application Directory
     */
    public static final String APPDIR = HOMEDIR + ".Renamer" + SEP;
    /**
     * The Logging Directory
     */
    public static final String LOGDIR = APPDIR + "logs" + SEP;
}
