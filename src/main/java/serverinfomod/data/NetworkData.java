package serverinfomod.data;

public class NetworkData {
    private final String averageIn;
    private final long averageInPackets;
    private final String totalIn;
    private final long totalInPackets;
    private final String averageOut;
    private final long averageOutPackets;
    private final String totalOut;
    private final long totalOutPackets;

    public NetworkData(
        String averageIn,
        long averageInPackets,
        String totalIn,
        long totalInPackets,
        String averageOut,
        long averageOutPackets,
        String totalOut,
        long totalOutPackets
    ) {
        this.averageIn = averageIn;
        this.averageInPackets = averageInPackets;
        this.totalIn = totalIn;
        this.totalInPackets = totalInPackets;
        this.averageOut = averageOut;
        this.averageOutPackets = averageOutPackets;
        this.totalOut = totalOut;
        this.totalOutPackets = totalOutPackets;
    }
}
