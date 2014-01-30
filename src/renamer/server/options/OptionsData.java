package renamer.server.options;

import renamer.shared.file.FileData;

/**
 * @file OptionsData.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/17/2013
 */

/**
 * A Class Containing Constant Data used for options.
 */
public class OptionsData {    
    /**
     * The Constant File Name for the option File. This is: "options.xml".
     */
    public static final String FILENAME = "options.xml";
    /**
     *  Constant array of strings representing the video file extentions to allow,
     *  ie: {".avi", ".mp4", ".mpg", ".mkv", ".mov", ".txt"}.
     */
    public static final String[] FILTERS = {".avi", ".mp4", ".mpg", ".mkv", ".mov", ".txt"};
    /**
     * Constant array of strings representing the subtitle file extentions to allow,
     *  ie: {".ass", ".smi", ".srt", ".ssa"}.
     */
    public static final String[] SUBFILTERS = {".ass", ".smi", ".srt", ".ssa"};
    /**
     * Constant array of strings representing the supported Languages for subtitles,
     *  ie: {"None", "English"}.
     */
    public static final String[] LANGUAGES = {"None", "English"};
    /**
     * Constant array of strings representing the subtitle tag for each language,
     *  ie: {"", ".en"}.
     */
    public static final String[] LANGUAGETAGS = {" ", ".en"};
    /**
     * The File Path for the Options File.
     */
    public static final String OPTIONSPATH = FileData.APPDIR + "usr" + FileData.SEP;
    /**
     * The Full Path for the Options File.
     */
    public static final String FULLPATH = OPTIONSPATH + FILENAME;
    
}
