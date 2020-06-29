package gasStation;

public class Car {

    public enum FuelType {
        GAS, DISEL, PETROL
    }

    private FuelType fuelType;
    private int fuelVolume;
    private int kolonka;

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(int fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public int getKolonka() {
        return kolonka;
    }

    public void setKolonka(int kolonka) {
        this.kolonka = kolonka;
    }
}
