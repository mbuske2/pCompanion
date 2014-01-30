package renamer.shared.objects;

/**
 * @file Episode.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/17/2013
 */

/**
 * A class that represents an episode of a TV show.
 */
public class Episode {
    /**
     * The Name of the Episode.
     */
    private String name;
    /**
     * The Episode Number in "Exx" format.
     */
    private String episodeNumber;
    /**
     * The Season Number in "Sxx" format.
     */
    private String seasonNumber;
    /**
     * The Series Name this episode belongs to.
     */
    private String seriesName;
    /**
     * The full name of the episode, ie, "Series Name - SxxExx - Name".
     */
    private String fullName;
    
    /**
     * Default Constructor creating empty Episode Object.
     */
    public Episode(){
        
    }

    /**
     *  Returns the name of the episode.
     * 
     * @return String The name of the Episode.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the episode.
     * 
     * @param name The new name of the episode.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the episode number in "Exx" form.
     * 
     * @return String The episode number in "Exx" form.
     */
    public String getEpisodeNumber() {
        return episodeNumber;
    }

    /**
     * Sets the episode number for the new episode in "Exx" form.
     * 
     * @param episodeNumber The new episode number in "Exx" form for the episode.
     */
    public void setEpisodeNumber(String episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    /**
     * Gets the season number in "Sxx" form for the episode.
     * 
     * @return String The season number in "Sxx" form for the episode.
     */
    public String getSeasonNumber() {
        return seasonNumber;
    }

    /**
     * Sets the season number in "Sxx" form the episode belongs to.
     * 
     * @param seasonNumber The new season number for this episode.
     */
    public void setSeasonNumber(String seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    /**
     * Gets the name of the series this episode belongs to.
     * 
     * @return String The series name this episode belongs to.
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * Sets the series name the episode belongs to.
     * 
     * @param seriesName The new series name for the episode.
     */
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    /**
     * Returns the full name of the episode.
     * 
     * @return String The full name of the episode.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the episode object. 
     * 
     * @param fullName The new Full Name of the Episode.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    
    
}
