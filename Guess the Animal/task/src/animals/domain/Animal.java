package animals.domain;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public final class Animal implements Question {
    private static final Function<String, Animal> toAnimal =
            (Function<String, Animal>) domain.getObject("toAnimal");
    private static final UnaryOperator<String> getAnimalName =
            (UnaryOperator<String>) domain.getObject("getAnimalName");

    private final String data;

    public Animal(String data) {
        this.data = data;
    }

    public static Animal from(String input) {
        return toAnimal.apply(input);

    }

    public String getName() {
        return getAnimalName.apply(data);
    }

    @Override
    public String getQuestion() {
        return "Is it " + data + "?";
    }

    @Override
    public String toString() {
        return data;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return data.equals(animal.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}