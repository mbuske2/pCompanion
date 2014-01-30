package renamer.shared.logging;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @file LogFormatter.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/29/2013
 */

/**
 * A class to format the log entries for the program.
 */
public class LogFormatter extends Formatter {
    
    /**
     * Basic Constructor. Check Formatter's Constructor for more.
     */
    public LogFormatter() {
        super();
    }
    
    /**
     * Gets the LogRecord in this applications custom format. Eventually, I will 
     * put an example here.
     * 
     * @param record The LogRecord to format.
     * @return String The formatted LogRecord.
     */
    @Override
    public String format(LogRecord record) {
        String dateStamp = Logs.formatDate(new Date(record.getMillis()));
        String log = dateStamp + " " + record.getLoggerName() + "." + record.getLevel() + ": " + record.getMessage();
        if (record.getThrown() != null){
            log += record.getThrown().getMessage();
        }
        log += "\r\n";
        return log;
    }
}
