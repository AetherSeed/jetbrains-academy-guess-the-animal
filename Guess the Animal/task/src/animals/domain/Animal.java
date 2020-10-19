package animals.domain;

import java.util.Objects;

public final class Animal implements Question {
    private static final String VOWEL_ARTICLE = "an ";
    private static final String CONSONANT_ARTICLE = "a ";
    private static final String ENGLISH_VOWELS = "aeiou";

    private final String data;

    private Animal(String data) {
        this.data = data;
    }

    public static Animal from(String input) {
        String name = input.toLowerCase().strip();
        if (name.startsWith(VOWEL_ARTICLE) || name.startsWith(CONSONANT_ARTICLE)) {
            return new Animal(name);
        } else {
            final var isStartVowel = ENGLISH_VOWELS.indexOf(name.charAt(0)) > -1;
            final var article = isStartVowel ? VOWEL_ARTICLE : CONSONANT_ARTICLE;
            return new Animal(article + name);
        }

    }

    public String getName() {
        return data.substring(data.startsWith(CONSONANT_ARTICLE) ? 2 : 3);
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