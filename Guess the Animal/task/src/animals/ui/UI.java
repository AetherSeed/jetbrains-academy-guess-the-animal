package animals.ui;

import animals.domain.LanguageRules;

import java.text.MessageFormat;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.System.out;
import static java.util.Objects.isNull;

public final class UI {
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

    public boolean askYesNo() {
        while (true) {
            final var respond = readLine();
            if (LanguageRules.IS_POSITIVE_ANSWER.test(respond)) {
                return true;
            }
            if (LanguageRules.IS_NEGATIVE_ANSWER.test(respond)) {
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

    public void pause() {
        scanner.nextLine();
    }

}
