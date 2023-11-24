package Writers;

import Model.Manufacturer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class XmlWriter {
    private final ArrayList<Manufacturer> MANUFACTURERS;

    public XmlWriter(ArrayList<Manufacturer> manufacturers) {
        this.MANUFACTURERS = manufacturers;
    }
    public void write(String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Создание корневого элемента
            Document doc = docBuilder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("manufacturers");
            doc.appendChild(rootElement);

            for (Manufacturer manufacturer : MANUFACTURERS) {
                Element architectureElement = doc.createElement("architecture");
                architectureElement.setAttribute("name", manufacturer.getARCHITECTURE());
                rootElement.appendChild(architectureElement);

                Element manufacturerElement = doc.createElement("manufacturer");
                architectureElement.appendChild(manufacturerElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(manufacturer.getNAME()));
                manufacturerElement.appendChild(nameElement);

                Element processorsElement = doc.createElement("processors");
                manufacturerElement.appendChild(processorsElement);

                Map<String, Integer> cpus = manufacturer.getCPUs();
                for (Map.Entry<String, Integer> entry : cpus.entrySet()) {
                    Element processorElement = doc.createElement("processor");
                    processorsElement.appendChild(processorElement);

                    Element coresElement = doc.createElement("cores");
                    coresElement.appendChild(doc.createTextNode(String.valueOf(entry.getValue())));
                    processorElement.appendChild(coresElement);

                    Element cpuNameElement = doc.createElement("name");
                    cpuNameElement.appendChild(doc.createTextNode(entry.getKey()));
                    processorElement.appendChild(cpuNameElement);
                }
            }

            // Запись содержимого в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(filePath));
            transformer.transform(source, result);

            System.out.println("Данные успешно записаны в XML-файл: " + filePath);
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
