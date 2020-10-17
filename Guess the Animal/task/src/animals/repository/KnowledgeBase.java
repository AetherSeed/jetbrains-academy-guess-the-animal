package animals.repository;

import animals.domain.Animal;
import animals.domain.Statement;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

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

    boolean load();

    boolean save();

    String FILENAME = "animals";

    enum Type {
        XML(new XmlMapper()),
        JSON(new JsonMapper()),
        YAML(new YAMLMapper()),
        IN_MEMORY(new KnowledgeTree() {
            @Override
            public boolean load() {
                return false;
            }

            @Override
            public boolean save() {
                return false;
            }
        });

        private final KnowledgeBase repository;

        Type(KnowledgeBase repository) {
            this.repository = repository;
        }

        Type(ObjectMapper objectMapper) {
            this.repository = new KnowledgeTreeJackson(
                    objectMapper, FILENAME + "." + this.name().toLowerCase()
            );
        }

        public KnowledgeBase getInstance() {
            return repository;
        }
    }

}