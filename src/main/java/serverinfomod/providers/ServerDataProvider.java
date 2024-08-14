package serverinfomod.providers;

import necesse.engine.network.server.Server;
import necesse.engine.util.GameUtils;
import serverinfomod.data.*;


public class ServerDataProvider implements IDataProvider {

    private final Server server;

    public ServerDataProvider(Server server) {
        this.server = server;
    }

    @Override
    public String getKey() {
        return "server";
    }

    @Override
    public ServerData getData() {
        return new ServerData(
            new PlayersData(server.getPlayersOnline(), server.getSlots()),
            new WorldData(
                server.world.filePath.getName(),
                server.world.levelManager.getLoadedLevelsNum(),
                server.world.worldEntity.getDay(),
                server.world.worldEntity.getTime() / 1000L
            ),
            new PerformanceData(
                GameUtils.getTimeStringNano(server.serverThread.gameLoop.getTickTimeAverage()),
                server.tickManager().getTPS(),
                server.tickManager().getTotalTicks(),
                server.tickManager().getSkippedTicks()
            ),
            new NetworkData(
                server.packetManager.getAverageIn(),
                server.packetManager.getTotalInPackets(),
                server.packetManager.getTotalIn(),
                server.packetManager.getTotalInPackets(),
                server.packetManager.getAverageOut(),
                server.packetManager.getAverageOutPackets(),
                server.packetManager.getTotalOut(),
                server.packetManager.getAverageOutPackets()
            )
        );
    }
}
