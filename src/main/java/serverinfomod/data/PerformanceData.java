package serverinfomod.data;

public class PerformanceData {
    private final String tickTime;
    private final int TPS;
    private final long totalTicks;
    private final long skippedTicks;

    public PerformanceData(String tickTime, int tps, long totalTicks, long skippedTicks) {
        this.tickTime = tickTime;
        this.TPS = tps;
        this.totalTicks = totalTicks;
        this.skippedTicks = skippedTicks;
    }
}
