package renamer.server.options;

import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import renamer.shared.logging.Logs;
import renamer.shared.time.RTime;

/**
 * @file OptionsHandler.java
 * @author Matt Buske
 * @version 0.2A
 * @date 01/02/2014
 */
/**
 * A Handler Class To Parse the Options file.
 */
public class OptionsHandler extends DefaultHandler {

    /**
     * The Logger for the OptionsHandler Class.
     */
    private static final Logger logger = Logs.getLogger(OptionsHandler.class.getName());
    /**
     * The Current Value of the Current Element.
     */
    private String currentValue;
    /**
     * boolean Flag that denotes if it is in the RTime Object.
     */
    private boolean inScrapeInterval;
    /**
     * boolean Flag that denotes if it is in the General Options Or Not.
     */
    private boolean inGeneral;
    /**
     * boolean Flag that denotes if it is in the TVDB Options or Not.
     */
    private boolean inTVDB;
    /**
     * boolean Flag that denotes if it is in the Subtitle Options or Not.
     */
    private boolean inSubs;
    /**
     * boolean Flag that denotes if it is in the Filter Options or Not.
     */
    private boolean inFil;
    /**
     * boolean Flag that denotes if it is in the Languages HashMap Or Not.
     */
    private boolean inLang;
    /**
     * The OptClass Object The Info Is Being Parsed Into.
     */
    private OptClass options;
    /**
     * The RTime Object for the Scrape Interval.
     */
    private RTime scrape;

    /**
     * Basic Constructor.
     */
    public OptionsHandler() {
        options = new OptClass();
        inScrapeInterval = false;
        inGeneral = false;
        inFil = false;
        inSubs = false;
        inTVDB = false;
        inGeneral = false;
        inLang = false;
        scrape = options.getScrapeInterval();
    }
    
    /**
     * Gets the OptClass File That Was Parsed.
     *
     * @return OptClass The Options File That Was Parsed.
     */
    public OptClass getOptions(){
        return options;
    }
    

    /**
     * What to do at the Start of the Document.
     *
     * @throws SAXException If there is an exception in the Sax Parser.
     */
    @Override
    public void startDocument() throws SAXException {
        Logs.logInfo("Parsing Options File...", logger);
    }

    /**
     * What to do at the End of the Document.
     *
     * @throws SAXException If there is an exception in the Sax Parser.
     */
    @Override
    public void endDocument() throws SAXException {
        Logs.logInfo("Parsing of Options File Complete.", logger);
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
        if (qName.equals("scrapeInterval")) {
            inScrapeInterval = true;
        } else if (qName.equals("general")) {
            inGeneral = true;
        } else if (qName.equals("subtitles")) {
            inSubs = true;
        } else if (qName.equals("filters")) {
            inFil = true;
        } else if (qName.equals("tvdb")) {
            inTVDB = true;
        } else if (qName.equals("languages")) {
            inLang = true;
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
        if (inGeneral){
            if (name.equals("general")){
                inGeneral = false;
            } else {
                parseGeneral(name);
            }
        } else if (inFil){
            if (name.equals("filters")){
                inGeneral = false;
            } else {
                parseFilters(name);
            }
        } else if (inSubs){
            if (name.equals("subtitles")){
                inSubs = false;
            } else {
                parseSubtitles(name);
            }
        } else if (inTVDB){
            if (name.equals("tvdb")){
                inTVDB = false;
            } else {
                parseTVDB(name);
            }
        }
         
  
    }
    
    /**
     * Parses the General Options.
     * 
     * @param name The Name of the Element Being Parsed.
     */
    private void parseGeneral(String name){
        if (name.equals("defaultDirectory")) {
            options.setDefaultDirectory(currentValue);
        }
    }
    
    /**
     * Parses the Filter Options.
     * 
     * @param name The Name of the Element Being Parsed.
     */
    private void parseFilters(String name){
    }
    
    /**
     * Parses the Subtitle Options.
     * 
     * @param name The Name of the Element Being Parsed.
     */
    private void parseSubtitles(String name){
        if (inLang){
            if (name.equals("languages")){
                inLang = false;
            } else {
                parseLang(name);
            }
        } else {
            if (name.equals("languageKey")) {
                options.setLanguageKey(currentValue);
            } else if (name.equals("languageIndex")){
                options.setLanguageIndex(strToInt(currentValue));
            }
        }
    }
    
    /**
     * Parses the TVDB Options.
     * 
     * @param name The Name of the Element Being Parsed.
     */
    private void parseTVDB(String name){
        if (inScrapeInterval){
            if (name.equals("scrapeInterval")){
                inScrapeInterval = false;
                options.setScrapeInterval(scrape);
            } else {
                parseRTimeTVDB(name);
            }
        } else {
            if (name.equals("tvLang")) {
                options.setTVLang(currentValue);
            }
        }
    }
    
    /**
     * Parses the Languages Part of Subtitles.
     * 
     * @param name The Name of the Element Being Parsed.
     */
    private void parseLang(String name){
    }
    
    /**
     * Parses the RTime Part of The TVDB.
     * 
     * @param name The Name of the Element Being Parsed.
     */
    private void parseRTimeTVDB(String name){
        if (name.equals("year")){            
            scrape.setYear(strToInt(currentValue));
        } else if (name.equals("month")){
            scrape.setMonth(strToInt(currentValue));
        } else if (name.equals("week")){
            scrape.setWeek(strToInt(currentValue));
        } else if (name.equals("day")){
            scrape.setDay(strToInt(currentValue));
        } else if (name.equals("hour")){
            scrape.setHour(strToInt(currentValue));
        } else if (name.equals("minute")){
            scrape.setMinute(strToInt(currentValue));
        } else if (name.equals("second")){
            scrape.setSecond(strToInt(currentValue));
        }
    }
    
    /**
     * Converts a String to a Number.
     *
     * @param s The String to Convert.
     * 
     * @return Integer The Converted String.
     */
    private int strToInt(String s){
        try{
            return Integer.parseInt(s);
        }catch (Exception e){
            Logs.logException(e, "Invalid Options XML Format For String " + s +": ", logger);   
        }
        return 0;
        
    }
    
}
