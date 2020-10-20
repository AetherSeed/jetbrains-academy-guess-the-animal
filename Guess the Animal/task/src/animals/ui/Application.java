package animals.ui;

import animals.domain.Animal;
import animals.domain.KnowledgeTree;
import animals.domain.TreeNode;
import animals.repository.KnowledgeBase;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;

public final class Application implements Runnable {
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
        final var services = new Services(knowledgeTree, ui);

        out.println("Welcome to the animal expert system!");

        new Menu("What do you want to do:")
                .add("Play the guessing game", new Game(knowledgeTree, ui))
                .add("List of all animals", services::printAnimals)
                .add("Search for an animal", services::searchAnimal)
                .add("Knowledge Tree stats", services::printStatistics)
                .run();

        repository.save(knowledgeTree);
        ui.sayGoodbye();
        LOGGER.log(Level.FINE, "Application finished.");
    }

}