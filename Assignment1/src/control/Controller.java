package control;

import model.*;
import view.*;
import java.util.Scanner;

public class Controller {
    Scanner userInput;
    Model model = new Model();
    View view = new View();

    public Controller() {
        userInput = new Scanner(System.in);
        this.model = new Model();
    }

    public void run() {

    }
}
