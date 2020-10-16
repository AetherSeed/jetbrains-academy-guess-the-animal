package animals.repository;

import animals.domain.Animal;
import animals.domain.Statement;

public interface KnowledgeBase {
    void reset();

    String getData();

    String getQuestion();

    boolean isEmpty();

    boolean isAnimal();

    boolean isStatement();

    void setRoot(TreeNode root);

    void next(boolean direction);

    void addAnimal(final Animal animal, final Statement statement, final boolean isRight);
}