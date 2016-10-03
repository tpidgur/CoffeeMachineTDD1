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
        view.printMessage(View.INPUT_CURRENCY + machine.getMoneyPutByUser());


//        putMoneyInCoffeeMachine();
//        int beverageId = inputDrinkIdWithScanner();
//        giveBeverage(banknotes, beverageId);
    }

    public int inputIntWithScanner(String message) {
        view.printMessage(message);
        while (!sc.hasNextInt()) {
            view.printMessage(view.WRONG_INPUT);
            sc.next();
        }
        return sc.nextInt();
    }

    public Banknote[] putMoneyInCoffeeMachine() {
        List<Banknote> banknotes = new ArrayList<>();
        while (true) {
            int res = inputIntWithScanner(view.PUT_MONEY);
            switch (res) {
                case -1://discard
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
