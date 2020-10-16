package animals.repository;

import animals.domain.Animal;
import animals.domain.Statement;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnowledgeTree implements KnowledgeBase {
    private static final Logger LOGGER = Logger.getLogger(KnowledgeTree.class.getName());

    protected TreeNode root;
    protected TreeNode current;

    @Override
    public void reset() {
        current = root;
    }

    @Override
    public String getQuestion() {
        final var data = current.getData();
        final var question = isAnimal() ? Animal.from(data) : Statement.from(data);
        return question.getQuestion();
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(root);
    }

    @Override
    public boolean isAnimal() {
        return current.isLeaf();
    }

    @Override
    public boolean isStatement() {
        return !isAnimal();
    }

    @Override
    public void setRoot(TreeNode root) {
        this.root = root;
        this.current = root;
    }

    @Override
    public void next(boolean direction) {
        current = direction ? current.getYes() : current.getNo();
    }

    @Override
    public String getData() {
        return current.getData();
    }

    @Override
    public void addAnimal(final Animal animal, final Statement statement, final boolean isRight) {
        LOGGER.log(Level.FINER, "...entering method addAnimal(...)");
        final var newAnimal = new TreeNode(animal);
        final var oldAnimal = new TreeNode(current.getData());
        current.setData(statement);
        current.setYes(isRight ? newAnimal : oldAnimal);
        current.setNo(isRight ? oldAnimal : newAnimal);
        LOGGER.log(Level.FINER, "...added {0}, '{1}' - {2}", new Object[]{animal, statement, isRight});
    }
}