package animals;

import animals.repository.KnowledgeBase;
import animals.repository.TreeNode;

import java.util.logging.Logger;

public class Application implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    private final KnowledgeBase knowledgeBase;

    Application(final KnowledgeBase base) {
        knowledgeBase = base;
    }

    public void run() {
        final var ui = new UI();
        ui.sayHello();
        knowledgeBase.load();

        if (knowledgeBase.isEmpty()) {
            knowledgeBase.setRoot(new TreeNode(ui.askFavoriteAnimal()));
        }

        new Game(knowledgeBase, ui).run();
        knowledgeBase.save();
        ui.sayGoodbye();
    }

}