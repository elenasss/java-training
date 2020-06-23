package warehouse;

public class Supplier extends Thread {

    private Warehouse wareHouse;

    public Supplier(Warehouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        while (true) {
            wareHouse.deliver();
        }
    }
}
