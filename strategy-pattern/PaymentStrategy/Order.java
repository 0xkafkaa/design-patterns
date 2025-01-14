import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// interface implementation
interface Payments{
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}

class PayByPayPal implements Payments{
    private static final Map<String, String> DB = new HashMap<>();    
    InputStreamReader in = new InputStreamReader(System.in);
    private final BufferedReader br = new BufferedReader(in);
    private String email;
    private String password;
    private boolean signedIn;

    static{
        DB.put("kafkaa", "kafkaa.xyz");
        DB.put("murakami", "murakami.xyz");
    }

    public void collectPaymentDetails(){
        try {
            while(!signedIn){
                System.out.println("Enter Email: ");
                email = br.readLine();
                System.out.println("Enter Password: ");
                password = br.readLine();
                if(verify()){
                    System.out.println("User auth successful");
                } else {
                    System.out.println("Unauthorized user");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean verify(){
        setSignedIn(email.equals(DB.get(password)));
        return signedIn;
    }

    private void setSignedIn(boolean signedIn){
        this.signedIn = signedIn;
    }

    public boolean pay(int paymentAmount){
        if(signedIn){
            System.out.println("Payment made successfully in PayPal of amount: " + paymentAmount);
            return true;
        } else return false;
    }
}

class PayByCreditCard implements Payments{
    InputStreamReader in = new InputStreamReader(System.in);
    private final BufferedReader br = new BufferedReader(in);
    CreditCard card;

    public void collectPaymentDetails(){
        try {
            System.out.println("Enter the card number: ");
            String cardNumber = br.readLine();
            System.out.println("Enter the card expiration date 'mm/yy': ");
            String expiryDate = br.readLine();
            System.out.println("Enter the CVV code: ");
            String cvvCode = br.readLine();
            card = new CreditCard(cardNumber, expiryDate, cvvCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public boolean pay(int paymentAmount){
        if(validCard()){
            card.debitAmount(paymentAmount);
            System.out.println("Payment made successfully of amount " + paymentAmount + "by Credit Card");
            return true;
        } else return false;
    }

    private boolean validCard(){
        return card != null;
    }
}

class CreditCard {
    private int amount;
    private String cardNumber;
    private String expiryDate;
    private String cvvCode;

    CreditCard(String cardNumber, String expiryDate, String cvvCode){
        this.amount = 1000;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvvCode = cvvCode;
    }

    public int getAmount(){
        return this.amount;
    }

    public void creditAmount(int amount){
        this.amount += amount;
    }

    public void debitAmount(int amount){
        this.amount -= amount;
    }
}

public class Order {
    private int totalCost = 0;
    private boolean isClosed = false;

    public void processOrder(Payments strategy) {
        strategy.collectPaymentDetails();
        
    }

    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }
}

class PaymentStrategy{
    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static Payments strategy;

    static {
        priceOnProducts.put(1, 2200);
        priceOnProducts.put(2, 1850);
        priceOnProducts.put(3, 1100);
        priceOnProducts.put(4, 890);
    }
    public static void main(String[] args) throws IOException {
        while (!order.isClosed()) {
            int cost;

            String continueChoice;
            do {
                System.out.print("Please, select a product:" + "\n" +
                        "1 - Mother board" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memory" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);
                System.out.print("Count: ");
                int count = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost * count);
                System.out.print("Do you wish to continue selecting products? Y/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("Y"));

            if (strategy == null) {
                System.out.println("Please, select a payment method:" + "\n" +
                        "1 - PalPay" + "\n" +
                        "2 - Credit Card");
                String paymentMethod = reader.readLine();

                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }
            }


            order.processOrder(strategy);

            System.out.print("Pay " + order.getTotalCost() + " units or Continue shopping? P/C: ");
            String proceed = reader.readLine();
            if (proceed.equalsIgnoreCase("P")) {

                if (strategy.pay(order.getTotalCost())) {
                    System.out.println("Payment has been successful.");
                } else {
                    System.out.println("FAIL! Please, check your data.");
                }
                order.setClosed();
            }
        }
    }
}