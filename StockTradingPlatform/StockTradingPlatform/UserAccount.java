import java.util.ArrayList;

public class UserAccount {
    private String username;
    private double cashBalance;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    // Portfolio quantity held for each stock symbol
    private java.util.HashMap<String, Integer> holdings = new java.util.HashMap<>();

    public UserAccount(String username, double initialCash) {
        this.username = username;
        this.cashBalance = initialCash;
    }

    public String getUsername() { return username; }
    public double getCashBalance() { return cashBalance; }
    public java.util.HashMap<String, Integer> getHoldings() { return holdings; }
    public ArrayList<Transaction> getTransactions() { return transactions; }

    public boolean buy(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return false;
        }
        double cost = stock.getPrice() * quantity;
        if (cost > cashBalance) {
            System.out.println("Insufficient funds. Trade not completed.");
            return false;
        }
        cashBalance -= cost;
        holdings.put(stock.getSymbol(),
                holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
        transactions.add(new Transaction("BUY", stock.getSymbol(), quantity, stock.getPrice()));
        System.out.printf("Bought %d shares of %s successfully.%n", quantity, stock.getSymbol());
        return true;
    }

    public boolean sell(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return false;
        }
        int owned = holdings.getOrDefault(stock.getSymbol(), 0);
        if (quantity > owned) {
            System.out.println("You do not own enough shares to sell.");
            return false;
        }
        cashBalance += stock.getPrice() * quantity;
        int remaining = owned - quantity;
        if (remaining == 0) holdings.remove(stock.getSymbol());
        else holdings.put(stock.getSymbol(), remaining);
        transactions.add(new Transaction("SELL", stock.getSymbol(), quantity, stock.getPrice()));
        System.out.printf("Sold %d shares of %s successfully.%n", quantity, stock.getSymbol());
        return true;
    }

    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\\n------ Transaction History ------");
        System.out.printf("%-5s %-8s %-8s %-12s %s%n",
                "Type", "Symbol", "Qty", "Price", "Date & Time");
        for (Transaction t : transactions) t.display();
    }
}
