import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var tree = new TreeMap<Integer, String>();

        final Map<String, Runnable> operations = Map.of(
                "+", () -> tree.put(scanner.nextInt(), scanner.next()),
                "-", () -> tree.remove(scanner.nextInt()),
                "!", () -> tree.put(scanner.nextInt(), scanner.next()));

        var numberQueries = scanner.nextInt();
        while (numberQueries-- > 0) {
            operations.get(scanner.next()).run();
        }
        System.out.println(String.join(" ", tree.values()));
    }
}
