package renamer.shared.file;

/**
 * @file PathUt.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/17/2013
 */

/**
 * A class for Path/file name utilities.
 */
public class PathUt {
    /**
     * Takes a file path and returns the extension of the file.
     * 
     * @param file The path/file name that you need to get the extension of.
     * 
     * @return String Returns the extension of the file/path
     */
    public static String getFileExtension(String file){
        String sp[] = file.split("\\.");
        String ext = "." + sp[sp.length-1];
        return ext;
    }
    
    /**
     * Checks the file against the known extensions and returns true if the file
     * is of that extension. False otherwise.
     * 
     * @param ext The array of extentions that you want.
     * @param fileName The fileName to check.
     * 
     * @return boolean Returns true if the file is of a wanted extension, false otherwise.
     */
    public static boolean checkExtension(String[] ext, String fileName){
        String extension = getFileExtension(fileName);
        for (int i = 0; i < ext.length; i++){
            if (extension.equals(ext[i])){
                return true;
            }
        }
        return false;
    }
    
    
    
}
