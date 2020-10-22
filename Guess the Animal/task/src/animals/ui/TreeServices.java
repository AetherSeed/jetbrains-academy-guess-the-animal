package animals.ui;

import animals.domain.*;

import static java.lang.System.out;

public final class TreeServices {
    private static final UI ui = new UI("tree-services");
    private final KnowledgeTree knowledgeTree;

    TreeServices(KnowledgeTree knowledgeTree) {
        this.knowledgeTree = knowledgeTree;
    }

    void listAnimals() {
        ui.println("list.animals");
        knowledgeTree.getAnimals().keySet().stream()
                .map(LanguageRules.ANIMAL_NAME::apply)
                .sorted()
                .forEach(name -> ui.println("list.print", name));
    }

    void statistics() {
        final var stats = knowledgeTree.getStatistics();

        ui.printf("title");
        ui.printf("root", knowledgeTree.getRoot().getData());
        ui.printf("nodes", stats.getCount() * 2 - 1);
        ui.printf("animals", stats.getCount());
        ui.printf("statements", stats.getCount() - 1);
        ui.printf("height", stats.getMax());
        ui.printf("minimum", stats.getMin());
        ui.printf("average", stats.getAverage());
    }

    void searchAnimal() {
        ui.println("search.animal");
        final var animal = Services.askAnimal();
        final var animals = knowledgeTree.getAnimals();
        if (animals.containsKey(animal)) {
            ui.println("search.facts", LanguageRules.ANIMAL_NAME.apply(animal));
            final var facts = animals.get(animal);
            facts.forEach(fact -> ui.println("search.print", fact));
        } else {
            ui.println("search.not_found");
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


}
