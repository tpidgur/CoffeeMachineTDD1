package ua.rd.tdd.entities;

import java.util.List;
import java.util.Set;

/**
 * defines basic operations of the machine
 */
public interface Machine {
    public void passBanknotes(Banknote[] arr);

    public List<Banknote> getMoneyBack();

    public int getTotalMoneyPut();

    public List<Banknote> getBanknoteListPutByUser();

    public Set<BeverageType> getAvailableBeverageList();

    public int calculateChange();

    public List<Integer> getBanknoteListChange();


    boolean isEnoughMoney(BeverageType beverageType);

    public void setChosenDrink(BeverageType chosenDrink);

    public BeverageType getChosenDrink();

    public int getBeveragePrice();
    public List<Integer> getDrinkAndChangeTransaction();
}
