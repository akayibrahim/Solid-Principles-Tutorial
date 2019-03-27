package LiskovSubstitution;

/**
 * Created by iakay on 18.03.2019.
 */
public class LSProblem {
    static class DrinkMachine {
        public static abstract class Machine {
            public static void prepareDrink() {
                System.out.println("prepared.");
            }

            public abstract void addMilk();
        }

        public static class CoffeeMachine extends Machine {

            public void addMilk() {
                System.out.println("milk added.");
            }
        }

        public static class TeaMachine extends Machine {

            public void addMilk() {
                // nothing
            }
        }
    }

    public static void main(String[] args) {
        DrinkMachine.Machine coffee = new DrinkMachine.CoffeeMachine();
        DrinkMachine.Machine tea = new DrinkMachine.TeaMachine();

        coffee.prepareDrink();
        coffee.addMilk();

        tea.prepareDrink();
        tea.addMilk(); // burada anlamsız
    }
}
