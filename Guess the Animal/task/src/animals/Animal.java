package animals;

final class Animal {
    private static final String VOWEL_ARTICLE = "an ";
    private static final String CONSONANT_ARTICLE = "a ";
    private static final String ENGLISH_VOWELS = "aeiou";

    private final String data;

    private Animal(String data) {
        this.data = data;
    }

    static Animal from(String input) {
        String name = input.toLowerCase().strip();
        if (name.startsWith(VOWEL_ARTICLE) || name.startsWith(CONSONANT_ARTICLE)) {
            return new Animal(name);
        } else {
            final var isStartVowel = ENGLISH_VOWELS.indexOf(name.charAt(0)) > -1;
            final var article = isStartVowel ? VOWEL_ARTICLE : CONSONANT_ARTICLE;
            return new Animal(article + name);
        }

    }

    String getQuestion() {
        return "Is it " + data + "?";
    }

}