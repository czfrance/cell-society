package cellsociety.xml;

import cellsociety.models.Grid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
  public void save (Map<String, String> dataValues, List<String> dataFields, Grid grid, Map<String, Optional> saveInfo)
      throws ParserConfigurationException, TransformerException {

    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    // root elements
    Document doc = docBuilder.newDocument();
    Element root = doc.createElement("data");
    doc.appendChild(root);
    root.setAttribute("simulation", dataValues.get("simulationType"));

    for (String field : dataFields) {
      Element tag = doc.createElement(field);
      if (field.equals("config")) {
        String gridConfig = getGridInfo(grid);
        tag.setTextContent(gridConfig);
      }
      else if (dataValues.get(field).equals("")) {
        continue;
      }
      else if (saveInfo.containsKey(field) && (saveInfo.get(field).isPresent())) {
        tag.setTextContent(saveInfo.get(field).get().toString());
      }
      else {
        tag.setTextContent(dataValues.get(field));
      }
      root.appendChild(tag);
    }

    // write dom document to a file
    try (FileOutputStream output =
        new FileOutputStream("data/b.xml")) {
      writeXml(doc, output);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private void writeToDocument(Document doc, String title) {
    try (FileOutputStream output =
        new FileOutputStream("data/b.xml")) {
      writeXml(doc, output);
    } catch (IOException | TransformerException e) {
      e.printStackTrace();
    }
  }

  private String getGridInfo(Grid grid) {
    String gridConfig = "";
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.getRow(i).size(); j++) {
        int state = grid.getRow(i).get(j).getCurrentState();
        gridConfig += Integer.toString(state);
      }
      gridConfig += ".\n";
    }
    return gridConfig;
  }

  private static void writeXml(Document doc,
      OutputStream output)
      throws TransformerException {

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(output);

    transformer.transform(source, result);

  }
}
