package animals;

import animals.domain.Animal;
import animals.repository.KnowledgeBase;
import animals.repository.Node;

public class Game {
    private final UI ui = new UI();

    public void run() {
        ui.sayHello();
        final var root = new Node(ui.askFavoriteAnimal());
        final var db = new KnowledgeBase(root);
        ui.sayLearnedMuch();

        System.out.println("Let's play a game!");

        do {
            ui.sayThinkAnimal();

            while (db.isStatement()) {
                System.out.println(db.getQuestion());
                db.next(ui.askYesNo());
            }

            System.out.println(db.getQuestion());
            final boolean isGuessedWrong = !ui.askYesNo();

            if (isGuessedWrong) {
                System.out.println("I give up. What animal do you have in mind?");

                final var animal = Animal.from(ui.readLine());
                System.out.printf("Specify a fact that distinguishes %s from %s:%n", animal, db.getData());
                final var statement = ui.getStatement();
                System.out.println("Is the statement correct for the " + animal.getName() + "?");

                db.addAnimal(animal, statement, ui.askYesNo());
                ui.sayLearnedMuch();
            }
            db.reset();
            ui.askNewGame();
        } while (ui.askYesNo());

        ui.sayGoodbye();
    }

}