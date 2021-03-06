package ua.rd.tdd.entities;

/**
 * Beverage enum and corresponding prices
 */
public enum BeverageType {
    LATTE(10), MACHIATO(15), AMERICANO(20);
    private int price;

    BeverageType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString() + "(id=" + super.ordinal() + ", price=" + getPrice() + ")";
    }
}
