package renamer.shared.objects;

import java.util.HashMap;

/**
 * @file SeriesBuilder.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/17/2013
 */

/**
 * A class that builds the Series object.
 */
public class SeriesBuilder extends Series {
    /**
     * Private constructor for the series builder.
     * 
     * @param id The id of the Series.
     */
    private SeriesBuilder(String id){
        super(id);
    }
    
    /**
     * Returns a new instance of the series builder.
     * 
     * @param id the id for the new series.
     * @return SeriesBuilder A new series object with the given id.
     */
    public static SeriesBuilder getNewInstance(String id){
        return new SeriesBuilder(id);
    }
    
    /**
     * Sets the series name.
     * 
     * @param seriesName The name to give to the series. 
     */
    public void setSeriesName(String seriesName){
        super.name = seriesName;
    }
    
    /**
     * Sets the seasons for the Series Object. It also Sets weather the series 
     * has specials or not and the total number of seasons it has.
     * 
     * @param seasons The HashMap of seasons for the series.
     */
    public void setSeasons(HashMap<String, Season> seasons){
        super.seasons = seasons;
        if (seasons.containsKey("0")){
            super.hasSpecials = true;
            super.numberSeasons = seasons.size() - 1;
        } else {
            super.numberSeasons = seasons.size();
        }
    }
}
