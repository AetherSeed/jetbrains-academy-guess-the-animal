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
        System.out.println("The sentence should be of the format: 'It can/has/is ...'");

        final var answer = ui.askYesNo();
        System.out.print("You answered: ");
        System.out.println(answer ? "Yes" : "No");

        ui.sayGoodbye();
    }
}