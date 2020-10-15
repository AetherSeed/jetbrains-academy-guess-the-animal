package animals;

public class Main {
    public static void main(String[] args) {
        final UI ui = new UI();

        ui.sayHello();

        System.out.println("Enter an animal:");
        final var animal = Animal.from(ui.readLine());
        System.out.println(animal.getQuestion());

        final var answer = ui.askYesNo();
        System.out.print("You answered: ");
        System.out.println(answer ? "Yes" : "No");

        ui.sayGoodbye();
    }
}