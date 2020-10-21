package animals.ui;

import animals.domain.KnowledgeTree;
import animals.domain.TreeNode;
import animals.repository.KnowledgeBase;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Application implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    private static final UI greetings = new UI("animals.localization.Greetings");
    private final KnowledgeBase repository;
    private final KnowledgeTree knowledgeTree;
    private final Services services;

    public Application(final KnowledgeBase repository) {
        this.repository = repository;
        this.knowledgeTree = repository.load();
        services = new Services(knowledgeTree, new UI("services"));
    }

    public void run() {
        LOGGER.log(Level.FINE, "Application started.");

        greetings.println("hi");
        greetings.println();

        if (knowledgeTree.isEmpty()) {
            knowledgeTree.setRoot(
                    new TreeNode(services.askFavoriteAnimal()));
        }
        greetings.println("welcome");

        new Menu("main-menu")
                .add("play", new Game(knowledgeTree))
                .add("list", services::listAnimals)
                .add("search", services::searchAnimal)
                .add("delete", services::deleteAnimal)
                .add("statistics", services::statistics)
                .add("print", services::printTree)
                .run();

        repository.save(knowledgeTree);
        greetings.println();
        greetings.println("bye");

        LOGGER.log(Level.FINE, "Application finished.");
    }

}