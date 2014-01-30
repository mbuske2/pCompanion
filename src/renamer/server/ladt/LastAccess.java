package renamer.server.ladt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import renamer.shared.file.FileSys;
import renamer.shared.logging.Logs;
import renamer.shared.time.RTime;

/**
 * @file LastAccess.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/24/2013
 */

/**
 * A class for saving and reading Ladt Objects.
 */
public class LastAccess {
    /**
     * The Logger for the LastAccess Class.
     */
    private static final Logger logger = Logs.getLogger(LastAccess.class.getName());
    /**
     * The la.dt Constant String for Logging.
     */
    private static final String LADT = "la.dt";
    
    /**
     * Gets the current date based on the running system.
     *
     * @return Date The current Date.
     */
    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * Gets the expiration date based on the current date and a RTime object.
     *
     * @param time The time needed to create an expiration date.
     * @return Date The expiration date.
     */
    public static Date getExpirationDate(RTime time) {
        Calendar c = Calendar.getInstance();
        addRTime(time, c);
        return c.getTime();
    }

    /**
     * Adds RTime to the current time to get the expiration time.
     *
     * @param time The time to add to the current time.
     * @param c The current Calendar instance.
     */
    private static void addRTime(RTime time, Calendar c) {
        c.add(Calendar.YEAR, time.getYear());
        c.add(Calendar.MONTH, time.getMonth());
        c.add(Calendar.DAY_OF_YEAR, (time.getWeek() * 7) + time.getDay());
        c.add(Calendar.HOUR_OF_DAY, time.getHour());
        c.add(Calendar.MINUTE, time.getMinute());
        c.add(Calendar.SECOND, time.getSecond());
    }

    /**
     * Saves the Ladt Object file to disk.
     *
     * @param path The path where the Ladt object is to be saved.
     * @param laf The Ladt Object to save.
     * @return boolean Returns true if the Ladt saved correctly, false
     * otherwise.
     */
    private static boolean saveLastAccessFile(File path, Ladt laf) {
        try {
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(path);
            ObjectOutputStream out;
            out = new ObjectOutputStream(fileOut);
            out.writeObject(laf);
            out.close();
            fileOut.close();
            Logs.logInfo("Successfully Saved New la.dt File: " + path.getAbsolutePath(), logger);
            return true;
        } catch (IOException ex) {
            Logs.logException(ex, "Could not save la.dt File: ", logger);
            return false;
        }
    }

    /**
     * Reads in a Serialized Ladt object and returns it.
     *
     * @param path The path to the Ladt object.
     * @return Ladt The Ladt object that was read in. Returns null on error.
     */
    private static Ladt readLastAccessFile(File path) {
       Ladt laf;
       FileInputStream fileIn;
       try {
            fileIn = new FileInputStream(path);
            ObjectInputStream in;
            in = new ObjectInputStream(fileIn);
            laf = (Ladt) in.readObject();
            in.close();
            fileIn.close();
            Logs.logInfo("Successfully Loaded la.dt File: " + path.getAbsolutePath(), logger);
        } catch (Exception ex) {
            Logs.logException(ex, "Could not load la.dt File: ", logger);
            return null;
        }
        return laf;
    }

    /**
     * Checks to see if the object has expired or not.
     *
     * @param laf The Ladt object to check.
     * @return boolean true if it has expired, false other wise.
     */
    private static boolean hasExpired(Ladt laf) {
        Date c = getCurrentDate();
        if (laf == null) return true;
        if (laf.expireDate.before(c) || laf.expireDate.equals(c)) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the current show needs a re-scrape, and if so makes a new Ladt file. This function
     * returns true if the current shows Ladt file has expired. False if it's Ladt file has not expired.
     * If the shows Ladt file has expired, it will create a new one with an expiration date based on time.
     * 
     * @param path The path to the Ladt File.
     * @param time The time in which to offset the current time to get an expiration time.
     * 
     * @return boolean Returns true if the show needs to be re-scraped, false if it does not.
     */
    public static boolean needsRescrape(String path, RTime time) {
        File f = new File(path + "la.dt");
        Ladt laf;
        FileSys.checkDirectory(path);
        if (f.exists()) {
            laf = readLastAccessFile(f);
            Logs.logInfo(FileSys.getFileExistsMessage(f.getAbsolutePath()), logger);
        } else {
            Logs.logInfo(FileSys.getFileDNECreationMessage(f.getAbsolutePath()), logger);
            try {
                f.createNewFile();
                Logs.logInfo(FileSys.getFileCreationMessage(f.getAbsolutePath()), logger);
            } catch (IOException ex) {
                Logs.logException(ex, "File Failed To Create: ", logger);
            }
            laf = null;
        }
        if (hasExpired(laf)){
            Logs.logInfo("la.dt File Has Expired: " + f.getAbsolutePath(), logger);
            Date d = getExpirationDate(time);
            //how to handle if it did not save.
            saveLastAccessFile(f, new Ladt(d));
            return true;
        }
        Logs.logInfo("la.dt File Has Not Expired: " + f.getAbsolutePath(), logger);
        return false;
    }
}

/**
 * A class that holds info about when the last time a show was scrapped.
 */
class Ladt implements Serializable {

    /**
     * Object's version ID.
     */
    private static final long serialVersionUID = 1;
    /**
     * The Date the Ladt Was Created.
     */
    Date dateCreated;
    /**
     * The Date the Ladt Expires.
     */
    Date expireDate;

    /**
     * Constructor that creates a Ladt object with a specific expiration date.
     * The creation date is initialized to the current date of the system.
     *
     * @param expireDate The expiration date of the Ladt.
     */
    Ladt(Date expireDate) {
        this.dateCreated = Calendar.getInstance().getTime();
        this.expireDate = expireDate;
    }

    /**
     * Constructor that creates a Ladt object with a creation date and
     * expiration date.
     *
     * @param dateCreated The date of creation.
     * @param expireDate The expiration date of the Ladt.
     */
    Ladt(Date dateCreated, Date expireDate) {
        this.dateCreated = dateCreated;
        this.expireDate = expireDate;
    }
}