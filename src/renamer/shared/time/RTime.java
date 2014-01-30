package renamer.shared.time;

import java.io.Serializable;

/**
 * @file RTime.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/24/2013
 */

/**
 * A class for storing time for options and the like.
 */
public class RTime implements Serializable {
    /**
     * The number of years.
     */
    private int year;
    /**
     * The number of months.
     */
    private int month;
    /**
     * The number of weeks.
     */
    private int week;
    /**
     * The number of days.
     */
    private int day;
    /**
     * The number of hours.
     */
    private int hour;
    /**
     * The number of minutes.
     */
    private int minute;
    /**
     * The number of seconds.
     */
    private int second;
    /**
     * Object's version ID.
     */
    private static final long serialVersionUID = 1;
    
    
    /**
     * A constructor that makes a basic RTime object.
     */
    public RTime(){
        year = 0;
        month = 0;
        week = 0;
        day = 0;
        hour = 0;
        minute = 0;
        second = 0;
    }

    /**
     * Gets the year component of RTime.
     *
     * @return Integer The year component of RTime.
     */
    public int getYear() {
        return year;
    }
    
    /**
     * Gets the month component of RTime.
     *
     * @return Integer The month component of RTime.
     */
    public int getMonth() {
        return month;
    }
    
    /**
     * Gets the week component of RTime.
     * 
     * @return Integer The week component of RTime.
     */
    public int getWeek() {
        return week;
    }
    
    /**
     * Gets the day component of RTime.
     *
     * @return Integer The day component of RTime.
     */
    public int getDay() {
        return day;
    }    
    
    /**
     * Gets the hour component of RTime.
     *
     * @return Integer The hour component of RTime.
     */
    public int getHour() {
        return hour;
    }
    
    /**
     * Gets the minute component of RTime.
     *
     * @return Integer The minute component of RTime.
     */
    public int getMinute() {
        return minute;
    }
    
    /**
     * Gets the second component of RTime.
     *
     * @return Integer The second component of RTime.
     */
    public int getSecond() {
        return second;
    }
    

    /**
     * Sets the years component of RTime.
     *
     * @param year The new years to RTime.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets the months component of RTime.
     *
     * @param month The new months to RTime.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Sets the weeks component of RTime.
     *
     * @param week The new weeks to RTime.
     */
    public void setWeek(int week) {
        this.week = week;
    }

    /**
     * Sets the days component of RTime.
     *
     * @param day The new days to RTime.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Sets the hours component of RTime.
     * 
     * @param hour The new hours to RTime.
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Sets the minute component of RTime.
     * 
     * @param minute The new minutes to RTime.
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Sets the seconds component of RTime.
     * 
     * @param second The new seconds to RTime.
     */
    public void setSecond(int second) {
        this.second = second;
    }
}
