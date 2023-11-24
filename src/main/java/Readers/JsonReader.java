package Readers;

import Model.Manufacturer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JsonReader {
    private final String PATH;

    public JsonReader(String path) {
        this.PATH = path;
    }

    public ArrayList<Manufacturer> read() throws IOException {
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();

        String json = Files.readString(Paths.get(PATH));
        JSONObject file = new JSONObject(json);

        JSONObject manufacturersTag = file.getJSONObject("manufacturers");
        JSONArray architectureTag = manufacturersTag.getJSONArray("architecture");

        for (int i = 0; i < architectureTag.length(); i++) {
            JSONObject architecture = architectureTag.getJSONObject(i);

            String architectureName = architecture.getString("name");
            JSONArray informationAboutManufacturer = architecture.getJSONArray("manufacturer");
            for (int j = 0; j < informationAboutManufacturer.length(); j++) {
                JSONObject manufacturer = informationAboutManufacturer.getJSONObject(j);

                String manufacturerName = manufacturer.getString("name");
                LinkedHashMap<String, Integer> processors = allManufacturerProcessors(manufacturer);

                manufacturers.add(new Manufacturer(architectureName, manufacturerName, processors));
            }
        }

        return manufacturers;
    }

    private LinkedHashMap<String, Integer> allManufacturerProcessors(JSONObject manufacturer) {
        LinkedHashMap<String, Integer> allManufacturerProcessors = new LinkedHashMap<>();
        JSONArray processors = manufacturer.getJSONArray("processor");
        for (int k = 0; k < processors.length(); k++) {
            JSONObject processor = processors.getJSONObject(k);


            String processorName = processor.getString("name");
            int cores = Integer.parseInt(processor.getString("cores"));

            allManufacturerProcessors.put(processorName, cores);
        }

        return allManufacturerProcessors;
    }
}
