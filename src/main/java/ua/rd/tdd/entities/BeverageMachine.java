package ua.rd.tdd.entities;

import java.util.*;

public class BeverageMachine implements Machine {
    /**
     * Defines the amount of banknotes (Integer) of corresponding nominal (ua.rd.tdd.entities.Banknote)
     */
    private Map<Banknote, Integer> banknotes = new TreeMap<>(Collections.reverseOrder());
    /**
     * Defines the amount of beverages (Integer) of corresponding type (ua.rd.tdd.entities.BeverageType)
     */
    private Map<BeverageType, Integer> drinks = new HashMap<>();

    private List<Banknote> moneyPutByUser = new ArrayList<>();

    private BeverageType chosenDrink;

     /**
     * initializes banknotes and drinks map with initial values
     */public void init() {
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

    /**
     * adds to the moneyPutByUser collection array of Banknotes
     *
     * @param arr ua.rd.tdd.entities.Banknote array
     */
    public void pass(Banknote[] arr) {
        Collections.addAll(moneyPutByUser, arr);
    }

    public List<Integer> getDrinkAndChangeTransaction() {
        decreaseByOneSelectedDrink();
        List<Integer> change = getBanknoteListChange();
        increaseCurrentBalance();
        return change;
    }

    /**
     * decreases the specific amount of drinks in the map drinks
     */
    public void decreaseByOneSelectedDrink() {
        int amount = drinks.get(chosenDrink);
        drinks.replace(chosenDrink, amount, amount - 1);
    }


    public boolean isNotEmptyAmountOfBeverage(BeverageType type) {
        return drinks.get(type) > 0;
    }

    /**
     * @return List  <ua.rd.tdd.entities.Banknote> put by user.
     */
    public List<Banknote> getMoneyBack() {
        List<Banknote> temp = moneyPutByUser;
        moneyPutByUser = null;
        return temp;
    }


    public int getTotalMoneyPut() {
        return moneyPutByUser.stream().mapToInt(i -> i.getNominal()).sum();
    }

    public boolean hasMachineBanknotesForChange() {
        return !banknotes.isEmpty();
    }

    /**
     * @return Set<ua.rd.tdd.entities.BeverageType> from drinks map with the amount>0
     */
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

    @Override
    public int getBeveragePrice() {
        return chosenDrink.getPrice();
    }

    @Override
    public boolean isEnoughMoney(BeverageType beverageType) {
        return getTotalMoneyPut() - beverageType.getPrice() >= 0;
    }

    public void setChosenDrink(BeverageType chosenDrink) {
        this.chosenDrink = chosenDrink;
    }

    public List<Integer> getBanknoteListChange() {
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
        Map<Banknote, Integer> customerMoney = countBanknotesRepetitionAsMap(moneyPutByUser);
        Iterator<Map.Entry<Banknote, Integer>> iter1 = customerMoney.entrySet().iterator();
        Iterator<Map.Entry<Banknote, Integer>> iter2 = banknotes.entrySet().iterator();
        while (iter2.hasNext() && iter1.hasNext()) {
            Map.Entry<Banknote, Integer> entry2 = iter2.next();
            Map.Entry<Banknote, Integer> entry1 = iter1.next();
            entry2.setValue(entry2.getValue() + entry1.getValue());
        }

    }

    public Map<Banknote, Integer> countBanknotesRepetitionAsMap(List<Banknote> list) {
        Map<Banknote, Integer> banknoteFrequency = new TreeMap<>(Collections.reverseOrder());
        List<Banknote> allBanknotes = Arrays.asList(Banknote.values());
        Iterator<Banknote> iter = allBanknotes.iterator();
        while (iter.hasNext()) {
            Banknote item = iter.next();
            int count = Collections.frequency(list, item);
            banknoteFrequency.put(item, count);
        }
        return banknoteFrequency;
    }

    public Set<BeverageType> getBeverageList() {
        return drinks.keySet();
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

    public List<Banknote> getBanknoteListPutByUser() {
        return moneyPutByUser;
    }
}
