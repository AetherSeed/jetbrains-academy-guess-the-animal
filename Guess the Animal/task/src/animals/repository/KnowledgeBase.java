package animals.repository;

import animals.domain.Animal;
import animals.domain.Question;
import animals.domain.Statement;

public final class KnowledgeBase {
    private final Node root;
    private Node current;

    public KnowledgeBase(Node root) {
        this.root = root;
        this.current = root;
    }

    public void reset() {
        current = root;
    }

    public String getQuestion() {
        return current.getData().getQuestion();
    }

    public boolean isAnimal() {
        return current.isLeaf();
    }

    public boolean isStatement() {
        return !current.isLeaf();
    }

    public void next(boolean direction) {
        current = direction ? current.getYes() : current.getNo();
    }

    public Question getData() {
        return current.getData();
    }

    public void addAnimal(final Animal animal, final Statement statement, final boolean isRight) {
        Node newAnimal = new Node(animal);
        Node oldAnimal = new Node(current.getData());
        current.setData(statement);
        current.setYes(isRight ? newAnimal : oldAnimal);
        current.setYes(isRight ? oldAnimal : newAnimal);
    }

}