package renamer.server.tvdb;

import renamer.shared.file.FileData;

/**
 * @file TVDBData.java
 * @author Matt Buske
 * @version 0.2A
 * @date 01/02/2014
 */

/**
 * A Class Containing Constant Data used for the TVDB classes.
 */
public class TVDBData {
    /**
     * The "/tvdb_data" part of the path.
     */
    public static final String TVDBDATA = "tvdb_data" + FileData.SEP;
    /**
     * The full Path to "tvdb_data".
     */
    public static final String TVDBDATADIR = FileData.APPDIR + TVDBDATA;
    
}
