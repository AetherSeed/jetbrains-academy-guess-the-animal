package animals.domain;

import java.util.ResourceBundle;

public interface Question {
    ResourceBundle domain = ResourceBundle.getBundle("animals.localization.Domain");

    String getQuestion();
}