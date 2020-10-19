package animals.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu implements Runnable {
    private static final Scanner scanner = new Scanner(System.in);
    private final List<Entry> menu = new ArrayList<>();
    private final String title;
    private boolean once;

    public Menu() {
        title = "";
    }

    public Menu(final String title) {
        this.title = String.format("%n%s%n", title);
    }

    public Menu once() {
        once = true;
        return this;
    }

    public Menu add(final String entry, final Runnable action) {
        menu.add(new Entry(String.format("%2d. %s", menu.size() + 1, entry), action));
        return this;
    }

    @Override
    public void run() {
        do {
            System.out.println(title);
            menu.forEach(System.out::println);
            System.out.println(" 0. Exit");
            try {
                final int choice = Integer.parseInt(scanner.nextLine()) - 1;
                if (choice == -1) {
                    return;
                }
                menu.get(choice).run();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Please enter the number from 0 up to " + menu.size());
            }
        } while (!once);
    }

    private static final class Entry implements Runnable {
        private final String name;
        private final Runnable action;

        Entry(final String name, final Runnable action) {
            this.name = name;
            this.action = action;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public void run() {
            action.run();
        }
    }
}