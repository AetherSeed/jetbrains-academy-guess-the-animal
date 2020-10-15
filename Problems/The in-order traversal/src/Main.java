import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        int numberQueries = scanner.nextInt();
        while (numberQueries-- > 0) {
            final var operation = scanner.next().charAt(0);
            switch (operation) {
                case '+':
                    break;
                case '-':
                    break;
                case '!':
                    break;
                default:
                    System.out.println("Unrecognized operation");
            }
        }
    }
}

class Node {
    int key;
    int value;
    Node left;
    Node right;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
