package animals.ui;

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
