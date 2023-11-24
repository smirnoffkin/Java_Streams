package Model;

import java.util.Map;

public class Manufacturer {
    private final String ARCHITECTURE;
    private final String NAME;
    private final Map<String, Integer> CPUs;

    public Manufacturer(String architecture, String name, Map<String, Integer> cpus) {
        this.ARCHITECTURE = architecture;
        this.NAME = name;
        this.CPUs = cpus;
    }

    public String getARCHITECTURE() {
        return ARCHITECTURE;
    }

    public String getNAME() {
        return NAME;
    }

    public Map<String, Integer> getCPUs() {
        return CPUs;
    }
}
