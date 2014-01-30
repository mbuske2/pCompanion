package renamer.server.util;

import java.io.File;
import java.util.logging.Logger;
import renamer.shared.logging.Logs;
import renamer.shared.objects.Series;
import renamer.shared.xml.XMLParser;
import renamer.server.tvdb.ShowHandler;

/**
 * @file XMLHelper.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/24/2013
 */

/**
 * A utilities class for reading XML Files.
 */
public class XMLHelper {
    
    /**
     * The Logger for the XMLHelper Class.
     */
    private static final Logger logger = Logs.getLogger(XMLHelper.class.getName());

    /**
     * Parses an XML file located at the place path points to and returns it as a Series Object.
     * 
     * @param id The ID of the Series.
     * @param name The name of the Series.
     * @param path The path to the XML file to parse.
     * 
     * @return Series The parsed XML file represented by a Series object.
     */
    public static Series getSeriesInfo(String id, String name, String path) {
        
        try {
            Logs.logInfo("Parsing Series from file: " + path, logger);
            XMLParser par = new XMLParser(new ShowHandler(name, id));
            ShowHandler show = (ShowHandler) par.parse(new File(path));
            Series series = (Series) show.getBuilder();
            Logs.logInfo(path + " Series file successfully parsed.", logger);
            return series;   
        } catch (Exception ex) {
            Logs.logException(ex, "Series Could Not Be Parsed: ", logger);
        } 
        return null;
    }
}
