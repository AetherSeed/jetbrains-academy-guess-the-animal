import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        int nodesCount = scanner.nextInt();
        Node root = null;
        int maxDepth = 0;

        while (nodesCount-- > 0) {
            Node.depth = -1;
            root = insert(root, scanner.nextInt());
            if (Node.depth > maxDepth) {
                maxDepth = Node.depth;
            }
        }
        System.out.println(maxDepth);
    }

    static Node insert(Node node, int key) {
        Node.depth++;
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }
        return node;
    }

}

class Node {
    static int depth;
    int key;
    Node left;
    Node right;

    Node(int key) {
        this.key = key;
    }
}
