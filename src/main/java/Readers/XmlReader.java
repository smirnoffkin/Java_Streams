package Readers;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Model.Manufacturer;

public class XmlReader {
    private final String PATH;

    public XmlReader(String path) {
        this.PATH = path;
    }
    public ArrayList<Manufacturer> read() {
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();

        try {
            String xml = new String(Files.readAllBytes(Paths.get(PATH)));

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xml));
            Document document = builder.parse(inputSource);

            NodeList architectureNodes = document.getElementsByTagName("architecture");

            for (int i = 0; i < architectureNodes.getLength(); i++) {
                Element architectureElement = (Element) architectureNodes.item(i);

                String architectureName = architectureElement.getAttribute("name");


                NodeList manufacturerNodes = architectureElement.getElementsByTagName("manufacturer");

                for (int j = 0; j < manufacturerNodes.getLength(); j++) {
                    Element manufacturerElement = (Element) manufacturerNodes.item(j);
                    String manufacturerName = getTextContent(manufacturerElement.getElementsByTagName("name"));

                    NodeList processorNodes = manufacturerElement.getElementsByTagName("processor");
                    Map<String, Integer> cpus = new HashMap<>();

                    for (int k = 0; k < processorNodes.getLength(); k++) {
                        Element processorElement = (Element) processorNodes.item(k);
                        String cpuName = getTextContent(processorElement.getElementsByTagName("name"));
                        int cpuCores = Integer.parseInt(getTextContent(processorElement.getElementsByTagName("cores")));

                        cpus.put(cpuName, cpuCores);
                    }

                    manufacturers.add(new Manufacturer(architectureName, manufacturerName, cpus));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return manufacturers;
    }

    private String getTextContent(NodeList nodeList) {
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return "";
    }
}
