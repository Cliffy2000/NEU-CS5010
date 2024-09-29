package view;

public class View {
    public void gameStart() {
        System.out.println("Welcome to the game. You are a captain of a ship heading out to find a treasure on a island. Currently you are standing on the dock. ");
    }

    public void stage1To2() {
        System.out.println("You set sail to the open ocean, beginning your trip towards the treasure island.");
    }

    public void stage2To3() {
        System.out.println("You arrive at the treasure island.");
    }

    public void gameComplete() {
        System.out.println("Congratulations! You have found the treasure!");
        System.out.println("I hope you enjoyed the game!");
    }
}
