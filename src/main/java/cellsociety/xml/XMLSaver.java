package cellsociety.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

public class XMLSaver {
  public void save (String simType)
      throws ParserConfigurationException, TransformerException {

    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    // root elements
    Document doc = docBuilder.newDocument();
    Element rootElement = doc.createElement("company");
    doc.appendChild(rootElement);

    doc.createElement("staff");
    rootElement.appendChild(doc.createElement("staff"));

    //...create XML elements, and others...

    // write dom document to a file
    try (FileOutputStream output =
        new FileOutputStream("data/idk.xml")) {
      writeXml(doc, output);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static void writeXml(Document doc,
      OutputStream output)
      throws TransformerException {

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(output);

    transformer.transform(source, result);

  }
}
