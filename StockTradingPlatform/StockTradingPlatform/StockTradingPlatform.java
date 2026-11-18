import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class StockTradingPlatform {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Stock> market = new ArrayList<>();
    private static UserAccount user;

    private static void initializeMarket() {
        market.add(new Stock("TCS", "Tata Consultancy", 3850.00));
        market.add(new Stock("INFY", "Infosys", 1625.50));
        market.add(new Stock("RELI", "Reliance Industries", 2910.00));
        market.add(new Stock("HDFCB", "HDFC Bank", 1740.00));
        market.add(new Stock("ITC", "ITC Limited", 465.25));
    }

    private static Stock findStock(String symbol) {
        for (Stock stock : market) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) return stock;
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static void showMarket() {
        System.out.println("\\n========== STOCK MARKET (SIMULATED) ==========");
        System.out.printf("%-8s %-22s %s%n", "Symbol", "Company", "Price");
        for (Stock stock : market) stock.display();
        System.out.println("Note: These are sample prices for simulation, not live market prices.");
    }

    private static void buyStock() {
        showMarket();
        System.out.print("Enter stock symbol to buy: ");
        Stock stock = findStock(sc.nextLine().trim());
        if (stock == null) {
            System.out.println("Invalid stock symbol.");
            return;
        }
        int quantity = readInt("Enter quantity: ");
        user.buy(stock, quantity);
    }

    private static void sellStock() {
        showMarket();
        System.out.print("Enter stock symbol to sell: ");
        Stock stock = findStock(sc.nextLine().trim());
        if (stock == null) {
            System.out.println("Invalid stock symbol.");
            return;
        }
        int quantity = readInt("Enter quantity: ");
        user.sell(stock, quantity);
    }

    private static void showPortfolio() {
        System.out.println("\\n========== YOUR PORTFOLIO ==========");
        System.out.printf("Cash Balance: ₹%.2f%n", user.getCashBalance());
        HashMap<String, Integer> holdings = user.getHoldings();
        double investmentValue = 0;

        if (holdings.isEmpty()) {
            System.out.println("You do not own any stocks yet.");
        } else {
            System.out.printf("%-8s %-22s %-10s %-12s%n",
                    "Symbol", "Company", "Quantity", "Market Value");
            for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
                Stock stock = findStock(entry.getKey());
                int qty = entry.getValue();
                double value = stock.getPrice() * qty;
                investmentValue += value;
                System.out.printf("%-8s %-22s %-10d ₹%-12.2f%n",
                        stock.getSymbol(), stock.getCompanyName(), qty, value);
            }
        }

        double totalValue = user.getCashBalance() + investmentValue;
        double profitLoss = totalValue - 100000.00;
        System.out.printf("Invested Market Value: ₹%.2f%n", investmentValue);
        System.out.printf("Total Portfolio Value : ₹%.2f%n", totalValue);
        System.out.printf("Profit/Loss vs Initial ₹100,000: ₹%.2f%n", profitLoss);
    }

    private static void updateSimulatedPrice() {
        showMarket();
        System.out.print("Enter stock symbol to update: ");
        Stock stock = findStock(sc.nextLine().trim());
        if (stock == null) {
            System.out.println("Invalid stock symbol.");
            return;
        }
        System.out.print("Enter new simulated price (₹): ");
        try {
            double price = Double.parseDouble(sc.nextLine().trim());
            if (price <= 0) {
                System.out.println("Price must be greater than zero.");
                return;
            }
            stock.setPrice(price);
            System.out.println("Simulated price updated.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid price.");
        }
    }

    public static void main(String[] args) {
        initializeMarket();
        user = new UserAccount("DemoUser", 100000.00);

        while (true) {
            System.out.println("\\n====== STOCK TRADING PLATFORM ======");
            System.out.println("1. Display Market Data");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. Update Simulated Stock Price");
            System.out.println("7. Exit");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: showMarket(); break;
                case 2: buyStock(); break;
                case 3: sellStock(); break;
                case 4: showPortfolio(); break;
                case 5: user.displayTransactions(); break;
                case 6: updateSimulatedPrice(); break;
                case 7:
                    System.out.println("Thank you for using the simulated trading platform!");
                    sc.close();
                    return;
                default: System.out.println("Invalid choice. Please select 1-7.");
            }
        }
    }
}
