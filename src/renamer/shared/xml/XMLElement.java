package renamer.shared.xml;

import java.util.HashMap;
import java.util.Set;

/**
 * @file XMLElement.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/29/2013
 */

/**
 * A Class representing an XML Element.
 */
public class XMLElement {
    /**
     * The child Elements to this element.
     */
    private HashMap<String, XMLElement> children;
    /**
     * The Attributes belonging to an XMLElement.
     */
    private HashMap<String, String> attributes;
    /**
     * The Value of the XMLElement.
     */
    private String value;
    /**
     * The Name of the Element.
     */
    private String name;
    
    /**
     * Creates an XMLElement object with a specific name.
     * 
     * @param name The Name of the XMLElement being created.
     */
    public XMLElement(String name){
        children = new HashMap<String, XMLElement>();
        attributes = new HashMap<String, String>();
        value = null;
        this.name = name;
    }
    
    /**
     * Creates and XMLElement object with a specific name and value.
     * 
     * @param name The Name of the XMLElement being created.
     * @param val The value of the XMLElement being created. 
     */
    public XMLElement(String name, String val){
        children = new HashMap<String, XMLElement>();
        attributes = new HashMap<String, String>();
        this.value = val;
        this.name = name;
    }
    
    /**
     * Adds an Child XMLElement to this XMLElement.
     * 
     * @param element The XMLElement to add.
     */
    public void addElement(XMLElement element){
       children.put(element.getName(), element);
    }
    
    /**
     * Returns true if this XMLElement has children, false if not.
     * 
     * @return boolean Returns true if this XMLElement has children, false if not.
     */
    public boolean hasChildren(){
        return !children.isEmpty();
    }
    
    /**
     * Gets the name of the XMLElement.
     * 
     * @return String The Name of the XMLElement.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Gets the XMLElement object by its key.
     * 
     * @param elementName The key to the child element.
     * 
     * @return XMLElement Returns the XMLElement if it exists, returns null otherwise.
     */
    public XMLElement getElement(String elementName){
        return children.get(elementName);
    }
    
    /**
     * Adds a new attribute to the Element.
     * 
     * @param name The name of the Attribute.
     * @param value The value of the Attribute. 
     */
    public void addAttribute(String name, String value){
        attributes.put(name, value);
    }
    
    /**
     * Change value of attribute.
     * 
     * @param name The name of the Attribute.
     * @param newValue The new value of the attribute. 
     */
    public void changeAttrVal(String name, String newValue){
        attributes.remove(name);
        attributes.put(name, newValue);
    }
    
    /**
     * Gets the value associated with the attribute name.
     * 
     * @param attrName The Name of the Attribute.
     * 
     * @return String The value of the Attribute.
     */
    public String getAttrVal(String attrName){
        return attributes.get(attrName);
    }
    
    /**
     * Gets the value of the XMLElement.
     * 
     * @return String The value of the XMLElement.
     */
    public String getValue(){
        return value;
    }
    
    /**
     * Sets the value of the XMLElement.
     * 
     * @param val The new value for the XMLElement.
     */
    public void setValue(String val){
        this.value = val;
    }
    
    /**
     * Returns true if the element has a value, false otherwise.
     * 
     * @return boolean true if the element has a value, false otherwise.
     */
    public boolean hasValue(){
        return value != null;
    }
    
    /**
     * Returns true if the element has Attributes, false otherwise.
     * 
     * @return boolean Returns true if the element has Attributes, false otherwise.
     */
    public boolean hasAttributes(){
        return !attributes.isEmpty();
    }
    
    /**
     * Returns the number of children this XMLElement has.
     * 
     * @return Integer The number of children this XMLElement has.
     */
    public int getNumberOfChildren(){
        return children.size();
    }
    
    /**
     * Returns the number of attributes this XMLElement has.
     * 
     * @return Integer The number of attributes this XMLElement has.
     */
    public int getNumberOfAttributes(){
        return attributes.size();
    }
    
    /**
     * Gets the Attribute Names for this XMLElement.
     * 
     * @return Set The set of names for this XMLElement. 
     */
    public Set getAttributeNames(){
        return attributes.keySet();
    }
    
    /**
     * Gets the names of the children elements.
     *
     * @return Set The names of the children elements.
     */
    public Set getChildrenNames(){
        return children.keySet();
    }
    
}
