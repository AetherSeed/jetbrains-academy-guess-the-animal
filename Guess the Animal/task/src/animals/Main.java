package animals;

import animals.repository.KnowledgeTreeJackson;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.log(Level.CONFIG, "Program started with arguments: {0}", Arrays.toString(args));

        if (args.length > 1 && args[0].equals("-type")) {
            System.setProperty("animals.database.type", args[1]);
            LOGGER.log(Level.CONFIG, "animals.database.type = {0}", args[1]);
        }
        new Application(
                new KnowledgeTreeJackson(System.getProperty("animals.database.type", "json"))
        ).run();
    }
}