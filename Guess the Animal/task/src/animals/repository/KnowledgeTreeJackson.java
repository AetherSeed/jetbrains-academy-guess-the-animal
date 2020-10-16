package animals.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnowledgeTreeJackson extends KnowledgeTree {
    enum DatabaseType {
        XML(new XmlMapper()),
        JSON(new JsonMapper()),
        YAML(new YAMLMapper()),
        DEFAULT(new ObjectMapper());

        ObjectMapper mapper;

        DatabaseType(ObjectMapper mapper) {
            this.mapper = mapper;
        }
    }

    private static final Logger LOGGER = Logger.getLogger(KnowledgeTreeJackson.class.getName());
    private static final String DATABASE_NAME = "animals";
    private static final Map<String, ObjectMapper> DATABASE_TYPES = Map.of(
            "json", new JsonMapper(), "xml", new XmlMapper(), "yaml", new YAMLMapper());

    private final ObjectMapper objectMapper;
    private final String fileName;

    public KnowledgeTreeJackson(final String type) {
        objectMapper = DATABASE_TYPES.getOrDefault(type, new ObjectMapper());
        fileName = DATABASE_NAME + "." + type;
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