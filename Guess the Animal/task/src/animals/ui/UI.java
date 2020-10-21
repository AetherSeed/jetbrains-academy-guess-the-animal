package animals.ui;

import animals.domain.Animal;
import animals.domain.Statement;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.System.out;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public final class UI {
    private static final Pattern POSITIVE = Pattern.compile(
            "(y|yes|yeah|yep|sure|right|affirmative|correct|indeed|you bet|exactly|you said it)[.!]?");
    private static final Pattern NEGATIVE = Pattern.compile(
            "(n|no( way)?|nah|nope|negative|i don't think so|yeah no)[.!]?");

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private final ResourceBundle resourceBundle;

    public UI(final String messages) {
        this.resourceBundle = ResourceBundle.getBundle(messages);
    }

    public void println(String key, Object... args) {
        out.println(MessageFormat.format(getText(key), args));
    }

    public void print(String key, Object... args) {
        out.print(MessageFormat.format(getText(key), args));
    }

    public void printf(String key, Object... args) {
        out.printf(getText(key), args);
    }

    public void println() {
        out.println();
    }

    private String getText(String key) {
        if (isNull(resourceBundle) || !resourceBundle.containsKey(key)) {
            return key;
        }
        if (resourceBundle.getObject(key) instanceof String[]) {
            return pickMessage(resourceBundle.getStringArray(key));
        }
        final var message = resourceBundle.getString(key);
        if (message.indexOf('\f') > 0) {
            return pickMessage(message.split("\f"));
        }
        return message;
    }

    public void sayHello() {
        final var messages = new ArrayList<String>();
        final var time = LocalTime.now();
        if (time.isAfter(LocalTime.NOON) && time.isBefore(LocalTime.of(18, 0))) {
            messages.add("Good afternoon!");
        } else if (time.isAfter(LocalTime.of(5, 0)) && time.isBefore(LocalTime.NOON)) {
            messages.add("Good morning!");
        } else if (time.isAfter(LocalTime.of(18, 0))) {
            messages.add("Good evening!");
        } else if (time.isAfter(LocalTime.MIDNIGHT) && time.isBefore(LocalTime.of(4, 0))) {
            messages.add("Hi Night Owl!");
        } else if (time.isAfter(LocalTime.of(4, 0))) {
            messages.add("Hi Early Bird!");
        }
        messages.add("Hello!");
        System.out.println(pickMessage(messages.toArray(String[]::new)));
        System.out.println();
    }

    public void sayGoodbye() {
        System.out.println();
        System.out.println(pickMessage(new String[]{
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
                "Have a nice one!"
        }));
    }

    public boolean askYesNo() {
        while (true) {
            final var respond = readLine();
            if (POSITIVE.matcher(respond).matches()) {
                return true;
            }
            if (NEGATIVE.matcher(respond).matches()) {
                return false;
            }
            System.out.println(pickMessage(new String[]{
                    "Come on, yes or no?",
                    "Please enter yes or no.",
                    "Funny, I still don't understand, is it yes or no?",
                    "Sorry, it must be yes or no.",
                    "Let's try again: yes or no?",
                    "I'm not sure I caught you: was it yes or no?",
                    "Oh, it's too complicated for me: just say me yes or no.",
                    "I'm filling a bit intrigued: just say yes or no.",
                    "I am a bit confused, give me a simple answer: yes or no",
                    "Oh, no, don't try to confuse me: say yes or no.",
                    "Could you please simply say yes or no?",
                    "Sorry, may l ask you again: was it yes or no?"
            }));
        }
    }

    public Statement getStatement() {
        while (true) {
            System.out.println("The sentence should be of the format: 'It can/has/is ...'");
            final var input = readLine();
            if (input.matches("it (can|has|is) .+")) {
                return Statement.from(input);
            }
            System.out.println("The examples of a statement: \n" +
                    "It can fly \n" +
                    "It has horns \n" +
                    "It is a mammal ");
        }
    }

    public boolean isCorrect(String key, String data) {
        return data.matches(resourceBundle.getString(key));
    }

    public String readLine() {
        return scanner.nextLine().trim().toLowerCase();
    }

    private String pickMessage(final String[] messages) {
        return messages[random.nextInt(messages.length)];
    }


    public void sayLearnedMuch() {
        System.out.print(pickMessage(new String[]{
                "Nice!",
                "Great!",
                "Wonderful!"
        }));
        System.out.println(" I've learned so much about animals!");
    }

    public Animal askFavoriteAnimal() {
        System.out.println("I want to learn about animals.");
        System.out.println("Which animal do you like most?");
        return Animal.from(readLine());
    }

    public void sayThinkAnimal() {
        System.out.println("You think of an animal, and I guess it.");
        System.out.println("Press enter when you're ready.");
        scanner.nextLine();
    }

    public void askNewGame() {
        System.out.println(pickMessage(new String[]{
                "That was funny! Want to try again?",
                "Do you like to play again?",
                "It was nice to play with you! Do you want to repeat?",
                "It was a pleasure for me to know you better! Want to play again?",
                "Thank you for playing! One more game?",
                "Thank you! I had too much fun! Do you want to play again?"
        }));
    }


    public void pause() {
        scanner.nextLine();
    }

}
