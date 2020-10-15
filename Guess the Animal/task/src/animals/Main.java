package animals;

import animals.domain.Animal;

public class Main {
    public static void main(String[] args) {
        final UI ui = new UI();

        ui.sayHello();

        System.out.println("Enter the first animal:");
        final var firstAnimal = Animal.from(ui.readLine());
        System.out.println("Enter the second animal:");
        final var secondAnimal = Animal.from(ui.readLine());

        System.out.println("Specify a fact that distinguishes " + firstAnimal + " from " + secondAnimal + ".");

        final var statement = ui.getStatement();
        System.out.println("Is it correct for " + secondAnimal + "?");
        final var isCorrectSecond = ui.askYesNo();
        final var isCorrectFirst = !isCorrectSecond;

        System.out.println("I learned the following facts about animals:");
        System.out.println(" - " + statement.getFact(firstAnimal, isCorrectFirst));
        System.out.println(" - " + statement.getFact(secondAnimal, isCorrectSecond));
        System.out.println("I can distinguish these animals by asking the question:");
        System.out.println(" - " + statement.getQuestion());

        ui.sayGoodbye();
    }
}