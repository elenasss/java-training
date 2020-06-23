package warehouse;

import java.util.Map;

public class Shop extends Warehouse implements Runnable {

        private final static int MIN_QUANTITY = 5;
        private final static int DELIVERY_QUANTITY = 5;
        private Warehouse warehouse;
        protected Map<Warehouse.Type, Map<Product, Integer>> catalog;


        public Shop(Warehouse warehouse) {
            this.warehouse = warehouse;
            this.catalog = setProducts();
        }

        public void run() {
            while (true) {
                this.deliver();
            }
        }

    @Override
        protected void fillQuantities() {
            for (Map<Warehouse.Product, Integer> products : catalog.values()) {
                for (Warehouse.Product product : products.keySet()) {
                    if (products.get(product) < MIN_QUANTITY) {
                        System.out.println(product + " not enough. Deliver from warehouse");
                        products.put(product, products.get(product) + DELIVERY_QUANTITY);
                        warehouse.takeProducts(product);
                    }
                }
            }
        }

    public synchronized void takeProducts(Product product, int quantity) {
        while (insufficient(product, quantity)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        removeQuantity(product, quantity);
        notifyAll();
    }

    private boolean insufficient(Product product, int quantity){
        for (Map<Product, Integer> products : catalog.values()) {
            for (Product p : products.keySet()) {
                if (p == product && products.get(p) < quantity) {
                    return true;
                }
            }
        }
        return false;
    }

    private void removeQuantity(Product product, int quantity) {
        for (Map<Product, Integer> products : catalog.values()) {
            for (Product p : products.keySet()) {
                if (p == product) {
                    products.put(p, products.get(p) - quantity);
                }
            }
        }
    }
}
