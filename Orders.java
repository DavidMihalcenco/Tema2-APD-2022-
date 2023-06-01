import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Orders implements Runnable {
    public BufferedReader orders;
    public BufferedWriter ordersF;
    public BufferedWriter ordersProductF;
    public Semaphore semaphore;
    public String folder;
    public Orders(BufferedReader orders,Semaphore semaphore,String folder,BufferedWriter ordersF,
                  BufferedWriter ordersProductF) {
        this.orders = orders;
        this.semaphore = semaphore;
        this.folder = folder;
        this.ordersF = ordersF;
        this.ordersProductF = ordersProductF;
    }

    @Override
    public void run() {
        String strCurrentLine;
        try {
            while ((strCurrentLine = orders.readLine()) != null) {

                String[] ids = strCurrentLine.split(",");
                String costumerID = ids[0];
                int producesNR = Integer.parseInt(ids[1]);
                if (producesNR != 0) {
                    ExecutorService executor = Executors.newFixedThreadPool(producesNR);
                    BufferedReader order_products = new BufferedReader(new FileReader(folder +
                            "/order_products.txt"));
                    for (int i = 0; i < producesNR; i++) {
                        semaphore.acquire();
                        executor.execute(new Order_Products(costumerID, order_products, semaphore, ordersProductF));
                    }
                    ordersF.write(strCurrentLine + ",shipped" + "\n");
                    executor.shutdown();
                    try {
                        executor.awaitTermination(1, TimeUnit.MINUTES);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    order_products.close();
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
