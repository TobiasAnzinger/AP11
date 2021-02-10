package aufgabeWallet;

public class Money {

    private int amount;
    private String currency;

    public Money (String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public void changeAmount(int amount) {
        this.amount += amount;
    }

    public int getAmount() {
        return amount;
    }
}