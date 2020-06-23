package warehouse;

import java.util.Random;

public class Client extends Thread {

    private Shop shop;
    protected Warehouse.Product product;

    public Client(Shop shop, Warehouse.Product product) {
        this.shop = shop;
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            int quantity = new Random().nextInt(4) + 1;
            shop.takeProducts(product, quantity);
            System.out.println("Client bought " + quantity + " " + product);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Problems with the client");
            }
        }
    }
}
