package renamer.shared.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @file XMLParser.java
 * @author Matt Buske
 * @version 0.2A
 * @date 01/02/2014
 */

/**
 * A Class To Parse Files.
 */
public class XMLParser {
    
    
    /**
     * The Handler that is stored.
     */
    private DefaultHandler handler;
    
    /**
     * Constructor that builds the class with a Handler Object.
     *
     * @param handler The Handler To Which This XMLParser Will Use.
     */
    public XMLParser(DefaultHandler handler){
        this.handler = handler;
    }
    
    /**
     * Parses the file and returns the handler it parsed with.
     * 
     * @param path The File To Parse.
     * 
     * @return DefaultHandler The Handler that parsed the file.
     * 
     * @throws ParserConfigurationException If there is a ParserConfig Exception
     * @throws SAXException If There is a SAXException.
     * @throws IOException If There is an IOException.
     */
    public DefaultHandler parse(File path) throws ParserConfigurationException, SAXException, IOException{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(path, handler);
        return handler;
    }
    
    /**
     * Parses the InputSource and returns the handler it parsed with.
     * 
     * @param in The InputSource To Parse.
     * 
     * @return DefaultHandler The Handler that parsed the file.
     * 
     * @throws ParserConfigurationException If there is a ParserConfig Exception
     * @throws SAXException If There is a SAXException.
     * @throws IOException If There is an IOException.
     */
    public DefaultHandler parse(InputSource in) throws ParserConfigurationException, SAXException, IOException{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(in, handler);
        return handler;
    }
    
    
}
