package animals.domain;

import java.util.Map;
import java.util.NoSuchElementException;

public final class Statement implements Question {
    private static final Map<String, String> RULES_QUESTION = Map.of(
            "it can (.*)", "Can it $1?",
            "it has (.*)", "Does it have $1?",
            "it is (.*)", "Is it $1?"
    );
    private static final Map<String, String> RULES_NEGATIVE = Map.of(
            "it can (.*)", "It can't $1.",
            "it has (.*)", "It doesn't have $1.",
            "it is (.*)", "It isn't $1."
    );
    private final String data;

    private Statement(String input) {
        this.data = input;
    }

    public static Statement from(Object input) {
        return new Statement(input.toString().toLowerCase().trim().replaceFirst("(.+)\\.?", "$1"));
    }

    public String getFact(final Animal animal, final boolean isPositive) {
        return getFact(isPositive).replaceFirst("It(.+)", "The " + animal.getName() + "$1");
    }

    public String getFact(final boolean isPositive) {
        return isPositive ? getPositiveFact() : getNegativeFact();
    }

    public String getPositiveFact() {
        return capitalize(data + ".");
    }

    public String getNegativeFact() {
        return generateSentence(RULES_NEGATIVE);
    }

    @Override
    public String getQuestion() {
        return generateSentence(RULES_QUESTION);
    }

    private String generateSentence(Map<String, String> rules) {
        final var rule = rules.entrySet().stream()
                .filter(e -> data.matches(e.getKey()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't generate question from statement: " + data));
        return capitalize(data.replaceFirst(rule.getKey(), rule.getValue()));
    }

    private String capitalize(final String sentence) {
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return data;
    }
}