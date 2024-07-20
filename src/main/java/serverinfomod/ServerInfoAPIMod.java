package serverinfomod;

import necesse.engine.GameEventListener;
import necesse.engine.GameEvents;
import necesse.engine.events.ServerStartEvent;
import necesse.engine.events.ServerStopEvent;
import necesse.engine.modLoader.ModSettings;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;
import serverinfomod.providers.DataProvidersRegistry;
import serverinfomod.providers.ServerDataProvider;


@ModEntry
public class ServerInfoAPIMod {

    private HTTPServer infoApiServer;
    public DataProvidersRegistry dataRegistry;
    public int port = 61111;

    public ModSettings initSettings() {
        return new ModSettings() {
            @Override
            public void addSaveData(SaveData saveData) {
                saveData.addInt("port", port);
            }

            @Override
            public void applyLoadData(LoadData loadData) {
                port = loadData.getInt("port", port);
                if ((port >= 65565) | (port <= 0)) {
                    System.out.println("[INFO API] Invalid port! Port should be between 0 and 65556. Fallback to default 61111");
                    port = 61111;
                }
            }
        };
    }

    public void init() {
        System.out.println("[INFO API] Loading mod...");
        dataRegistry = new DataProvidersRegistry();
        infoApiServer = new HTTPServer();
        // Get server object
        // Hack from https://github.com/DrFair/BossFightSummary/blob/654b83b4452ea9b81ff5d10c17d806ad9cb1128e/src/main/java/fightsummary/BossFightSummary.java#L35
        GameEvents.addListener(ServerStartEvent.class, new GameEventListener<ServerStartEvent>() {
            @Override
            public void onEvent(ServerStartEvent e) {
                dataRegistry.addDataProvider(new ServerDataProvider(e.server));
                System.out.println("[INFO API] Added ServerDataProvider");
                infoApiServer.start(port, dataRegistry);
                System.out.println("[INFO API] Server running on port " + port + ".");
            }
        });
        GameEvents.addListener(ServerStopEvent.class, new GameEventListener<ServerStopEvent>() {
            @Override
            public void onEvent(ServerStopEvent e) {
                infoApiServer.stop();
            }
        });
    }
}
