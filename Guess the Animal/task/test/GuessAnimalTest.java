import animals.Main;
import org.hyperskill.hstest.dynamic.input.DynamicTestingMethod;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;

import java.io.IOException;

public class GuessAnimalTest extends StageTest<String> {
    public GuessAnimalTest() {
        super(Main.class);
    }

    @DynamicTestingMethod
    CheckResult testFileFormats() throws IOException {
        return new Scenario("file-formats").check();
    }

    @DynamicTestingMethod
    CheckResult testMenu() throws IOException {
        return new Scenario("menu").check();
    }

}