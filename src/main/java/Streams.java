import lombok.NonNull;
import lombok.experimental.UtilityClass;
import Model.Manufacturer;

import java.util.*;
import java.util.stream.Stream;


public class Streams {
    public static void outputArchitectures(ArrayList<Manufacturer> manufacturerArrayList) {
        manufacturerArrayList.stream().map(Manufacturer::getARCHITECTURE).forEach(System.out::println);
    }
}