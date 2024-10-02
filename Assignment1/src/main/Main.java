package main;

import control.*;

/**
 * Project: CS5010 Assignment 1
 * Author: Yichen (Cliff) Yang
 * Description: A text adventure of a ship captain going out to sea to find treasure.
 *
 * Game overview:
 * The game has three stages.
 *  - The first stage is on the dock. The player can find some rum to pick up before heading onto the ship. To depart, the player need to prepare the sails and hold the wheel.
 *  - The second stage is at sea. The player must repeatedly sail the ship to get to the destination. Performing certain actions can make the travelling faster.
 *  - The third stage is on the island. There are several possible actions where one of them leads to the completion of the game.
 *
 * Assignment Checklist:
 *  - Detecting a verb and noun from an input string
 *  - Has more than 5 possible verbs and 7 possible nouns
 *  - Gives a resulting output from a valid verb, noun combination
 *  - Tells what is wrong if input is not valid
 *  - Has additional outputs if satisfies a specific condition (changing game stages)
 *  - Has additional effects on the code under certain conditions (stage 2 travel speed depends on actions performed)
 *
 *  Sample entries for completion:
 *  Stage 1: look around - talk to crew - take rum - board ship - ready sails - hold wheel
 *  Stage 2: distribute rum - check map - adjust sails - sail ship - sail ship
 *  Stage 3: walk around the beach - pick up coconut - break coconut - follow footprints - dig up the bag
 *
 */

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }
}
