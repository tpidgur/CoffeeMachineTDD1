package ua.rd.tdd.mvc;

import ua.rd.tdd.entities.Banknote;
import ua.rd.tdd.entities.BeverageType;
import ua.rd.tdd.entities.Machine;

import java.util.*;

public class Controller {
    Machine machine;
    View view;
    Scanner sc;


    public Controller(Machine machine, View view) {
        this.machine = machine;
        this.view = view;
        sc = new Scanner(System.in);
    }

    public void processUser() {
        view.printMessage(View.AVAILABLE_BEVERAGES + machine.getAvailableBeverageList());
        machine.passBanknotes(putMoneyInCoffeeMachine());
        view.printMessage(View.INPUT_CURRENCY + machine.getBanknoteListPutByUser());
        inputDrinkIdWithScanner();
        view.printMessage(View.CHANGE +  machine.getDrinkAndChangeTransaction());
    }

    public int inputIntWithScanner(String message) {
        view.printMessage(message);
        while (!sc.hasNextInt()) {
            view.printMessage(view.WRONG_INPUT);
            sc.next();
        }
        return sc.nextInt();
    }

    public int inputDrinkIdWithScanner() {
        while (true) {
            int res = inputIntWithScanner(View.SELECT_BEVERAGE + machine.getAvailableBeverageList());
            Optional containsValue = EnumSet.allOf(BeverageType.class).stream().filter(e -> e.ordinal() == res).findAny();
            if (containsValue.isPresent()) {
               BeverageType beverage=(BeverageType) containsValue.get();
                if ( machine.isEnoughMoney(beverage)) {
                    machine.setChosenDrink(beverage);
                    view.printMessage(view.CHOSEN_BEVERAGE_TYPE + machine.getChosenDrink());
                    return res;
                } else {
                    view.printMessage(view.NOT_ENOUGH_MONEY);
                    machine.passBanknotes(putMoneyInCoffeeMachine());
                }
            } else {
                view.printMessage(view.WRONG_INPUT);
            }
        }
    }

    public Banknote[] putMoneyInCoffeeMachine() {
        List<Banknote> banknotes = new ArrayList<>();
        while (true) {
            int res = inputIntWithScanner(view.PUT_MONEY);
            switch (res) {
                case -1://discard
                    view.printMessage(view.RETURN + machine.getMoneyBack());
                    System.exit(0);
                case 0://exit from putting money
                    Banknote[] b = new Banknote[banknotes.size()];
                    return banknotes.toArray(b);
                default://put money
                    Optional containsValue = EnumSet.allOf(Banknote.class).stream().filter(e -> e.getNominal() == res).findAny();
                    if (containsValue.isPresent()) {
                        banknotes.add((Banknote) containsValue.get());
                    } else {
                        view.printMessage(view.WRONG_INPUT);
                    }
            }
        }
    }


}
