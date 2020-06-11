package library;

import java.time.LocalDateTime;

public class HistoryLog implements Comparable<HistoryLog>
{

    private String client;
    private LocalDateTime borrowingTime;
    private LocalDateTime returnTime;
    private boolean isOverDue;

    public HistoryLog(String client, int period, LocalDateTime borrowingTime) {
        this.client = client;
        this.borrowingTime = borrowingTime;
        this.returnTime = this.borrowingTime.plusSeconds(period);
    }

    public LocalDateTime getBorrowingTime() {
        return borrowingTime;
    }

    public String getClient() {
        return client;
    }

    public void setOverDue(boolean overDue) {
        isOverDue = overDue;
    }

    public boolean isOverDue() {
        return isOverDue;
    }

        @Override
    public int compareTo(HistoryLog o) {
       return this.borrowingTime.compareTo(o.borrowingTime);
    }
}
