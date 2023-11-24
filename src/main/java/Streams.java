import lombok.NonNull;
import lombok.experimental.UtilityClass;
import Model.Manufacturer;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
@NonNull
public class Streams {
    public void writeNames(ArrayList<Manufacturer> manufacturers) {
        manufacturers
                .forEach(s -> System.out.println(s.getNAME()));
    }
}