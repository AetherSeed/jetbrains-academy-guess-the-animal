package animals.domain;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnowledgeTree {
    private static final Logger LOGGER = Logger.getLogger(KnowledgeTree.class.getName());
    private final Map<Animal, List<String>> animals = new HashMap<>();
    protected TreeNode current;
    private TreeNode root;
    private boolean isUpdated;

    public void reset() {
        current = root;
    }

    public String getQuestion() {
        final var data = current.getData();
        final var question = isAnimal() ? Animal.from(data) : Statement.from(data);
        return question.getQuestion();
    }

    public boolean isEmpty() {
        return Objects.isNull(root);
    }

    public boolean isAnimal() {
        return current.isLeaf();
    }

    public boolean isStatement() {
        return !isAnimal();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
        this.current = root;
    }

    public void next(boolean direction) {
        current = direction ? current.getYes() : current.getNo();
    }

    public String getData() {
        return current == null ? "Null" : current.getData();
    }

    public void addAnimal(final Animal animal, final Statement statement, final boolean isRight) {
        LOGGER.log(Level.FINER, "...entering method addAnimal(...)");
        final var newAnimal = new TreeNode(animal);
        final var oldAnimal = new TreeNode(current.getData());
        isUpdated = false;
        current.setData(statement);
        current.setYes(isRight ? newAnimal : oldAnimal);
        current.setNo(isRight ? oldAnimal : newAnimal);
        LOGGER.log(Level.FINER, "...added {0}, '{1}' - {2}", new Object[]{animal, statement, isRight});
    }

    public Map<Animal, List<String>> getAnimals() {
        if (!isUpdated) {
            animals.clear();
            collectAnimals(root, new LinkedList<>());
            isUpdated = true;
        }
        return animals;
    }

    private void collectAnimals(final TreeNode node, final Deque<String> facts) {
        if (node.isLeaf()) {
            animals.put(Animal.from(node.getData()), List.copyOf(facts));
            return;
        }
        final var statement = Statement.from(node.getData());
        facts.add(statement.getPositiveFact());
        collectAnimals(node.getYes(), facts);
        facts.removeLast();
        facts.add(statement.getNegativeFact());
        collectAnimals(node.getNo(), facts);
        facts.removeLast();
    }
}