import java.util.*;

import static java.util.Collections.frequency;

public class BeverageMachine {
    private Map<Banknote, Integer> banknotes = new HashMap<>();
    private Map<BeverageType, Integer> drinks = new HashMap<>();
    private List<Banknote> moneyPutByUser;
    private BeverageType chosenDrink;

    {
        banknotes.put(Banknote.ONE, 10);
        banknotes.put(Banknote.TWO, 10);
        banknotes.put(Banknote.FIVE, 10);
        banknotes.put(Banknote.TEN, 10);
        banknotes.put(Banknote.TWENTY, 10);
        banknotes.put(Banknote.FIFTY, 10);
        drinks.put(BeverageType.LATTE, 10);
        drinks.put(BeverageType.MACHIATO, 10);
        drinks.put(BeverageType.AMERICANO, 10);
    }


    public void passBanknotes(Banknote[] arr) {
        moneyPutByUser = Arrays.asList(arr);
    }

    public Set<BeverageType> getBeverageList() {
        return drinks.keySet();
    }

    public void decreaseByOneSelectedDrink(BeverageType type) {
        int amount = drinks.get(type);
        drinks.replace(type, amount, amount - 1);
    }

    public Map<Banknote, Integer> getBanknotes() {
        return banknotes;
    }

    public void setBanknotes(Map<Banknote, Integer> banknotes) {
        this.banknotes = banknotes;
    }

    public Map<BeverageType, Integer> getDrinks() {
        return drinks;
    }

    public boolean isNotEmptyAmountOfBeverage(BeverageType type) {
        return drinks.get(type) > 0;
    }

    public List<Banknote> getMoneyBack() {
        List<Banknote> temp = moneyPutByUser;
        moneyPutByUser = null;
        return temp;
    }

    public List<Banknote> getMoneyPutByUser() {
        return moneyPutByUser;
    }

    public int getTotalMoneyPut() {
        return moneyPutByUser.stream().mapToInt(i -> i.getNominal()).sum();
    }

    public boolean hasAnyMoneyForChange() {
        return banknotes.isEmpty();
    }

    public Set<BeverageType> getAvailableBeverageList() {
        Set<BeverageType> available = new LinkedHashSet<>();
        Iterator<Map.Entry<BeverageType, Integer>> iter = drinks.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<BeverageType, Integer> entry = iter.next();
            if (entry.getValue() > 0) {
                available.add(entry.getKey());
            }
        }
        return available;
    }

    public int calculateChange() {
        return getTotalMoneyPut() - chosenDrink.getPrice();
    }

    public BeverageType getChosenDrink() {
        return chosenDrink;
    }

    public void setChosenDrink(BeverageType chosenDrink) {
        this.chosenDrink = chosenDrink;
    }

    public List<Integer> getChange() {
        int requiredChange = calculateChange();
        Iterator<Map.Entry<Banknote, Integer>> iter = banknotes.entrySet().iterator();
        List<Integer> change = new LinkedList<>();
        while (iter.hasNext()) {
            Map.Entry<Banknote, Integer> entry = iter.next();
            int banknote = entry.getKey().getNominal();
            int amount = entry.getValue();
            while (banknote <= requiredChange && amount > 0) {
                change.add(banknote);
                requiredChange = requiredChange - banknote;
                entry.setValue(amount--);

            }
        }
        return change;
    }


    public void increaseCurrentBalance() {

    }

    public Map<Banknote, Integer> countBanknotesRepetitionAsMap(List<Banknote> list) {
        Map<Banknote, Integer> banknoteFrequency = new HashMap<>();
        List<Banknote> allBanknotes = Arrays.asList(Banknote.values());
        Iterator<Banknote> iter = allBanknotes.iterator();
        while (iter.hasNext()) {
            Banknote item = iter.next();
            int count = Collections.frequency(list, item);
            banknoteFrequency.put(item, count);
        }
        return banknoteFrequency;
    }
}
