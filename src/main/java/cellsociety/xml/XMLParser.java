package cellsociety.xml;

import cellsociety.models.GameOfLifeModel;
import cellsociety.models.PercolationModel;
import cellsociety.models.SegregationModel;
import cellsociety.models.SimulationModel;
import cellsociety.models.SpreadingFireModel;
import cellsociety.models.WaTorModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * This class handles parsing XML files and returning a completed object.
 *
 * @author Rhondu Smithwick
 * @author Robert C. Duvall
 * @author Cynthia France
 */
public class XMLParser {
  public static final String ERROR_MESSAGE = "XML file does not represent %s";
  private final DocumentBuilder DOCUMENT_BUILDER;


  /**
   * Create parser for XML files of given type.
   */
  public XMLParser () throws XMLException {
    DOCUMENT_BUILDER = getDocumentBuilder();
    //TYPE_ATTRIBUTE = type;
  }

  /**
   * Get data contained in this XML file as an object
   */
  public Map<String, String> getInformation (File dataFile) throws XMLException {
    Element root = getRootElement(dataFile);

    Map<String, String> results = new HashMap<>();
    for (String field : SimulationModel.DATA_FIELDS) {
      results.put(field, getTextValue(root, field));
    }
    return results;
  }

  // get root element of an XML file
  private Element getRootElement (File xmlFile) throws XMLException {
    try {
      DOCUMENT_BUILDER.reset();
      Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
      return xmlDocument.getDocumentElement();
    }
    catch (SAXException | IOException e) {
      throw new XMLException(e);
    }
  }

  private String getAttribute (Element e, String attributeName) {
    return e.getAttribute(attributeName);
  }

  // get value of Element's text
  private String getTextValue (Element e, String tagName) {
    NodeList nodeList = e.getElementsByTagName(tagName);
    if (nodeList.getLength() > 0) {
      return nodeList.item(0).getTextContent();
    }
    else {
      // FIXME: empty string or exception? In some cases it may be an error to not find any text
      return "";
    }
  }

  private DocumentBuilder getDocumentBuilder () throws XMLException {
    try {
      return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    catch (ParserConfigurationException e) {
      throw new XMLException(e);
    }
  }
}