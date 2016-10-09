package ua.rd.tdd.mvc;

import ua.rd.tdd.entities.BeverageMachine;
import ua.rd.tdd.entities.Machine;

public class Main {
    public static void main(String[] args) {
        // Initialization

        View view = new View();
        BeverageMachine machine = new BeverageMachine();
        machine.init();
        Controller controller = new Controller(machine, view);
        // Run
        controller.processUser();
    }
}
