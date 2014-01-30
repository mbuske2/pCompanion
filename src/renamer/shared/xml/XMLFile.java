package renamer.shared.xml;

/**
 * @file XMLFile.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/29/2013
 */

/**
 * A Class representing an XML File.
 */
public class XMLFile {
    /**
     * The Root Element of the XMLFile
     */
    private XMLElement rootElement;
    /**
     * The Name of the XMLFile. This does not have path, just '"something".xml'.
     */
    private String name;
    /**
     * The Path of the XMLFile. This is the directory it would be located in.
     */
    private String path;
    
    /**
     * The Basic Constructor with a name.
     * 
     * @param name The name of the XMLFile. This does not have path, just '"something".xml'.
     */
    public XMLFile(String name){
        rootElement = null;
        this.name = name;
        this.path = "";
    }
    
    /**
     * The Basic Constructor with a name and a path to the file.
     * 
     * @param name The name of the XMLFile. This does not have path, just '"something".xml'.
     * @param path The Path to the XMLFile. This is the directory it would be located in.
     */
    public XMLFile(String name, String path){
        rootElement = null;
        this.name = name;
        this.path = path;
    }
    
    /**
     * Returns true if the XMLFile has a root element.
     * 
     * @return boolean Returns true if the XMLFile has a root element.
     */
    public boolean hasRootElement(){
        return rootElement != null;
    }
    
    /**
     * Adds a new root Element to the XMLFile.
     * 
     * @param element The element to add to the XMLFile.
     */
    public void addRootElement(XMLElement element){
        rootElement = element;
    }
    
    /**
     * Gets the root XMLElement.
     * 
     * @return XMLElement The root XMLElement.
     */
    public XMLElement getRootElement(){
        return rootElement;
    }
    
    /**
     * Changes the name of the XMLFile.
     * 
     * @param name The new name of the XMLFile.
     */
    public void changeFileName(String name){
        this.name = name;
    }
    
    /**
     * Changes the directory path of the XMLFile.
     * 
     * @param path The new directory path to the XMLFile.
     */
    public void changeFilePath(String path){
        this.path = path;
    }
    
    /**
     * Gets the directory path of the XMLFile.
     * 
     * @return String The directory path of the XMLFile.
     */
    public String getFilePath(){
        return path;
    }
    
    /**
     * Gets the name of the XMLFile.
     * 
     * @return String The name of the XMLFile.
     */
    public String getFileName(){
        return name;
    }

}
