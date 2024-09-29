package control;

import model.*;
import view.*;

import java.util.HashSet;
import java.util.Scanner;

public class Controller {
    Scanner userInput;
    Model model = new Model();
    View view = new View();

    public Controller() {
        this.userInput = new Scanner(System.in);
        this.model = new Model();
        this.view = new View();
    }

    public void run() {
        boolean gameRunning = true;
        view.gameStart();

        while (gameRunning) {
            System.out.println(this.model.getCurrentStage().getVerbs());
            System.out.println(this.model.getCurrentStage().getNouns().keySet());

            String input = this.userInput.nextLine();
            String verb = (this.model.parseInput(input))[0], noun = (this.model.parseInput(input))[1];

            if (verb.equals("not found") || noun.equals("not found")) {
                this.view.invalidInput();
            } else {
                String response = this.model.getResponse(noun, verb);
                this.view.printResponse(response);
            }
            this.model.action(noun, verb);

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

            this.model.getTextParser().setVerbs((new HashSet<>(this.model.getCurrentStage().getVerbs())));
            this.model.getTextParser().setNouns(this.model.getCurrentStage().getNouns().keySet());
        }

        this.view.gameComplete();
    }
}
