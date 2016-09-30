public enum BeverageType {
        LATTE(10),MACHIATO(15),AMERICANO(20);
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
}
