package renamer.server.tvdb;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import org.xml.sax.InputSource;
import java.util.zip.ZipInputStream;
import renamer.shared.file.FileSys;
import renamer.shared.logging.Logs;
import renamer.shared.objects.Series;
import renamer.shared.xml.XMLParser;

/**
 * @file TVDBScraper.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/01/2013
 */

/**
 * A utilities class for scraping the TVDB for series info.
 */
public class TVDBScraper {
    /**
     * The Logger for the TVDBScraper Class.
     */
    private static final Logger logger = Logs.getLogger(TVDBScraper.class.getName());
    /**
     * Maximum size in bytes the entry size can be in the Zip file.
     */
    private static final long MAXSIZE = 2147483647;
    /**
     * Constant String that is URL to the TVDB.
     */
    private static final String SADDY = "http://thetvdb.com/api/GetSeries.php?seriesname=";
    /**
     * The apiKey for the TVDB.
     */
    private static final String apiKEY = "1EEB9AB7E941F4F9";
    /**
     * The series directory part of the address.
     */
    private static final String SERIESPART = "/series/";
    /**
     * The Base URL for the TVDB.
     */
    private static final String INDEPTHSERIES = "http://thetvdb.com/api/";

    /**
     * Parses an XML file located at the place path points to and returns it as a Series Object.
     * This needs to be updated when moved around.
     * 
     * @param id The ID of the Series.
     * @param name The name of the Series.
     * @param path The path to the XML file to parse.
     * 
     * @return Series The parsed XML file represented by a Series object.
     */
    public static Series getSeriesInfo(String id, String name, String path) {
        try {
            Logs.logInfo("Attempting to Scrape Info From The TVDB for show: " + name, logger);   
            XMLParser par = new XMLParser(new ShowHandler(name, id));
            URL url = new URL(INDEPTHSERIES + apiKEY + SERIESPART + id + "/all/en.zip");
            File info = extractFileFromZip(url, path);
            ShowHandler show = (ShowHandler) par.parse(info);
            Series series = (Series) show.getBuilder();
            Logs.logInfo("Successful Scrape of Info From The TVDB show: " + name, logger);
            return series;
        } catch (Exception ex) {
            Logs.logException(ex, "Failed to Scrape Info From The TVDB for show: " + name + ": ", logger);
        }
        return null;
    }

    /**
     * Extracts a file from a Zip file. This needs to be reworked.
     * 
     * @param url The URL to the Zip file.
     * @param path The path to where the File will be placed.
     * 
     * @return File The file that was extracted.
     *  
     */
    private static File extractFileFromZip(URL url, String path) {
        try {
            ZipInputStream input;
            input = new ZipInputStream(url.openStream());
            ZipEntry entry = input.getNextEntry();
            while (entry != null) {
                if (entry.getName().equalsIgnoreCase("en.xml")) {
                    long entSize = entry.getSize();
                    if (entSize > 0 && entSize <= MAXSIZE) {
                        int size = (int) entSize;
                        byte[] bytesToRead = new byte[size];
                        FileSys.checkDirectory(path);
                        FileSys.checkFile(path + "en.xml");
                        FileOutputStream fos = new FileOutputStream(path + "en.xml");
                        BufferedOutputStream bos = new BufferedOutputStream(fos, size);
                        int s;
                        while ((s = input.read(bytesToRead, 0, bytesToRead.length)) != -1) {
                            bos.write(bytesToRead, 0, s);
                        }
                        bos.flush();
                        bos.close();

                    } else {
                        Logs.logWarning("ZipFile Entry: " + path + " is too large to be extracted: " , logger);
                    }
                    break;
                } else {
                    entry = input.getNextEntry();
                }
                input.close();

            }
            return new File(path + "en.xml");
        } catch (IOException ex) {
            Logs.logException(ex, "Error UnZipping File: ", logger);
            return null;
        }
    }

    /**
     * Gets the series from the series search from TVDB.
     *
     * @param name The Series to search for.
     * 
     * @return HashMap<String, String> The results of the search for a series.
     */
    public static HashMap<String, String> getSeries(String name) {
        Logs.logInfo("Searching TVDB for the series: " + name, logger);
        String nameRep = replaceSpace(name);
        try {
            XMLParser par = new XMLParser(new TVHandler());
            URL url = new URL(SADDY + nameRep);
            InputStream input = url.openStream();
            TVHandler tv = (TVHandler) par.parse(new InputSource(input));
            HashMap<String, String> showsMap = tv.getShowsMap();
            Logs.logInfo("Successful Search of The TVDB for Series: " + name, logger);
            return showsMap;
        } catch (Exception ex) {
            Logs.logException(ex, "Error finding Series " + name + ": ", logger);
        }
        return null;
    }

    /**
     * Replaces the spaces in a string with '%20'.
     * 
     * @param toReplace The String to replace the spaces.
     * 
     * @return Sting The String with the spaces replaced.
     */
    private static String replaceSpace(String toReplace) {
        String nString = toReplace.trim();
        return nString.replace(" ", "%20");
    }
}