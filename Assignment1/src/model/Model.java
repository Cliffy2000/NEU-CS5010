/*
Citations:
    Help on ArrayLists: https://www.w3schools.com/java/java_arraylist.asp
    Help on Java lists: https://www.geeksforgeeks.org/list-interface-java-examples/

 */

package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    int gameStep = 0;   // Tracks the number of moves by the player, is used to measure time passed
    int gameTime = 0;   // This is the day / night tracker, 0 is for day and 1 for night.
    String gameScene = "Dock";  // Scenes include Dock / Sea / Island / Market

    List<Interactable> inventory;

    public void init() {
        inventory = new ArrayList<Interactable>();
    }

    public void parseText(String userInput) {

    }
}
