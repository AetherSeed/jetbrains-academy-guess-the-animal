package animals.domain;

public class Statement implements Question {
    private final String statement;

    public Statement(String statement) {
        this.statement = capitalize(statement.replaceFirst("(.+)\\.?", "$1"));
    }

    public String getFact(final Animal animal, final boolean isPositive) {
        final var fact = getFact(isPositive).replaceFirst("It(.+)", "The " + animal.getName() + "$1");
        return capitalize(fact);
    }

    public String getFact(final boolean isPositive) {
        return isPositive ? getPositiveFact() : getNegativeFact();
    }

    public String getPositiveFact() {
        return statement + ".";
    }

    public String getNegativeFact() {
        final String fact;
        if (statement.startsWith("It has")) {
            fact = statement.replaceFirst("It has", "It doesn't have");
        } else if (statement.startsWith("It can")) {
            fact = statement.replaceFirst("It can", "It can't");
        } else {
            fact = statement.replaceFirst("It is", "It isn't");
        }
        return fact + ".";
    }

    public String getQuestion() {
        final var question = statement.replaceFirst("It (can|has|is) (.+)", "$1 it $2?");
        return capitalize(question);
    }

    private String capitalize(final String sentence) {
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return statement;
    }
}