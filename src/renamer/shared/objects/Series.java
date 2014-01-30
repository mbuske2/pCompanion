package renamer.shared.objects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @file Series.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/17/2013
 */

/**
 * A class representing a TV Series.
 */
public class Series {

    /**
     * The Name of the Series.
     */
    protected String name;
    /**
     * The Series ID from TVDB.
     */
    protected String id;
    /**
     * The number of seasons the Series has.
     */
    protected int numberSeasons;
    /**
     * If the series has specials or not.
     */
    protected boolean hasSpecials;
    /**
     * The Seasons that belong to this series.
     */
    protected HashMap<String, Season> seasons;

    /**
     * Constructor for a series. Useless unless you use the series builder.
     *
     * @param id The ID of the new series.
     */
    protected Series(String id) {
        this.id = id;
        seasons = new HashMap<String, Season>();
    }

    /**
     * Gets the name of the series.
     * 
     * @return String The name of the series.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the Series ID From TVDB.
     * 
     * @return String The series id from TVDB.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the number of seasons the series has.
     *
     * @return Integer The number of seasons the series has.
     */
    public int getNumberSeasons() {
        return numberSeasons;
    }

    /**
     * Gets whether or not the series has specials.
     *
     * @return boolean true if the series has specials, false otherwise.
     */
    public boolean hasSpecials() {
        return hasSpecials;
    }

    /**
     * Gets the seasons that belong to this series.
     *
     * @return HashMap<String, Season> The seasons that belong to this Series.
     */
    public HashMap<String, Season> getSeasons() {
        return seasons;
    }
    
    
    @Override
    public String toString() {
        String t = "";
        if (this.hasSpecials) {
            for (int i = 0; i < seasons.size(); i++) {
                Season currentSeason = seasons.get(Integer.toString(i));
                t = t + "Season " + i + ":\n";
                if (currentSeason != null) {
                    ArrayList<Episode> eps = currentSeason.getEpisodes();
                    for (int j = 0; j < eps.size(); j++) {
                        Episode currentEp = eps.get(j);
                        t = t + currentEp.getFullName() + "\n";
                    }
                }
            }
        } else {
            for (int i = 0; i < seasons.size(); i++) {
                Season currentSeason = seasons.get(Integer.toString(i+1));
                t = t + "Season " + (i + 1) + ":\n";
                if (currentSeason != null) {
                    ArrayList<Episode> eps = currentSeason.getEpisodes();
                    for (int j = 0; j < eps.size(); j++) {
                        Episode currentEp = eps.get(j);
                        t = t + currentEp.getFullName() + "\n";
                    }
                }
            }
        }
        //System.out.println(t);
        return t;
    }
}
