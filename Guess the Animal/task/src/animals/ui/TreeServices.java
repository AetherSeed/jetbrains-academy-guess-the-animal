package animals.ui;

import animals.domain.*;

import java.util.List;

import static java.lang.System.out;

public final class TreeServices {
    private static final UI ui = new UI("services");
    private final KnowledgeTree knowledgeTree;

    public TreeServices(KnowledgeTree knowledgeTree) {
        this.knowledgeTree = knowledgeTree;
    }

    void listAnimals() {
        out.println("Here are the animals I know:");
        knowledgeTree.getAnimals().keySet().stream()
                .map(Animal::getName)
                .sorted()
                .map(name -> " - " + name)
                .forEach(out::println);
    }

    void statistics() {
        final var stats = knowledgeTree.getAnimals().values().stream()
                .mapToInt(List::size).summaryStatistics();

        out.printf("%-30s %n%n", "The Knowledge Tree stats");
        out.printf("%-30s %s%n", "- root node", knowledgeTree.getRoot().getData());
        out.printf("%-30s %d%n", "- total number of nodes", stats.getCount() * 2 - 1);
        out.printf("%-30s %d%n", "- total number of animals", stats.getCount());
        out.printf("%-30s %d%n", "- total number of statements", stats.getCount() - 1);
        out.printf("%-30s %d%n", "- height of the tree", stats.getMax());
        out.printf("%-30s %d%n", "- minimum depth", stats.getMin());
        out.printf("%-30s %.1f%n", "- average depth", stats.getAverage());
    }

    void searchAnimal() {
        out.println("Enter an animal: ");
        final var animal = Animal.from(ui.readLine());
        final var animals = knowledgeTree.getAnimals();
        if (animals.containsKey(animal)) {
            out.println("Facts about the " + animal.getName() + ":");
            final var facts = animals.get(animal);
            facts.forEach(fact -> out.println(" - " + fact));
        } else {
            out.println("The animal is not in my knowledge tree.");
        }
    }

    void deleteAnimal() {
        if (knowledgeTree.getRoot().isLeaf()) {
            out.println("Can't delete the only animal from the root.");
            return;
        }
        out.println("Enter an animal: ");
        final var animal = Animal.from(ui.readLine());
        if (knowledgeTree.deleteAnimal(null, knowledgeTree.getRoot(), animal.toString())) {
            out.println("The " + animal.getName() + " was deleted from the knowledge base.");
        } else {
            out.println("The " + animal.getName() + " was not found in the knowledge base.");
        }
    }

    void printTree() {
        out.println("\n ┄");
        printNode(knowledgeTree.getRoot(), false, " ");
    }

    private void printNode(TreeNode node, boolean isYes, String prefix) {
        if (node.isLeaf()) {
            out.printf("%s%c %s%n", prefix, isYes ? '├' : '└', node.getData());
            return;
        }
        out.printf("%s%c %s%n", prefix, isYes ? '├' : '└', Statement.from(node.getData()).getQuestion());
        prefix += isYes ? '│' : ' ';
        printNode(node.getYes(), true, prefix);
        printNode(node.getNo(), false, prefix);
    }

    public Animal askFavoriteAnimal() {
        System.out.println("I want to learn about animals.");
        System.out.println("Which animal do you like most?");
        return Animal.from(ui.readLine());
    }

    public boolean askYesNo() {
        while (true) {
            final var respond = ui.readLine();
            if (LanguageRules.IS_POSITIVE_ANSWER.test(respond)) {
                return true;
            }
            if (LanguageRules.IS_NEGATIVE_ANSWER.test(respond)) {
                return false;
            }
            ui.println("ask.again");
        }
    }
}