import java.util.*;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var box1 = scanner.tokens().limit(3).mapToInt(Integer::parseInt).sorted().toArray();
        final var box2 = scanner.tokens().limit(3).mapToInt(Integer::parseInt).sorted().toArray();

        if (box1[0] < box2[0] && box1[1] < box2[1] && box1[2] < box2[2]) {
            System.out.println("Box 1 < Box 2");
        } else if (box1[0] > box2[0] && box1[1] > box2[1] && box1[2] > box2[2]) {
            System.out.println("Box 1 > Box 2");
        } else {
            System.out.println("Incompatible");
        }
    }
}