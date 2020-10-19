package animals.ui;

import animals.domain.Animal;
import animals.domain.KnowledgeTree;
import animals.domain.TreeNode;
import animals.repository.KnowledgeBase;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;

public class Application implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    private static final UI ui = new UI();
    private final KnowledgeBase repository;
    private final KnowledgeTree knowledgeTree;

    public Application(final KnowledgeBase repository) {
        this.repository = repository;
        this.knowledgeTree = repository.load();
    }

    public void run() {
        LOGGER.log(Level.FINE, "Application started.");
        ui.sayHello();

        if (knowledgeTree.isEmpty()) {
            knowledgeTree.setRoot(new TreeNode(ui.askFavoriteAnimal()));
        }

        menu();

        repository.save(knowledgeTree);
        ui.sayGoodbye();
        LOGGER.log(Level.FINE, "Application finished.");
    }

    private void menu() {
        out.println("Welcome to the animal expert system!");

        new Menu("What do you want to do:")
                .add("Play the guessing game", new Game(knowledgeTree, ui))
                .add("List of all animals", this::printAnimals)
                .add("Search for an animal", this::searchAnimal)
                .add("Knowledge Tree stats", this::printStatistics)
                .run();
    }

    private void printAnimals() {
        out.println("Here are the animals I know:");
        knowledgeTree.getAnimals().keySet().stream()
                .map(Animal::getName)
                .sorted()
                .map(name -> " - " + name)
                .forEach(out::println);
    }

    private void printStatistics() {
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

    private void searchAnimal() {
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