package animals.repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnowledgeTreeJackson extends KnowledgeTree {
    private static final Logger LOGGER = Logger.getLogger(KnowledgeTreeJackson.class.getName());

    private final ObjectMapper objectMapper;
    private final String fileName;

    KnowledgeTreeJackson(ObjectMapper mapper, String fileName) {
        objectMapper = mapper;
        this.fileName = fileName;
    }

    public boolean load() {
        LOGGER.log(Level.CONFIG, "Loading Knowledge base: {0}", fileName);
        try {
            setRoot(objectMapper.readValue(new File(fileName), TreeNode.class));
        } catch (IOException error) {
            LOGGER.log(Level.WARNING, "The knowledge Tree has not been loaded.", error);
            return false;
        }
        LOGGER.info("The Knowledge Tree has loaded successful.");
        LOGGER.log(Level.CONFIG, "Root node is '{0}'.", root.getData());
        return true;
    }

    public boolean save() {
        LOGGER.log(Level.CONFIG, "Saving Knowledge base: {0}", fileName);
        try {
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(fileName), root);
        } catch (IOException error) {
            LOGGER.log(Level.SEVERE, "Could not save the Knowledge Tree.", error);
            return false;
        }
        LOGGER.info("The Knowledge Tree has saved successful.");
        return true;
    }

}