package serverinfomod.data;

public class ServerData {
    private final PlayersData players;
    private final WorldData world;
    private final PerformanceData performance;
    private final NetworkData network;

    public ServerData(PlayersData players, WorldData world, PerformanceData performance, NetworkData network) {
        this.players = players;
        this.world = world;
        this.performance = performance;
        this.network = network;
    }
}
