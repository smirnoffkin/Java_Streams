package Writers;

import Model.Manufacturer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonWriter {
    private final ArrayList<Manufacturer> MANUFACTURERS;

    public JsonWriter(ArrayList<Manufacturer> manufacturers) {
        this.MANUFACTURERS = manufacturers;
    }
    public void write(String path) {
        JSONObject jsonInterpretation = getConvertedFile();

        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonInterpretation.toString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject getConvertedFile() {
        HashMap<String, ArrayList<JSONObject>> architectureAndManufacturers = new HashMap<>();
        for (int i = 0; i < MANUFACTURERS.size(); i++) {
            Manufacturer manufacturer = MANUFACTURERS.get(i);

            JSONArray allProcessorsOfManufacturer = getAllProcessorsOfManufacturer(manufacturer);

            JSONObject manufacturerTag = new JSONObject();
            manufacturerTag.put("name", manufacturer.getNAME());

            manufacturerTag.put("processor", allProcessorsOfManufacturer);


            String architecture = manufacturer.getARCHITECTURE();
            if (architectureAndManufacturers.containsKey(architecture)) {
                ArrayList<JSONObject> listOfManufacturer = architectureAndManufacturers.get(architecture);
                listOfManufacturer.add(manufacturerTag);
                architectureAndManufacturers.put(architecture, listOfManufacturer);
            } else {
                ArrayList<JSONObject> newManufacturer = new ArrayList<>();
                newManufacturer.add(manufacturerTag);
                architectureAndManufacturers.put(architecture, newManufacturer);
            }
        }

        JSONObject architectureList = new JSONObject();
        for (String architecture : architectureAndManufacturers.keySet())
            architectureList.put(architecture, architectureAndManufacturers.get(architecture));

        JSONObject architectureTag = new JSONObject();
        architectureTag.put("architecture", architectureList);


        JSONObject manufacturersTag = new JSONObject();
        manufacturersTag.put("manufacturers", architectureTag);

        return manufacturersTag;
    }

    private JSONArray getAllProcessorsOfManufacturer(Manufacturer manufacturer) {
        JSONArray allProcessorsOfManufacturer = new JSONArray();
        Map<String, Integer> manufacturerProcessors = manufacturer.getCPUs();
        for (String nameProcessor : manufacturerProcessors.keySet()) {
            JSONObject processorTag = new JSONObject();

            processorTag.put("name", nameProcessor);

            processorTag.put("core", manufacturerProcessors.get(nameProcessor));

            allProcessorsOfManufacturer.put(processorTag);
        }

        return allProcessorsOfManufacturer;
    }

}
