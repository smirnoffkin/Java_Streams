import lombok.NonNull;
import lombok.experimental.UtilityClass;
import Model.Manufacturer;

import java.util.*;
import java.util.stream.Stream;


public class Streams {
    public static void outputArchitectures(ArrayList<Manufacturer> manufacturerArrayList) {
        manufacturerArrayList.stream().map(Manufacturer::getARCHITECTURE).forEach(System.out::println);
    }

    public static void outputManufacturerWithLess4Symbols(ArrayList<Manufacturer> manufacturerArrayList) {
        Stream<Manufacturer> list = manufacturerArrayList.stream().filter(i -> i.getNAME()
                .replaceAll(" ", "")
                .length() < 4);
        List<Manufacturer> manufacturerList = list.toList();
        for (Manufacturer manufacturer : manufacturerList) {
            System.out.println(manufacturer.getNAME());
        }
    }

    public static void outputSortedByName(ArrayList<Manufacturer> manufacturerArrayList) {
        Stream<Manufacturer> list = manufacturerArrayList.stream().sorted(Comparator.comparing(Manufacturer::getNAME));
        List<Manufacturer> manufacturerList = list.toList();
        for (Manufacturer manufacturer : manufacturerList) {
            System.out.println(manufacturer.getNAME());
        }
    }
}