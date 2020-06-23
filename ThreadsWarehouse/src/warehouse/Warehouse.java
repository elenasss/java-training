package warehouse;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    public enum Type {
        FRUIT, VEGETABLE, MEAT
    }

    public enum Product {
        BANANA, ORANGE, APPLE,
        POTATO, EGGPLANT, CUCUMBER,
        PORK, BEEF,CHICKEN;
    }

    protected Map<Type, Map<Product, Integer>> catalog;
    protected final static int MIN_QUANTITY = 5;
    private final static int DELIVERY_QUANTITY = 25;
    private final static int BEGINNING_QUANTITY = 15;

    public Warehouse() {
        this.catalog = setProducts();
    }

    public synchronized void deliver() {
        while (!hasInsufficient()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fillQuantities();
        notifyAll();
    }

    public synchronized void takeProducts(Product product) {
        while (insufficient(product)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        removeQuantity(product);
        notifyAll();
    }

    private void removeQuantity(Product product) {
        for (Map<Product, Integer> products : catalog.values()) {
            for (Product p : products.keySet()) {
                if (p == product) {
                    products.put(p, products.get(p) - 5);
                }
            }
        }
    }

    private boolean insufficient(Product product) {
        for (Map<Product, Integer> products : catalog.values()) {
            for (Product p : products.keySet()) {
                if (p == product && products.get(p) < MIN_QUANTITY) {
                    return true;
                }
            }
        }
        return false;
    }


    protected void fillQuantities() {
        for (Map<Product, Integer> products : catalog.values()) {
            for (Product product : products.keySet()) {
                if (products.get(product) < MIN_QUANTITY) {
                    products.put(product, products.get(product) + DELIVERY_QUANTITY);
                    System.out.println(String.format("Not enough %s. Deliver from supplier", product));
                }
            }
        }
    }

    protected boolean hasInsufficient() {
        for (Map<Product, Integer> products : catalog.values()) {
            for (Product product : products.keySet()) {
                if (products.get(product) < MIN_QUANTITY) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Map<Type, Map<Product, Integer>> setProducts() {
        catalog = new HashMap<>();
        catalog.put(Type.FRUIT, new HashMap<>());
        catalog.put(Type.VEGETABLE, new HashMap<>());
        catalog.put(Type.MEAT, new HashMap<>());
        catalog.get(Type.FRUIT).put(Product.APPLE, BEGINNING_QUANTITY);
        catalog.get(Type.FRUIT).put(Product.ORANGE, BEGINNING_QUANTITY);
        catalog.get(Type.FRUIT).put(Product.BANANA, BEGINNING_QUANTITY);
        catalog.get(Type.VEGETABLE).put(Product.POTATO, BEGINNING_QUANTITY);
        catalog.get(Type.VEGETABLE).put(Product.CUCUMBER, BEGINNING_QUANTITY);
        catalog.get(Type.VEGETABLE).put(Product.EGGPLANT, BEGINNING_QUANTITY);
        catalog.get(Type.MEAT).put(Product.PORK, BEGINNING_QUANTITY);
        catalog.get(Type.MEAT).put(Product.CHICKEN, BEGINNING_QUANTITY);
        catalog.get(Type.MEAT).put(Product.BEEF, BEGINNING_QUANTITY);
        return catalog;
    }
}


