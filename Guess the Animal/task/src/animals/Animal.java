package animals;

final class Animal {
    private final String data;

    private Animal(String data) {
        this.data = data;
    }

    public static Animal from(String input) {
        String name = input.toLowerCase().strip();
        if (name.startsWith("a ") || name.startsWith("an ")) {
            return new Animal(name);
        } else {
            final var isStartVowel = "aeiou".indexOf(name.charAt(0)) > -1;
            final var article = isStartVowel ? "an " : "a ";
            return new Animal(article + name);
        }

    }

    public String getQuestion() {
        return "Is it " + data + "?";
    }

}