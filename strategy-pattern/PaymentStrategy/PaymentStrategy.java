import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// interface implementation
interface payments{
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}

class PayByPayPal implements payments{
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

class PaymentStrategy{
    public static void main(String[] args){
        System.out.println("hello world");
    }
}