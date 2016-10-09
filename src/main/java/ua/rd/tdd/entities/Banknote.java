package ua.rd.tdd.entities;

/**
 * Enum with nominal of banknote (String and int representation)
 */
public enum Banknote {
    ONE(1), TWO(2), FIVE(5), TEN(10), TWENTY(20), FIFTY(50);
    private int nominal;

    private Banknote(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }


}
