package renamer.server.options;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import renamer.shared.file.FileData;
import renamer.shared.file.FileSys;
import renamer.shared.logging.Logs;
import renamer.shared.xml.XMLFile;
import renamer.shared.xml.XMLParser;
import renamer.shared.xml.XMLSaver;

/**
 * @file FileOptions.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/17/2013
 */

/**
 * A utilities class for Loading/writing options.
 */
public class FileOptions {
    
    /**
     * The Logger for the FileOptions Class.
     */
    private static final Logger logger = Logs.getLogger(FileOptions.class.getName());
    
    /**
     * Checks to see if the options file and options directory exist. If they do not,
     * it attempts to create them. It then creates a default options file object and returns
     * it. If the directory exists and an options file exists, it will be loaded and returned.
     * 
     * @return OptClass Returns the OptClass Object. Returns null if it fails.
     */
    public static OptClass checkOpt() {
        File f = checkNFixPath();
        try {
            if (f.exists()) {
                Logs.logInfo("Options File Found: " + f.getAbsolutePath() + " loading...", logger);
                OptClass opt = loadFile(f);
                opt.setFile(f);
                return opt;
            } else {
                Logs.logInfo("Options File Not Found. Creating and Loading The Defaults.", logger);
                f.createNewFile();
                return createDefaultOptionsFile(f);
            }
        } catch (IOException ex) {
            Logs.logException(ex, "Could Not Create Options File: ", logger);
            return null;
        }
    }

    /**
     * Checks the integrity of the file structure and attempts to fix it if it is
     * broken.
     * 
     * @return File The Intact file structure. Returns null if it cannot be fixed.
     */
    private static File checkNFixPath() {
        if(!FileSys.checkDirectory(FileData.APPDIR)){
            Logs.logSevere("Failed To Create: " + FileData.APPDIR, logger);
            return null;
        }
        if (!FileSys.checkDirectory(OptionsData.OPTIONSPATH)){
            Logs.logSevere("Failed To Create: " + OptionsData.OPTIONSPATH, logger);
            return null;
        }
        File f = new File(OptionsData.FULLPATH);
        return f;
    }

    /**
     * Saves an OptClass object To The Disk.
     * 
     * @param options The OptClass Object To Save.
     * 
     * @return boolean Returns true if it was successful, false if it was not.
     */
    public static boolean saveOpt(OptClass options) {
        File f = checkNFixPath();
        if (!FileSys.checkFile(f.getAbsolutePath())) {
            Logs.logSevere("Could Not Save Options from path: " + f.getAbsolutePath(), logger);
            return false;
        }
        options.setFile(f);
        return saveFile(options);
    }

    /**
     * Loads the Options Profile and returns it.
     *
     * @param options The file the options profile will be saved to.
     * 
     * @return OptClass The Options Profile that was loaded from file.
     */
    private static OptClass loadFile(File options) {
        try {
            OptionsHandler opHan = new OptionsHandler();
            XMLParser par = new XMLParser(opHan);
            opHan = (OptionsHandler) par.parse(options);
            OptClass opt = opHan.getOptions();
            Logs.logInfo("Options Loaded: " + options.getAbsolutePath(), logger);
            return opt;
        } catch (Exception ex) {
            Logs.logException(ex, "Could Not Load Options: ", logger);
            return null;
        }
    }

    /**
     * Saves a Specific Options Profile to File.
     *
     * @param opt The Options Profile to save to File.
     * 
     * @return boolean Returns true if the options profile was saved correctly,
     * returns false if there was an error saving.
     */
    private static boolean saveFile(OptClass opt) {
        try {
            OptPacker optPack = new OptPacker(opt);
            XMLFile xf = optPack.getXMLFile();
            XMLSaver.saveXMLObj(xf);
            return true;
        } catch (Exception ex) {
            Logs.logException(ex, "Could Not Save Options: ", logger);
            return false;
        }
    }

    /**
     * Creates the Default Options File.
     * 
     * @return OptClass The Default Options File. Returns null if there is an error.
     */
    public static OptClass createDefaultOptionsFile() {
        File f = checkNFixPath();
        if (f == null) return null;
        if (!FileSys.checkFile(f.getAbsolutePath())) {
            Logs.logSevere("Could Not Create Default Options File In: " + f.getAbsolutePath(), logger);
            return null;
        }
        return createDefaultOptionsFile(f);
    }

    /**
     * Creates the default options file in the given directory.
     * 
     * @param dir The Directory where the file resides.
     * @return OptClass The Default Options Object. Returns null if it failed to save.
     */
    private static OptClass createDefaultOptionsFile(File dir) {
        OptClass options = new OptClass();
        options.setFile(dir);
        return (saveFile(options)) ? options : null;
    }
}
