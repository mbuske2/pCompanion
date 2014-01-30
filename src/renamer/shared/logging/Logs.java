package renamer.shared.logging;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @file Logs.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/29/2013
 */

/**
 * A class to set up the logs for the program.
 */
public class Logs {
    
    /**
     * The directory in which to store the log files.
     */
    private static String logDir;
    /**
     * The File Handler.
     */
    private static FileHandler f;
    /**
     * The Console Handler.
     */
    private static ConsoleHandler c;
    
    /**
     * Instantiates the Static Log Class.
     * 
     * @param logPath The Log Directory Path.
     * 
     * @return boolean Returns true for correct instantiation, false otherwise.
     */
    public static boolean init(String logPath){
        logDir = logPath;
        c = new ConsoleHandler();
        c.setFormatter(new LogFormatter());
        f = getHandler();
        if (f != null) {
            f.setFormatter(new LogFormatter());
            return true;
        } else {
            return false;
        }
        
    }
    
    /**
     * Gets a Logger with the given class name. This function also configures the
     * Logger for log entries custom to this application.
     * 
     * @param name The name of the Class the logger is associated with.
     * 
     * @return Logger The properly configured Logger.
     */
    public static Logger getLogger(String name){
        Logger logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false);
        logger.addHandler(c);
        logger.addHandler(f);
        return logger;
    }
    
    /**
     * Gets the FileHandler for the program.
     * 
     * @return FileHandler The File Handler for the Logger. Returns null if there
     * was an error.
     */
    private static FileHandler getHandler(){
        try {
            File l = new File(logDir);
            if (!l.exists()){
                l.mkdirs();
            }
            FileHandler fHandler = new FileHandler(logDir + "log.%u.%g.txt", (1024 * 1024 * 1024 * 1024), 10);
            return fHandler;
        } catch (IOException ex) {
            Logger.getLogger(Logs.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SecurityException ex) {
            Logger.getLogger(Logs.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    /**
     * Logs an exception for a specific Logger.
     * 
     * @param ex The exception to log.
     * @param msg The message to display in the log.
     * @param log The Log to log the message with.
     */
    public static void logException(Exception ex, String msg, Logger log){
        log.log(Level.SEVERE, msg, ex);
    }
    
    /**
     * Logs Info for a specific Logger.
     * 
     * @param msg The message to display in the log.
     * @param log The Log to log the message with.
     */
    public static void logInfo(String msg, Logger log){
        log.log(Level.INFO, msg);
    }
    
    /**
     * Logs Warning for a specific Logger.
     * 
     * @param msg The message to display in the log.
     * @param log The Log to log the message with.
     */
    public static void logWarning(String msg, Logger log){
        log.log(Level.WARNING, msg);
    }
    
    /**
     * Logs Severe for a specific Logger.
     * 
     * @param msg The message to display in the log.
     * @param log The Log to log the message with.
     */
    public static void logSevere(String msg, Logger log){
        log.log(Level.SEVERE, msg);
    }
    
    /**
     * Formats a date in the form of "[MM-dd-yyyy hh:mm:ss]".
     * 
     * @param d The date to format.
     * 
     * @return String The formatted date in the form "[MM-dd-yyyy hh:mm:ss]".
     */
    public static String formatDate(Date d){
        SimpleDateFormat dt = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        String date = "[" + dt.format(d) + "]";
        return date;
    }
}
