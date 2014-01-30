package renamer.shared.file;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import renamer.shared.logging.Logs;

/**
 * @file FileSys.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/29/2013
 */

/**
 * A utilities class for File Related Tasks.
 */
public class FileSys {
    /**
     * The Logger for the FileSys Class.
     */
    private static final Logger logger = Logs.getLogger(FileSys.class.getName());
    
    /**
     * Checks to see if a Directory Exists, If it doesn't, It attempts to create it.
     * 
     * @param path The Directory to check.
     * 
     * @return boolean Returns true if the directory exists or was created, false if
     * the directory failed to create properly.
     */
    public static boolean checkDirectory(String path) {
        File userDirPath = new File(path);
        if (userDirPath.isDirectory()) {
            Logs.logInfo(getDirExistsMessage(path), logger);
            return true;
        } else {
            Logs.logInfo(getDirDNECreationMessage(path), logger);
            if (userDirPath.mkdirs()) {
                Logs.logInfo(getDirCreationMessage(path), logger);
                return true;
            } else {
                Logs.logSevere("Could not create directory: " + userDirPath.getAbsolutePath(), logger);
                return false;
            }

        }
    }
    
    /**
     * Checks if a file exists, and if not, attempts to create it.
     * 
     * @param path The path to the File to check.
     * 
     * @return boolean Returns true if the file exists or was created, false if
     * the file failed to create properly.
     */
    public static boolean checkFile(String path) {
        File userDirPath = new File(path);
        if (userDirPath.isFile()) {
            Logs.logInfo(getFileExistsMessage(path), logger);
            return true;
        } else {
            Logs.logInfo(getFileDNECreationMessage(path), logger);
            try {
                userDirPath.createNewFile();
                Logs.logInfo(getFileCreationMessage(path), logger);
                return true;
            } catch (IOException ex) {
                Logs.logException(ex, "Could not create File: ", logger);
                return false;
            }

        }
    }
    
    /**
     * Returns either "File \"" + path + "\" Created." or 
     * "Directory \"" + path + "\" Created."
     * 
     * @param path The file or directory.
     * @param isFile true if the path is a file, false if it is a directory.
     * 
     * @return String The message for either the file or directory.
     */
    private static String getCreationMessage(String path, boolean isFile){
        if (isFile) return "File \"" + path + "\" Created.";
        return "Directory \"" + path + "\" Created.";
    }
    
    /**
     * Returns either "File \"" + path + "\" Does Not Exist. Creating..." or 
     * "Directory \"" + path + "\" Does Not Exist. Creating..."
     * 
     * @param path The file or directory.
     * @param isFile true if the path is a file, false if it is a directory.
     * 
     * @return String The message for either the file or directory.
     */
    private static String getDNECreationMessage(String path, boolean isFile){
        if (isFile) return "File \"" + path + "\" Does Not Exist. Creating...";
        return "Directory \"" + path + "\" Does Not Exist. Creating...";
    }
    
    /**
     * Returns either "File \"" + path + "\" Exists." or 
     * "Directory \"" + path + "\" Exists."
     * 
     * @param path The file or directory.
     * @param isFile true if the path is a file, false if it is a directory.
     * 
     * @return String The message for either the file or directory.
     */
    private static String getExistsMessage(String path, boolean isFile){
        if (isFile) return "File \"" + path + "\" Exists.";
        return "Directory \"" + path + "\" Exists.";
    }
    
    /**
     * Gets the File Creation Message, IE: "File \"" + path + "\" Created."
     * 
     * @param path The path to the File for the message.
     * 
     * @return String The Creation Message for a File.
     */
    public static String getFileCreationMessage(String path){
        return getCreationMessage(path, true);
    }
    
    /**
     * Gets the File Creation Message, IE: "File \"" + path + "\" Does Not Exist. Creating..."
     * 
     * @param path The path to the File for the message.
     * 
     * @return String The DNECreation Message for a File.
     */
    public static String getFileDNECreationMessage(String path){
        return getDNECreationMessage(path, true);
    }
    
    /**
     * Gets the File Creation Message, IE: "File \"" + path + "\" Exists."
     * 
     * @param path The path to the File for the message.
     * 
     * @return String The Exists Message for a File.
     */
    public static String getFileExistsMessage(String path){
        return getExistsMessage(path, true);
    }
    
    /**
     * Gets the Directory Creation Message, IE: "Directory \"" + path + "\" Created."
     * 
     * @param path The path to the Directory for the message.
     * 
     * @return String The Creation Message for a Directory.
     */
    public static String getDirCreationMessage(String path){
        return getCreationMessage(path, false);
    }
    
    /**
     * Gets the Directory DNECreation Message, IE: "Directory \"" + path + "\" Does Not Exist. Creating..."
     * 
     * @param path The path to the Directory for the message.
     * 
     * @return String The DNECreation Message for a Directory.
     */
    public static String getDirDNECreationMessage(String path){
        return getDNECreationMessage(path, false);
    }
    
    /**
     * Gets the Directory Exists Message, IE: "Directory \"" + path + "\" Exists."
     * 
     * @param path The path to the Directory for the message.
     * 
     * @return String The Exists Message for a Directory.
     */
    public static String getDirExistsMessage(String path){
        return getExistsMessage(path, false);
    }
}
