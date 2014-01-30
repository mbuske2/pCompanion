package renamer.server.tvdb;

import renamer.shared.objects.Season;
import renamer.shared.objects.Episode;
import renamer.shared.objects.SeriesBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import renamer.shared.logging.Logs;

/**
 * @file ShowHandler.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/01/2013
 */

/**
 * A Handler Class To Help With Scraping The TVDB, Used To Get The Show Info.
 */
public class ShowHandler extends DefaultHandler {
    
    /**
     * The Logger for the ShowHandler Class.
     */
    private static final Logger logger = Logs.getLogger(ShowHandler.class.getName());
    /**
     * The Current Value of the Current Element.
     */
    private String currentValue;
    /**
     * The Current Episode.
     */
    private Episode currentEpisode;
    /**
     * The Current Season Number.
     */
    private int currentSeason;
    /**
     * Current Season.
     */
    private Season s;
    /**
     * The Name of the Show.
     */
    private String showName;
    /**
     * Series Builder Object.
     */
    private SeriesBuilder seasons;
    /**
     * HashMap Of the Seasons Belonging To This Series.
     */
    private HashMap<String, Season> seasonsMap = new HashMap<String, Season>();
    /**
     * Array List of the Current Seasons Episodes.
     */
    private ArrayList<Episode> currentEps = new ArrayList<Episode>();

    /**
     * Constructor that builds a ShowHandler Object with a showName and show ID.
     * 
     * @param showName The Name of the Show for this Handler Object.
     * @param id The ID Of the Show For This Handler Object.
     */
    public ShowHandler(String showName, String id) {
        this.showName = showName;
        seasons = SeriesBuilder.getNewInstance(id);
        currentSeason = -1;
    }
    
    /**
     * Gets the SeriesBuilder Object Associated with this Handler.
     * 
     * @return SeriesBuilder The SeriesBuilder Object Associated with this Handler.
     */
    public SeriesBuilder getBuilder(){
        return seasons;
    }

    /**
     * What to do at the Start of the Document.
     * 
     * @throws SAXException If there is an exception in the Sax Parser.
     */
    @Override
    public void startDocument() throws SAXException {
        Logs.logInfo("Parsing TV Show " + showName + "...", logger);
    }

    /**
     * What to do at the End of the Document.
     * 
     * @throws SAXException If there is an exception in the Sax Parser.
     */
    @Override
    public void endDocument() throws SAXException {
        
        seasons.setSeasons(seasonsMap);
        seasons.setSeriesName(showName);
        int size = seasons.getNumberSeasons();
        seasons.toString();
        
        Logs.logInfo("Parsing of TV Show: " + showName + " Complete.", logger);
    }

    /**
     * Receive notification of character data inside an element and places it
     * into currentValue.
     *
     * @param ch the characters.
     * @param start The start position in the character array.
     * @param length The number of characters to use from the character array.
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue = new String(ch, start, length);
    }

    /**
     * What to do at the start of an element.
     *
     * @param uri The Namespace URI, or the empty string if the element has no
     * Namespace URI or if Namespace processing is not being performed.
     * @param localName The local name (without prefix), or the empty string if
     * Namespace processing is not being performed.
     * @param qName The qualified name (with prefix), or the empty string if
     * qualified names are not available.
     * @param attributes The attributes attached to the element. If there are no
     * attributes, it shall be an empty Attributes object.
     * @throws SAXException Any SAX exception, possibly wrapping another
     * exception.
     */
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Episode")) {
            currentEpisode = new Episode();
            currentEpisode.setSeriesName(showName);
        }
    }

    /**
     * Receive notification of the end of an element. Places elements in the
     * User Object.
     *
     * @param localName The local name (without prefix), or the empty string if
     * Namespace processing is not being performed.
     * @param name The qualified name (with prefix), or the empty string if
     * qualified names are not available.
     * @param uri The Namespace URI, or the empty string if the element has no
     * Namespace URI or if Namespace processing is not being performed.
     */
    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        if (name.equalsIgnoreCase("Episode")) {
            int season = Integer.parseInt(currentEpisode.getSeasonNumber());
            if (season > currentSeason) {
                if (currentSeason != -1){
                   seasonsMap.put(s.getSeasonNumber(), s); 
                }
                s = new Season();
                s.setSeasonNumber(Integer.toString(season));
                currentSeason = season;
                currentEps = new ArrayList<Episode>();
            }
            int epNum = Integer.parseInt(currentEpisode.getEpisodeNumber());
            int seasNum = Integer.parseInt(currentEpisode.getSeasonNumber());
            if (epNum < 10 && seasNum < 10){
                currentEpisode.setFullName(currentEpisode.getSeriesName() + " - S0" + currentEpisode.getSeasonNumber()
                            + "E0" + currentEpisode.getEpisodeNumber() + " - " + currentEpisode.getName());
            } else if (epNum < 10 && seasNum >= 10){
                currentEpisode.setFullName(currentEpisode.getSeriesName() + " - S" + currentEpisode.getSeasonNumber()
                            + "E0" + currentEpisode.getEpisodeNumber() + " - " + currentEpisode.getName());
            } else if (epNum >= 10 && seasNum < 10){
                currentEpisode.setFullName(currentEpisode.getSeriesName() + " - S0" + currentEpisode.getSeasonNumber()
                            + "E" + currentEpisode.getEpisodeNumber() + " - " + currentEpisode.getName());
            } else {
                currentEpisode.setFullName(currentEpisode.getSeriesName() + " - S" + currentEpisode.getSeasonNumber()
                            + "E" + currentEpisode.getEpisodeNumber() + " - " + currentEpisode.getName());
            }
            currentEps.add(currentEpisode);
            s.setEpisodes(currentEps);
            seasonsMap.put(s.getSeasonNumber(), s);
            
        } else if (name.equalsIgnoreCase("EpisodeNumber")) {
            currentEpisode.setEpisodeNumber(currentValue);

        } else if (name.equalsIgnoreCase("SeasonNumber")) {
            currentEpisode.setSeasonNumber(currentValue);

        } else if (name.equalsIgnoreCase("EpisodeName")) {
            currentEpisode.setName(currentValue);

        }
    }
}
