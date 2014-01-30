package renamer.server.options;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import renamer.shared.time.RTime;

/**
 * @file OptClass.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/17/2013
 */

/**
 * A Class used to store the user's settings.
 */
public class OptClass {    
    //General panel options
    /**
     * The default directory the user wants to have the file chooser to open in.
     */
    private String defaultDirectory;
    
    //Subtitle panel options
    /**
     * The index of the user's language in Constant Data.
     */
    private int languageIndex;
    /**
     * The language key used to get the language tag in the hashmap language.
     */
    private String languageKey;
    
    /**
     * The hashmap containing the languages and tags.
     */
    private HashMap<String, String> languages = new HashMap<String, String>();
    /**
     * The interval between scrapes to TVDB
     */
    private RTime scrapeInterval;
    /**
     * The language for the TVDB.
     */
    private String tvLang;
    
    
    
    /**
     * This has no used as of yet.
     */
    private boolean renameForSubs;
    /**
     * Reference to the Objects File location.
     */
    private File file;
    /**
     * The user's custom filters for video extentions.
     */
    private ArrayList<String> customFilters = new ArrayList<String>();
    
    
    /**
     * Creates the default user options object.
     */
    public OptClass(){
        defaultDirectory = System.getProperty("user.home");
        renameForSubs = false;
        languageIndex = 0;
        languageKey = OptionsData.LANGUAGES[languageIndex];
        scrapeInterval = new RTime();
        scrapeInterval.setDay(1);
        tvLang = "en";
        loadHashMap();
    }
    
    
    /**
     * Loads the languages HashMap with the data from OptionsData, the
     * keys are LANGUAGES and the objects are LANGUAGETAGS. This happens 
     * during the initialization of the class.
     */
    private void loadHashMap(){
        for (int i = 0; i < OptionsData.LANGUAGES.length; i++){
            languages.put(OptionsData.LANGUAGES[i], OptionsData.LANGUAGETAGS[i]);
        }
    }
    
    /*
     * Get Methods
     */
    /**
     * Gets the language the user uses for the TVDB.
     * 
     * @return String The Language code the user set for the TVDB.
     */
    public String getTVLang(){
        return tvLang;
    }
    
    /**
     * Gets the interval between scrapes.
     * 
     * @return RTime The integer between scrapes to the TVDB.
     */
    public RTime getScrapeInterval(){
        return scrapeInterval;
    }
    
    /**
     *  Returns the index of the preferred language within LANGUAGES and LANGUAGETAGS.
     * 
     * @return Integer The index pertaining to which language in the array in OptionsData.
     */
    public int getLanguageIndex() {
        return languageIndex;
    }
    
    /**
     *  Returns the language tag used in subtitle naming.
     * 
     * @return String The language tag for the user.
     */
    public String getLanguageTag(){
        return languages.get(languageKey);
    }
    
    /**
     *  Returns the user's custom filters for video.
     * 
     * @return ArrayList<String> The user's custom video filters.
     */
    public ArrayList<String> getCustomFilters() {
        return customFilters;
    }
    
    /**
     *  Returns OptionsData's FILTERS
     * 
     * @return String[] OptionsData's FILTERS
     */
    public static String[] getFilters() {
        return OptionsData.FILTERS;
    }

    /**
     *  Returns OptionsData's SUBFILTERS
     * 
     * @return String[] OptionsData's SUBFILTERS
     */
    public static String[] getSubFilters() {
        return OptionsData.SUBFILTERS;
    }

    /**
     *  This function has no purpose as of yet.
     * 
     * @return boolean Returns a boolean of some sort.
     */
    public boolean isRenameForSubs() {
        return renameForSubs;
    }
    
    /**
     *  Returns the File where the Options object is saved.
     * 
     * @return File The file path the User Object is associated with.
     */
    public File getFile() {
        return file;
    }
    
    /**
     *  Returns the user's default working directory (Where the file chooser opens, ect...).
     * 
     * @return String Returns the User's preferred default working Directory.
     */
    public String getDefaultDirectory() {
        return defaultDirectory;
    }
    
    /**
     * Gets the Language Key.
     * 
     * @return String The Language Key.
     */
    public String getLanguageKey() {
        return languageKey;
    }
    
    /**
     * Gets The Languages for the Subtitles.
     * 
     * @return HashMap<String, String> The Languages for the Subtitles.
     */
    public HashMap<String, String> getLanguages() {
        return languages;
    }   
    
     
    /*
    *   Set Methods
    */
    /**
     * Sets the tvLang for use with the TVDB.
     * 
     * @param tvLang The language tag to use with the TVDB.
     */
    public void setTVLang(String tvLang){
        this.tvLang = tvLang;
    }
    
    /**
     * Sets the time interval between scrapes to the TVDB.
     * 
     * @param scrapeInterval The new scrape Interval.
     */
    public void setScrapeInterval(RTime scrapeInterval){
        this.scrapeInterval = scrapeInterval;
    }
    
    /**
     *  Sets the language index to be used by options.
     * 
     * @param languageIndex The new language index.
     */
    public void setLanguageIndex(int languageIndex) {
        this.languageIndex = languageIndex;
    }
    
    /**
     *  Sets the file associated with this Options Object.
     *  
     * @param file The new File.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     *  Sets the new default working Directory.
     * 
     * @param defaultDirectory The new default working directory.
     */
    public void setDefaultDirectory(String defaultDirectory) {
        this.defaultDirectory = defaultDirectory;
    }
 
    /**
     *  This method sets the language index, as well as pulls the languageKey and 
     *  languageTag from LANGUAGES and LANGUAGETAGS in OptionsData using the language
     *  index and sets them as well.
     * 
     * @param index The selected Language index.
     */
    public void setSelectedLanguage(int index){
        setLanguageIndex(index);
        languageKey = OptionsData.LANGUAGES[languageIndex];
    }
    
    /**
     * Sets the Language Key For Options.
     * 
     * @param key The New Language Key.
     */
    public void setLanguageKey(String key){
        this.languageKey = key;
    }

}