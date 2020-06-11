package gasStation;

import java.time.LocalDateTime;

public class StationLoading {
    private String kolonkaId;
    private String fuelType;
    private int fuelQuantity;
    private LocalDateTime localDateTime;

    public StationLoading(String kolonkaId, String fuelType, int fuelQuantity, LocalDateTime localDateTime) {
        this.kolonkaId = kolonkaId;
        this.fuelType = fuelType;
        this.fuelQuantity = fuelQuantity;
        this.localDateTime = localDateTime;
    }
}
