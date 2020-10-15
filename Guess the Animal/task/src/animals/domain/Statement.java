package animals.domain;

public final class Statement implements Question {
    private final String data;

    public static Statement from(String input) {
        return new Statement(input.replaceFirst("(.+)\\.?", "$1"));
    }

    private Statement(String input) {
        this.data = input;
    }

    public String getFact(final Animal animal, final boolean isPositive) {
        final var fact = getFact(isPositive).replaceFirst("It(.+)", "The " + animal.getName() + "$1");
        return capitalize(fact);
    }

    public String getFact(final boolean isPositive) {
        return isPositive ? getPositiveFact() : getNegativeFact();
    }

    public String getPositiveFact() {
        return capitalize(data + ".");
    }

    public String getNegativeFact() {
        final String fact;
        if (data.startsWith("it has")) {
            fact = data.replaceFirst("it has", "It doesn't have");
        } else if (data.startsWith("it can")) {
            fact = data.replaceFirst("it can", "It can't");
        } else {
            fact = data.replaceFirst("it is", "It isn't");
        }
        return fact + ".";
    }

    @Override
    public String getQuestion() {
        final var question = data.replaceFirst("It (can|has|is) (.+)", "$1 it $2?");
        return capitalize(question);
    }

    private String capitalize(final String sentence) {
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return data;
    }
}