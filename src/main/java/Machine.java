import java.util.List;
import java.util.Set;

/**
 * defines basic operations of the machine
 */
public interface Machine {
    public void passBanknotes(Banknote[] arr);

    public List<Banknote> getMoneyBack();

    public int getTotalMoneyPut();

    public List<Banknote> getMoneyPutByUser();

    public Set<BeverageType> getAvailableBeverageList();

    public int calculateChange();

    public List<Integer> getChange();

    public void increaseCurrentBalance();

    public void setChosenDrink(BeverageType chosenDrink);

    public BeverageType getChosenDrink();

    public int getBeveragePrice();
}
