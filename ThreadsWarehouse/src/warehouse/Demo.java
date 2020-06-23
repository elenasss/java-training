package warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo {

    private static List<Warehouse.Product> products = new ArrayList();

    public static void main(String[] args) {

        products = setProducts();

        Warehouse wareHouse = new Warehouse();
        Supplier supplier = new Supplier(wareHouse);
        supplier.start();
        Shop shop1 = new Shop(wareHouse);
        Shop shop2 = new Shop(wareHouse);
        Shop shop3 = new Shop(wareHouse);
        new Thread(shop1).start();
        new Thread(shop2).start();
        new Thread(shop3).start();
        Client client1 = new Client(shop1, chooseProduct());
        Client client2 = new Client(shop1, chooseProduct());
        Client client3 = new Client(shop1, chooseProduct());
        client1.start();
        client2.start();
        client3.start();

        Client client4 = new Client(shop2, chooseProduct());
        Client client5 = new Client(shop2, chooseProduct());
        Client client6 = new Client(shop2, chooseProduct());
        client4.start();
        client5.start();
        client6.start();

        Client client7 = new Client(shop3, chooseProduct());
        Client client8 = new Client(shop3, chooseProduct());
        Client client9 = new Client(shop3, chooseProduct());
        client7.start();
        client8.start();
        client9.start();
    }

    private static List<Warehouse.Product> setProducts() {
        products.add(Warehouse.Product.APPLE);
        products.add(Warehouse.Product.ORANGE);
        products.add(Warehouse.Product.BANANA);
        products.add(Warehouse.Product.CUCUMBER);
        products.add(Warehouse.Product.EGGPLANT);
        products.add(Warehouse.Product.POTATO);
        products.add(Warehouse.Product.PORK);
        products.add(Warehouse.Product.CHICKEN);
        products.add(Warehouse.Product.BEEF);
        return products;
    }

    private static Warehouse.Product chooseProduct() {
        int random = new Random().nextInt(9);
        return products.get(random);
    }
}
