package animals.ui;

import animals.domain.Animal;
import animals.domain.KnowledgeTree;

import java.util.List;

import static java.lang.System.out;

public final class Services {
    private final KnowledgeTree knowledgeTree;
    private final UI ui;

    public Services(KnowledgeTree knowledgeTree, UI ui) {
        this.knowledgeTree = knowledgeTree;
        this.ui = ui;
    }

    void printAnimals() {
        out.println("Here are the animals I know:");
        knowledgeTree.getAnimals().keySet().stream()
                .map(Animal::getName)
                .sorted()
                .map(name -> " - " + name)
                .forEach(out::println);
    }

    void printStatistics() {
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
        out.println("Enter the animal: ");
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

}
