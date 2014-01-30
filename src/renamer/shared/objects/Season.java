package renamer.shared.objects;

import java.util.ArrayList;

/**
 * @file Season.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/17/2013
 */

/**
 * A class that represents a Season of a TV show.
 */
public class Season {
    /**
     * The Season Number.
     */
    private String seasonNumber;
    /**
     * The episodes that belong to this season.
     */
    private ArrayList<Episode> episodes;
    
    /**
     * Constructor to build a new Season.
     */
    public Season(){
        this.episodes = new ArrayList<Episode>();
    }

    /**
     * Gets the season number.
     *
     * @return String The season number.
     */
    public String getSeasonNumber() {
        return seasonNumber;
    }

    /**
     * Sets the season number for the season.
     *
     * @param seasonNumber The new season number for the season.
     */
    public void setSeasonNumber(String seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    /**
     * Gets the episodes that belong to this season.
     *
     * @return ArrayList<Episode> The episodes that belong to this season.
     */
    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    /**
     * Sets the episodes for this season.
     *
     * @param episodes The new episodes that belong to this season.
     */
    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    } 
}
