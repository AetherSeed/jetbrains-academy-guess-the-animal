package animals.ui;

import animals.domain.Animal;
import animals.domain.KnowledgeTree;
import animals.domain.Statement;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Game implements Runnable {
    private static final Logger LOG = Logger.getLogger(Game.class.getName());
    private static final UI ui = new UI("game");

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
        final var animal = Animal.from(ui.readLine());
        final var guessedAnimal = Animal.from(db.getData());
        ui.println("specify.fact", animal, guessedAnimal);
        final var statement = getStatement();

        ui.println("is.correct", animal.getName());
        final var isCorrect = ui.askYesNo();
        db.addAnimal(animal, statement, isCorrect);
        ui.println("learned");
        ui.println("print.fact", statement.getFact(guessedAnimal, !isCorrect));
        ui.println("print.fact", statement.getFact(animal, isCorrect));
        ui.print("nice");
        ui.println("know.more");
    }

    public Statement getStatement() {
        while (true) {
            ui.println("statement.format");
            final var statement = ui.readLine();
            if (ui.isCorrect("statement.negative", statement)) {
                ui.println("statement.error");
            } else if (ui.isCorrect("statement.regex", statement)) {
                return Statement.from(statement);
            }
            LOG.log(Level.INFO, "Statement: {0}", statement);
            LOG.log(Level.INFO, "Regexp: {0}", ResourceBundle.getBundle("game").getString("statement.regex"));
            ui.println("statement.example");
        }
    }
}