import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type;
    private String symbol;
    private int quantity;
    private double price;
    private LocalDateTime time;

    public Transaction(String type, String symbol, int quantity, double price) {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.time = LocalDateTime.now();
    }

    public void display() {
        System.out.printf("%-5s %-8s %-8d ₹%-10.2f %s%n",
                type, symbol, quantity, price,
                time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }
}
