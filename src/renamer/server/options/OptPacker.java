package renamer.server.options;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import renamer.shared.time.RTime;
import renamer.shared.xml.DefaultPacker;
import renamer.shared.xml.XMLElement;
import renamer.shared.xml.XMLFile;

/**
 * @file OptPacker.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/30/2013
 */

/**
 * A Utilities Class used to pack the user's settings.
 */
public class OptPacker extends DefaultPacker {
    
    /**
     * The OptClass Object to transform.
     */
    private OptClass options;
    
    /**
     * Constructs a OptPacker object with the given OptClass.
     * 
     * @param options The Options to build the XMLFile Object from.
     
     * @throws OptPackException Thrown if the options object being passed to the
     * OptPacker is null.
     */
    public OptPacker(OptClass options) throws OptPackException {
        if (options == null){
            throw new OptPackException();
        }
        this.options = options;
    }
    
    /**
     * Packages the <code>OptClass</code> Object into an <code>XMLFile</code> Object.
     * 
     * @return <code>XMLFile</code> The Packed XMLFile Object.
     */
    @Override
    protected XMLFile pack(){
        XMLElement root = new XMLElement("options");
        XMLFile xml = new XMLFile(OptionsData.FILENAME, OptionsData.OPTIONSPATH);
        xml.addRootElement(root);
        //pack the general options.
        XMLElement gen = this.packGeneral();
        root.addElement(gen);
        //pack the filter options.
        XMLElement fil = this.packFilter();
        root.addElement(fil);
        //pack the subtitle options.
        XMLElement sub = this.packSubtitles();
        root.addElement(sub);
        //pack the TVDB options.
        XMLElement tvd = this.packTVDB();
        root.addElement(tvd);
        
        return xml;
    }
    
    /**
     * Packs the General Options.
     * 
     * @return XMLElement The XMLElement with the General Options.
     */
    private XMLElement packGeneral(){
        XMLElement gen = new XMLElement("general");
       
        XMLElement defaultDir = new XMLElement("defaultDirectory");
        gen.addElement(defaultDir);
        defaultDir.setValue(options.getDefaultDirectory());
        
        
        return gen;
    }
    
    /**
     * Packs the Filter Options.
     * 
     * @return XMLElement The XMLElement with the Filter Options.
     */
    private XMLElement packFilter(){
        XMLElement fil = new XMLElement("filters");
        
        return fil;
    }
    
    /**
     * Packs the Subtitle Options.
     * 
     * @return XMLElement The XMLElement with the Subtitle Options.
     */
    private XMLElement packSubtitles(){
        XMLElement sub = new XMLElement("subtitles");
        
        //Language Index
        XMLElement lanInd = new XMLElement("languageIndex", Integer.toString(options.getLanguageIndex()));
        sub.addElement(lanInd);
        
        //Language Key
        XMLElement lanKey = new XMLElement("languageKey", options.getLanguageKey());
        sub.addElement(lanKey);
        
        //HashMap Data
        HashMap<String, String> languages = options.getLanguages();
        XMLElement langs = new XMLElement("languages");
        Set s = languages.keySet();
        Iterator it = s.iterator();
        while (it.hasNext()){
            String key = (String) it.next();
            String value = (String) languages.get(key);
            XMLElement lang = new XMLElement(key, value);
            langs.addElement(lang);
        }
        sub.addElement(langs);
        
        return sub;
    }
    
    /**
     * Packs the TVDB Options.
     * 
     * @return XMLElement The XMLElement with the TVDB Options.
     */
    private XMLElement packTVDB(){
        XMLElement sub = new XMLElement("tvdb");
        
        //Language Index
        XMLElement tvLan = new XMLElement("tvLang", options.getTVLang());
        sub.addElement(tvLan);
        
        //Scrape Time
        XMLElement time = packRTime(options.getScrapeInterval());
        sub.addElement(time);
        
        return sub;
    }
    
    /**
     * Packs the RTime Values.
     * 
     * @param rtime The Time Object To Pack.
     * 
     * @return XMLElement The XMLElement that represents an RTime Object.
     */
    private XMLElement packRTime(RTime rtime){
        XMLElement time = new XMLElement("scrapeInterval");
        
        XMLElement year = new XMLElement("year", Integer.toString(rtime.getYear()));
        XMLElement month = new XMLElement("month", Integer.toString(rtime.getMonth()));
        XMLElement week = new XMLElement("week", Integer.toString(rtime.getWeek()));
        XMLElement day = new XMLElement("day", Integer.toString(rtime.getDay()));
        XMLElement hour = new XMLElement("hour", Integer.toString(rtime.getHour()));
        XMLElement min = new XMLElement("minute", Integer.toString(rtime.getMinute()));
        XMLElement sec = new XMLElement("second", Integer.toString(rtime.getSecond()));
        
        time.addElement(sec);
        time.addElement(min);
        time.addElement(hour);
        time.addElement(day);
        time.addElement(week);
        time.addElement(month);
        time.addElement(year);
        
        return time;
    }
    
    public class OptPackException extends Exception {
    /**
     * Creates a new instance of
     * <code>OptPackException</code> without detail message.
     */
    public OptPackException() {
        super("OptClass object is NULL. OptPacker Cannot be created.");
    }

    /**
     * Constructs an instance of
     * <code>OptPackException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public OptPackException(String msg) {
        super(msg);
    }
}
}
