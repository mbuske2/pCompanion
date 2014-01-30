package renamer.server.tvdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import renamer.shared.logging.Logs;

/**
 * @file TVHandler.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/01/2013
 */

/**
 * A Handler Class To Help With Scraping The TVDB, Used when getting and parsing results of a search.
 */
public class TVHandler extends DefaultHandler {
    
    /**
     * The Logger for the TVHandler Class.
     */
    private static final Logger logger = Logs.getLogger(TVHandler.class.getName());
    /**
     * The Current Value of the Element.
     */
    private String currentValue;
    /**
     * The Current Show Id.
     */
    private String currentID;
    /**
     * The Current Show.
     */
    private String currentShow;
    /**
     * The Overall list of show names.
     */
    private ArrayList<String> showNames = new ArrayList<String>();
    /**
     * All of the completed Shows
     */
    private HashMap<String, String> shows = new HashMap<String, String>();
    
    /**
     * Basic Constructor for the TVHandler.
     */
    public TVHandler(){
        showNames = new ArrayList<String>();
    }
    
    /**
     * What to do at the Start of the Document.
     * 
     * @throws SAXException If there is an exception in the Sax Parser.
     */
    @Override
    public void startDocument() throws SAXException {
        Logs.logInfo("Parsing TV XML File From Search of TVDB.", logger);
    }
    
    /**
     * What to do at the End of the Document.
     * 
     * @throws SAXException If there is an exception in the Sax Parser.
     */
    @Override
    public void endDocument() throws SAXException {
        Logs.logInfo("Parsing TV XML File From Search of TVDB Complete.", logger);
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
        if (qName.equalsIgnoreCase("Series")) {
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
        if (name.equalsIgnoreCase("Series")){
            shows.put(currentShow, currentID);
            currentShow = null;
            currentID = null;
        } else if(name.equalsIgnoreCase("SeriesName")){
            currentShow = currentValue;
            showNames.add(currentValue);
        } else if(name.equalsIgnoreCase("seriesid")){
            currentID = currentValue;
        }
    }
    
    /**
     * Gets the show names that were scraped.
     * 
     * @return ArrayList<String> The ArrayList of Show Names.
     */
    public ArrayList<String> getShows(){
        return showNames;
    }
    
    /**
     * Gets the shows that were scraped.
     * 
     * @return HashMap<String, String> Gets the shows that were scraped.
     */
    public HashMap<String, String> getShowsMap(){
        return shows;
    }
}
