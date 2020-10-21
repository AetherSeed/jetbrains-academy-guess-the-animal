package animals.localization;

import animals.domain.Animal;

import java.util.ListResourceBundle;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Domain extends ListResourceBundle {
    private static final String VOWEL_ARTICLE = "an ";
    private static final String CONSONANT_ARTICLE = "a ";
    private static final String ENGLISH_VOWELS = "aeiou";

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"toAnimal", (Function<String, Animal>) input -> {
                    final var name = input.toLowerCase().strip();
                    if (name.startsWith(VOWEL_ARTICLE) || name.startsWith(CONSONANT_ARTICLE)) {
                        return new Animal(name);
                    } else {
                        final var isStartVowel = ENGLISH_VOWELS.indexOf(name.charAt(0)) > -1;
                        final var article = isStartVowel ? VOWEL_ARTICLE : CONSONANT_ARTICLE;
                        return new Animal(article + name);
                    }
                }},

                {"getAnimalName", (UnaryOperator<String>) data ->
                        data.substring(data.startsWith(CONSONANT_ARTICLE) ? 2 : 3)},

                {"bye", new String[]{
                        "Bye!",
                        "Bye, bye!",
                        "See you later!",
                        "See you soon!",
                        "Talk to you later!",
                        "I’m off!",
                        "It was nice seeing you!",
                        "See ya!",
                        "See you later, alligator!",
                        "In a while, crocodile!",
                        "Hasta la vista, baby!",
                        "Adios, amigos!",
                        "Au revoir!",
                        "Adieu!",
                        "Have a nice one!"
                }}};
    }
}
