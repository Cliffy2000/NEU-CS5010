package control;

import model.*;
import view.*;

import java.util.HashSet;
import java.util.Scanner;

public class Controller {
    Scanner userInput;
    Model model = new Model();
    View view = new View();

    boolean hacks = false; // hacks to show nouns and verbs before each player entry

    public Controller() {
        this.userInput = new Scanner(System.in);
        this.model = new Model();
        this.view = new View();
    }

    public void run() {
        boolean gameRunning = true;
        view.gameStart();

        this.view.askHacks();
        String hacksInput = this.userInput.nextLine();
        if (hacksInput.equals("enable hacks")) {
            this.view.confirmHacks();
        }

        this.view.gameSetting();
        // The main game loop
        while (gameRunning) {
            if (hacksInput.equals("enable hacks")) {
                System.out.print("Verbs: ");
                System.out.println(this.model.getCurrentStage().getVerbs());
                System.out.print("Nouns: ");
                System.out.println(this.model.getCurrentStage().getNouns().keySet());
            }

            String input = this.userInput.nextLine();
            String verb = (this.model.parseInput(input))[0], noun = (this.model.parseInput(input))[1];

            if (noun.equals("not found")) {
                this.view.invalidNoun();
            } else if (verb.equals("not found")) {
                this.view.invalidVerb();
            } else {
                String response = this.model.getResponse(noun, verb);
                this.view.printResponse(response);
            }
            this.model.action(noun, verb);

            // Checks if the player can move to the next stage
            switch (this.model.getProgression()) {
                case 0:
                    this.view.stage1To2();
                    break;
                case 1:
                    this.view.stage2To3();
                    break;
                case 2:
                    gameRunning = false;
                    break;
            }

            // updates the text parser to the latest sets of verbs and nouns
            this.model.getTextParser().setVerbs((new HashSet<>(this.model.getCurrentStage().getVerbs())));
            this.model.getTextParser().setNouns(this.model.getCurrentStage().getNouns().keySet());
        }

        this.view.gameComplete();
    }
}
