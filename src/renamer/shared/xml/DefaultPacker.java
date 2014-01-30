package renamer.shared.xml;

/**
 * @file DefaultPacker.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/30/2013
 */

/**
 * An Abstract Class used to pack objects into an XMLFile Object.
 */
public abstract class DefaultPacker {
    
    /**
     * Gets the XMLFile Object.
     * 
     * @return XMLFile The XMLFile Object.
     */
    public XMLFile getXMLFile(){
        return this.pack();
    }
    /**
     * Abstract method for XMLFile Pack();
     * 
     * @return XMLFile The Packed XMLFile Object.
     */
    protected abstract XMLFile pack();
}
