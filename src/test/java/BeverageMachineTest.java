import org.junit.Before;

import org.junit.Test;


import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BeverageMachineTest {
    BeverageMachine machine;

    @Before
    public void createBeverageMachineInstance() {
        machine = new BeverageMachine();
    }


    @Test
    public void passBanknoteTest() {
        Banknote[] arr = new Banknote[]{Banknote.FIFTY, Banknote.FIVE, Banknote.TEN};
        machine.passBanknotes(arr);
        List<Banknote> userMoney = machine.getBanknoteListPutByUser();
        assertThat(userMoney, is(Arrays.asList(new Banknote[]{Banknote.FIFTY, Banknote.FIVE, Banknote.TEN})));
    }

    @Test
    public void getTotalMoneyPutTest() {
        Banknote[] arr = new Banknote[]{Banknote.FIFTY, Banknote.FIVE, Banknote.TEN};
        machine.passBanknotes(arr);
        int sum = machine.getTotalMoneyPut();
        assertThat(sum, is(65));
    }

    @Test
    public void getBeverageList() {
        Set<BeverageType> list = machine.getBeverageList();
        Set<BeverageType> expected = new LinkedHashSet<>(Arrays.asList(BeverageType.values()));
        assertThat(list, is(expected));
    }

    @Test
    public void getAvailableBeverageList() {
        machine.getDrinks().put(BeverageType.AMERICANO, 0);//there is no any AMERICANO left
        Set<BeverageType> actual = machine.getAvailableBeverageList();
        Set<BeverageType> expected = new LinkedHashSet<>(Arrays.asList(BeverageType.LATTE, BeverageType.MACHIATO));
        assertThat(actual, is(expected));
    }


    @Test
    public void decreaseByOneSelectedDrinkTest() {
        int amount1 = machine.getDrinks().get(BeverageType.AMERICANO);
        machine.decreaseByOneSelectedDrink(BeverageType.AMERICANO);
        int amount2 = machine.getDrinks().get(BeverageType.AMERICANO);
        assertThat(amount1 - amount2, is(1));
    }

    @Test
    public void checkThatNotEmptyAmountOfBeverage() {
        boolean notEmpty = machine.isNotEmptyAmountOfBeverage(BeverageType.AMERICANO);
        assertThat(notEmpty, is(true));
    }

    @Test
    public void getMoneyBackTest() {
        machine.passBanknotes(new Banknote[]{Banknote.FIFTY, Banknote.FIVE, Banknote.TEN});
        List<Banknote> list = machine.getMoneyBack();
        List<Banknote> expected = Arrays.asList(new Banknote[]{Banknote.FIFTY, Banknote.FIVE, Banknote.TEN});
        assertThat(list, is(expected));
    }

    @Test
    public void hasAnyMoneyForChangeTest() {
        boolean hasChange = machine.hasAnyMoneyForChange();
        assertThat(hasChange, is(true));
    }


    @Test
    public void calculateChangeTest() {
        machine.passBanknotes(new Banknote[]{Banknote.FIFTY, Banknote.FIVE, Banknote.TEN});
        machine.setChosenDrink(BeverageType.AMERICANO);
        int actual = machine.calculateChange();
        assertThat(actual, is(45));
    }

    @Test
    public void getChangeTest() {
        machine.passBanknotes(new Banknote[]{Banknote.FIFTY, Banknote.FIVE, Banknote.TEN});
        machine.setChosenDrink(BeverageType.AMERICANO);
        List<Integer> receivedChange = machine.getBanknoteListChange();
        List<Integer> expected = Arrays.asList(20, 20, 5);
        assertThat(receivedChange, is(expected));
    }


    @Test
    public void increaseCurrentBalanceTest() {
        machine.passBanknotes(new Banknote[]{
                Banknote.ONE,
                Banknote.FIVE,
                Banknote.FIVE,
                Banknote.TEN,
                Banknote.TEN,
                Banknote.TEN,
                Banknote.ONE,
                Banknote.TWENTY});
        machine.increaseCurrentBalance();
        Map<Banknote, Integer> map = new TreeMap<>(Collections.reverseOrder());
        map.put(Banknote.ONE, 12);
        map.put(Banknote.TWO, 10);
        map.put(Banknote.FIVE, 12);
        map.put(Banknote.TEN, 13);
        map.put(Banknote.TWENTY, 11);
        map.put(Banknote.FIFTY, 10);
        assertThat(machine.getBanknotes(), is(map));
    }

    @Test
    public void countBanknotesRepetitionAsMapTest() {
        List<Banknote> list = Arrays.asList(new Banknote[]{
                Banknote.ONE,
                Banknote.FIVE,
                Banknote.FIVE,
                Banknote.TEN,
                Banknote.TEN,
                Banknote.TEN,
                Banknote.ONE,
                Banknote.TWENTY});
        Map<Banknote, Integer> banknoteFrequency = machine.countBanknotesRepetitionAsMap(list);
        Map<Banknote, Integer> map = new HashMap<>();
        map.put(Banknote.ONE, 2);
        map.put(Banknote.TWO, 0);
        map.put(Banknote.FIVE, 2);
        map.put(Banknote.TEN, 3);
        map.put(Banknote.TWENTY, 1);
        map.put(Banknote.FIFTY, 0);
        assertThat(banknoteFrequency, is(map));
    }
}
