import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Tema2 {

    public static void main(String[] args) throws IOException {

        String folder = args[0];
        int nrThreads = Integer.parseInt(args[1]);

        BufferedReader orders = new BufferedReader(new FileReader(folder + "/orders.txt"));
        BufferedWriter ordersF = new BufferedWriter(new FileWriter("orders_out.txt"));
        BufferedWriter ordersProductF = new BufferedWriter(new FileWriter("order_products_out.txt"));

        Semaphore semaphore = new Semaphore(nrThreads);
        ExecutorService executor = Executors.newFixedThreadPool(nrThreads);
        for(int i = 0 ; i < nrThreads; i++){
            executor.execute(new Orders(orders,semaphore,folder,ordersF,ordersProductF));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        orders.close();
        ordersF.close();
        ordersProductF.close();
    }
}
