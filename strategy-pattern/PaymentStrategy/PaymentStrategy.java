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
    public static void main(String[] args){
        System.out.println("hello world");
    }
}