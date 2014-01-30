package renamer.shared.xml;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import renamer.shared.logging.Logs;

/**
 * @file XMLSaver.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/29/2013
 */
/**
 * A utilities class for saving XML Files.
 */
public class XMLSaver {

    /**
     * The Logger for the XMLSaver Class.
     */
    private static final Logger logger = Logs.getLogger(XMLSaver.class.getName());

    /**
     * Saves an XMLFile to an XML File.
     *
     * @param xml The XMLFile to save to file.
     *
     * @return boolean Returns true if saving was successful, false if there was
     * an error.
     */
    public static boolean saveXMLObj(XMLFile xml) {
        String filePath = xml.getFilePath() + xml.getFileName();
        if (!xml.hasRootElement()) {
            Logs.logWarning("XMLOb \"" + filePath + "\" Has No Content!", logger);
            return false;
        }
        Logs.logInfo("Building XML File \"" + filePath + "\".", logger);
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = createDocument(xml, docBuilder);
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
            Logs.logInfo("File \"" + filePath + "\" Saved Successfully!", logger);
            return true;
        } catch (Exception e){
            Logs.logException(e, "Failed To Save XML File: ", logger);
        }
        return false;
    }

    /**
     * Creates an XML Document File.
     * 
     * @param xml The XML File object to build the Document with.
     * @param docBuilder The DocumentBuilder to create the Document with
     * 
     * @return Document The built XML document.
     */
    private static Document createDocument(XMLFile xml, DocumentBuilder docBuilder) {
     //   Logs.logInfo("Begin Building Document.", logger);
        Document doc = docBuilder.newDocument();
        XMLElement rootElement = xml.getRootElement();
        doc = createRootElement(rootElement, doc);
        return doc;
    }
    
    /**
     * Creates the Root Element.
     * 
     * @param element The root Element of the XML File.
     * @param doc The document that is being created.
     * 
     * @return Document The built XML document.
     */
    private static Document createRootElement(XMLElement element, Document doc){
     //   Logs.logInfo("Creating Root Element: " + element.getName(), logger);
        Element ele = doc.createElement(element.getName());
        if (element.hasAttributes()) {
            ele = addAttributes(ele, element);
        }
        if (element.hasChildren()) {
            ele = dealWithChildren(ele, element, doc);
        }
        doc.appendChild(ele);
        return doc;
    }

    /**
     * Creates and Element and all attributes, values, and children.
     * 
     * @param element The element to create.
     * @param doc The document that is being created.
     * 
     * @return Element The Created Element.
     */
    private static Element createElement(XMLElement element, Document doc) {
    //    Logs.logInfo("Creating Element: " + element.getName(), logger);
        Element ele = doc.createElement(element.getName());
        if (element.hasAttributes()) {
            ele = addAttributes(ele, element);
        }
        if (element.hasValue()){
            ele = createTextNode(ele, element, doc);
        }
        if (element.hasChildren()) {
            ele = dealWithChildren(ele, element, doc);
        }
        return ele;
    }
    
    /**
     * Creates a TextNode, or the Value Field, for the element.
     * 
     * @param ele The element to add the field to.
     * @param element Contains the value to add to the Element.
     * @param doc The document that is being created.
     * 
     * @return Element The Element with the Added TextNode.
     */
    private static Element createTextNode(Element ele, XMLElement element, Document doc){
        String val = element.getValue();
    //    Logs.logInfo("Creating Text Node: " + val, logger);
        ele.appendChild(doc.createTextNode(val));
        return ele;
    }

    /**
     * First call to build the children.
     * 
     * @param ele The Element to add the child to.
     * @param element The XMLElement to get the children from.
     * @param doc The document that is being created.
     * 
     * @return Element The Element with the children added.
     */
    private static Element dealWithChildren(Element ele, XMLElement element, Document doc) {
        Set s = element.getChildrenNames();
        Iterator it = s.iterator();
        return recurseChild(ele, element, it, doc);
    }
    
    /**
     * Second call to build children. Recurses Downwards.
     * 
     * @param ele The Element to add the child to.
     * @param element The XMLElement to get the children from.
     * @param it The current Iterator for the children.
     * @param doc The document that is being created.
     * 
     * @return  Element The Element with the children added.
     */
    private static Element recurseChild(Element ele, XMLElement element, Iterator it, Document doc){
        if (it.hasNext()) {
            String name = (String) it.next();
            XMLElement childElement = element.getElement(name);
            ele = recurseChild(ele, element, it, doc);
            Element e = createElement(childElement, doc);
            ele.appendChild(e);
        }
        return ele;
    }

    /**
     * Adds attributes to an Element.
     * 
     * @param ele The Element to add the attributes to.
     * @param e The XMLElement to get the attributes from.
     * 
     * @return Element The Element with the Attributes applied.
     */
    private static Element addAttributes(Element ele, XMLElement e) {
    //    Logs.logInfo("Creating Attributes For: " + e.getName(), logger);
        Set s = e.getAttributeNames();
        Iterator it = s.iterator();
        while (it.hasNext()) {
            String name = (String) it.next();
            String value = (String) e.getAttrVal(name);
            ele.setAttribute(name, value);
        }
        return ele;
    }
}
