package animals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UI {
    private static final Pattern POSITIVE = Pattern.compile(
            "(y|yes|yeah|yep|sure|right|affirmative|correct|indeed|you bet|exactly|you said it)[.!]?");
    private static final Pattern NEGATIVE = Pattern.compile(
            "(n|no( way)?|nah|nope|negative|i don't think so|yeah no)[.!]?");
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    void sayHello() {
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

    void sayGoodbye() {
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

    public String readLine() {
        return scanner.nextLine().trim().toLowerCase();
    }

    private String pickMessage(final String[] messages) {
        return messages[random.nextInt(messages.length)];
    }
}
