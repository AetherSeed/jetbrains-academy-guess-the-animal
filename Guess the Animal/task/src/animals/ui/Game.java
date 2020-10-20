package animals.ui;

import animals.domain.Animal;
import animals.domain.KnowledgeTree;

public final class Game implements Runnable {
    private final KnowledgeTree db;
    private final UI ui;

    public Game(KnowledgeTree db, UI ui) {
        this.db = db;
        this.ui = ui;

    }

    public void run() {
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
                final var guessedAnimal = Animal.from(db.getData());

                System.out.printf("Specify a fact that distinguishes %s from %s:%n", animal, db.getData());
                final var statement = ui.getStatement();
                System.out.println("Is the statement correct for the " + animal.getName() + "?");
                final var isCorrect = ui.askYesNo();
                db.addAnimal(animal, statement, isCorrect);

                System.out.println("I learned the following facts about animals:");
                System.out.println(" - " + statement.getFact(guessedAnimal, !isCorrect));
                System.out.println(" - " + statement.getFact(animal, isCorrect));
                ui.sayLearnedMuch();
            }
            db.reset();
            ui.askNewGame();
        } while (ui.askYesNo());

        ui.sayGoodbye();
    }

}