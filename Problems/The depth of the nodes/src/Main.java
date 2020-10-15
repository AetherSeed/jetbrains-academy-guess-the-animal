import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        int numberQueries = scanner.nextInt();
        Node root = null;

        while (numberQueries-- > 0) {
            final var operation = scanner.next().charAt(0);
            if (operation == '+') {
                Node.depth = -1;
                root = Node.insert(root, scanner.nextInt());
            } else {
                System.out.println(Node.depthMap.getOrDefault(scanner.nextInt(), "no"));
            }
        }
    }
}

final class Node {
    static int depth;
    static Map<Integer, String> depthMap = new HashMap<>();

    private final int key;
    private Node left;
    private Node right;

    Node(int key, int depth) {
        this.key = key;
        depthMap.put(key, String.valueOf(depth));
    }

    static Node insert(Node node, int key) {
        depth++;
        if (node == null) {
            return new Node(key, depth);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }
        return node;
    }
}
