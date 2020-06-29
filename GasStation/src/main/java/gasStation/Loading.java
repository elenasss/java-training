package gasStation;

import java.time.LocalDateTime;

public class Loading implements Comparable<Loading> {
    private String kolonkaId;
    private String fuelType;
    private int fuelQuantity;
    private LocalDateTime loadingTime;

    public Loading(String kolonkaId, String fuelType, int fuelQuantity, LocalDateTime loadingTime) {
        this.kolonkaId = kolonkaId;
        this.fuelType = fuelType;
        this.fuelQuantity = fuelQuantity;
        this.loadingTime = loadingTime;
    }

    @Override
    public int compareTo(Loading o) {
        return this.loadingTime.compareTo(o.loadingTime);
    }

    @Override
    public String toString() {
        return "Loading{" +
                "kolonkaId='" + kolonkaId + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", fuelQuantity=" + fuelQuantity +
                ", loadingTime=" + loadingTime +
                '}';
    }
}
