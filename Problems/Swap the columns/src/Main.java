import java.util.Scanner;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;

import static java.util.stream.IntStream.range;

/**
 * This solution demonstrates how you can solve the problem using Stream API.
 * I use one-dimensional array to represent two-dimensional matrix
 */
class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var rows = scanner.nextInt();
        final var cols = scanner.nextInt();

        final var matrix = scanner.tokens()
                .mapToInt(Integer::parseInt)
                .limit(rows * cols)
                .toArray();

        final var firstColumn = scanner.nextInt();
        final var secondColumn = scanner.nextInt();

        final IntUnaryOperator startingIndex = row -> row * cols;

        final IntConsumer swapColumns = index -> {
            final var temp = matrix[index + firstColumn];
            matrix[index + firstColumn] = matrix[index + secondColumn];
            matrix[index + secondColumn] = temp;
        };
        final IntConsumer printElement = index ->
            System.out.printf(index % cols == cols - 1 ? "%d%n" : "%d ", matrix[index]);

        range(0, rows).map(startingIndex).forEach(swapColumns);
        range(0, matrix.length).forEach(printElement);
    }
}