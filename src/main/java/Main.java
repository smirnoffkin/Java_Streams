import Model.Manufacturer;
import Readers.JsonReader;
import Readers.XmlReader;
import Writers.JsonWriter;
import Writers.XmlWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "Manufacturer.xml";
        XmlReader reader = new XmlReader(filePath);
        ArrayList<Manufacturer> manufacturerArrayList = reader.read();

        System.out.println("Вывод архитектур производителей");
        Streams.outputArchitectures(manufacturerArrayList);
        System.out.println();

    }

    private static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1);
        }
        return "";
    }

    private static ArrayList<Manufacturer> readXml(String filePath) {
        XmlReader reader = new XmlReader(filePath);
        return reader.read();
    }

    private static void writeJson(String filePath, ArrayList<Manufacturer> manufacturers) {
        JsonWriter writer = new JsonWriter(manufacturers);
        writer.write(filePath);
    }

    private static ArrayList<Manufacturer> readJson(String filePath) throws IOException {
        JsonReader reader = new JsonReader(filePath);
        return reader.read();
    }

    private static void writeXml(String filePath, ArrayList<Manufacturer> manufacturers) {
        XmlWriter writer = new XmlWriter(manufacturers);
        writer.write(filePath);
    }

}
