package view;

public class View {
    public void gameStart() {
        System.out.println("Welcome to my game!");
    }

    public void gameSetting() {
        System.out.println("You are a captain of a ship heading out to find a treasure on a island. Currently you are standing on the dock. ");
    }

    public void stage1To2() {
        System.out.println("\n\nYou set sail to the open ocean, beginning your trip towards the treasure island.");
    }

    public void stage2To3() {
        System.out.println("\n\nYou arrive at the treasure island.");
    }

    public void gameComplete() {
        System.out.println("Congratulations! You have found the treasure!");
        System.out.println("I hope you enjoyed the game!");
    }

    public void invalidNoun() {
        System.out.println("It seems that this is not a valid noun / item. Try acting on something else. ");
    }

    public void invalidVerb() {
        System.out.println("You cannot do this on this noun / item. Try a different action");
    }

    public void printResponse(String response) {
        System.out.println(response);
    }

    public void askHacks() {
        System.out.println("Type 'enable hacks' to show verbs and nouns. Type anything else to continue. ");
    }

    public void confirmHacks() {
        System.out.println("Hacks enabled.");
    }
}
