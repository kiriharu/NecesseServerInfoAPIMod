package serverinfomod;

import necesse.engine.GameEventListener;
import necesse.engine.GameEvents;
import necesse.engine.events.ServerStartEvent;
import necesse.engine.events.ServerStopEvent;
import necesse.engine.modLoader.annotations.ModEntry;
import serverinfomod.providers.DataProvidersRegistry;
import serverinfomod.providers.ServerDataProvider;


@ModEntry
public class ServerInfoAPIMod {

    private HTTPServer infoApiServer;
    public DataProvidersRegistry dataRegistry;

    public void init() {
        System.out.println("Loading ServerInfoAPI Mod...");
        dataRegistry = new DataProvidersRegistry();
        infoApiServer = new HTTPServer();
        // Get server object
        // Hack from https://github.com/DrFair/BossFightSummary/blob/654b83b4452ea9b81ff5d10c17d806ad9cb1128e/src/main/java/fightsummary/BossFightSummary.java#L35
        GameEvents.addListener(ServerStartEvent.class, new GameEventListener<ServerStartEvent>() {
            @Override
            public void onEvent(ServerStartEvent e) {
                System.out.println(e.server);
                dataRegistry.addDataProvider(new ServerDataProvider(e.server));
                System.out.println("Added ServerDataProvider");
                // TODO: add port to config
                infoApiServer.start(61111, dataRegistry);
                System.out.println("HTTP Info API server running on port 61111");
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
