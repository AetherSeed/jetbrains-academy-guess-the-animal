package animals.ui;

import animals.domain.KnowledgeTree;
import animals.domain.LanguageRules;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Game implements Runnable {
    private static final Logger LOG = Logger.getLogger(Game.class.getName());
    private static final UI ui = new UI("game");
//    private static final Services service = new Services();

    private final KnowledgeTree db;

    public Game(KnowledgeTree db) {
        this.db = db;
    }

    public void run() {
        do {
            ui.println("think");
            ui.println("enter");
            ui.pause();

            while (db.isStatement()) {
                ui.println(db.getQuestion());
                db.next(ui.askYesNo());
            }

            ui.println(db.getQuestion());

            if (!ui.askYesNo()) {
                giveUp();
            }

            db.reset();
            ui.print("thanks");
            ui.println("again");

        } while (ui.askYesNo());
    }

    private void giveUp() {
        ui.println("give.up");
        final var animal = LanguageRules.ANIMAL.apply(ui.readLine());
        final var guessedAnimal = db.getData();
        ui.println("specify.fact", animal, guessedAnimal);
        final var statement = getStatement().toString();

        ui.println("is.correct", LanguageRules.ANIMAL_NAME.apply(animal));
        final var isCorrect = ui.askYesNo();
        db.addAnimal(animal, statement, isCorrect);
        ui.println("learned");
        ui.println("print.fact", LanguageRules.FACT_GENERATOR.apply(!isCorrect).apply(statement, guessedAnimal));
        ui.println("print.fact", LanguageRules.FACT_GENERATOR.apply(isCorrect).apply(statement, animal));
        ui.print("nice");
        ui.println("know.more");
    }

    public String getStatement() {
        while (true) {
            ui.println("statement.format");
            final var input = ui.readLine();
            if (LanguageRules.IS_CORRECT_STATEMENT.test(input)) {
                return LanguageRules.STATEMENT.apply(input);
            }
            LOG.log(Level.INFO, "Statement is not correct: {0}", input);
            ui.println("statement.example");
        }
    }
}