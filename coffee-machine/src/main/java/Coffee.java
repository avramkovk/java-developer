import java.util.Map;

class Coffee {
    private String name;
    private Map<String, Integer> components;

    Coffee(String name, Map<String, Integer> components) {
        this.name = name;
        this.components = components;
    }

    public String getName() {
        return name;
    }
    public Map<String, Integer>  getComponents() {
        return components;
    }
}
