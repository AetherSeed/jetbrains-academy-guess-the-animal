package animals.domain;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class NodeData {
    public static final Function<String, Animal> toAnimal;
    public static final UnaryOperator<String> getAnimalName;

    private static final ResourceBundle domain = ResourceBundle.getBundle("animals.localization.Domain");

    static {
        toAnimal = (Function<String, Animal>) domain.getObject("toAnimal");
        getAnimalName = (UnaryOperator<String>) domain.getObject("getAnimalName");
    }

    protected String data;

    public NodeData() {
    }

    public NodeData(final String data) {
        this.data = data;
    }

    public static String capitalize(final String sentence) {
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1).toLowerCase();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeData nodeData = (NodeData) o;
        return data.equals(nodeData.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
