package renamer.bridge.gui;

import renamer.shared.file.FileData;
import renamer.server.tvdb.TVDBScraper;
import renamer.server.ladt.LastAccess;
import renamer.shared.objects.Series;
import renamer.server.options.OptClass;
import renamer.server.tvdb.TVDBData;
import renamer.server.util.XMLHelper;

/**
 * @file GUIHelper.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/29/2013
 */

/**
 * A class that connects the GUI and the BackEnd Classes.
 */
public class GUIHelper {
    
    /**
     * Gets the Series object either from scraping the TVDB or from file.
     * 
     * @param seriesName The name of the Series object to retrieve.
     * @param id The id of the Series object to retrieve.
     * @param opt The User's options.
     * 
     * @return Series The Series object to be retrieved.
     */
    public static Series retrieveSeries(String seriesName, String id, OptClass opt){
        String path = TVDBData.TVDBDATADIR + id + FileData.SEP;
        if (LastAccess.needsRescrape(path, opt.getScrapeInterval())){
            return TVDBScraper.getSeriesInfo(id, seriesName, path);
        } else {
            return XMLHelper.getSeriesInfo(id, seriesName, path + opt.getTVLang() + ".xml");
        }
    }
    
}
