import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Semaphore;

public class Order_Products implements Runnable{
    public String costumerID;
    public BufferedReader order_products;
    public Semaphore semaphore;
    public BufferedWriter orders_productF;
    public Order_Products(String costumerID,BufferedReader order_products, Semaphore semaphore,
                          BufferedWriter ordersProductF) {
        this.costumerID = costumerID;
        this.order_products = order_products;
        this.semaphore = semaphore;
        this.orders_productF = ordersProductF;
    }

    @Override
    public void run() {
        String strCurrentLine;
        try{
            while((strCurrentLine = order_products.readLine()) != null) {
                String[] ids = strCurrentLine.split(",");
                String cCostumerID = ids[0];
                if(Objects.equals(costumerID, cCostumerID)){
                    orders_productF.write(strCurrentLine + ",shipped" + "\n");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        semaphore.release();
    }
}
