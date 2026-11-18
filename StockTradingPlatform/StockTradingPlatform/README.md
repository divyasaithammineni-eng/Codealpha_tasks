# Stock Trading Platform (Java)

A console-based stock trading simulation using Java OOP.

## Features
- Display simulated market prices
- Buy and sell shares
- Validates trade quantity, available cash, and shares owned
- Track cash balance, holdings, portfolio market value, and profit/loss relative to the initial ₹100,000
- View transaction history with date and time
- Update sample prices to simulate market movement

**Important:** Prices are sample values, not live stock prices. No real money or brokerage is involved. Portfolio and transactions are stored in memory and reset when the application exits.

## Files
- `Stock.java` - Stock symbol, company name, price
- `UserAccount.java` - User balance, holdings, buying/selling logic
- `Transaction.java` - Transaction record
- `StockTradingPlatform.java` - Menu and application flow

## Run
Install a JDK (21 recommended), open this folder in VS Code and use terminal:

```bash
javac Stock.java Transaction.java UserAccount.java StockTradingPlatform.java
java StockTradingPlatform
```
