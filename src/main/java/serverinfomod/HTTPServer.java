package serverinfomod;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import serverinfomod.providers.DataProvidersRegistry;
import serverinfomod.providers.IDataProvider;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {

    private ExecutorService executorService;
    private HttpServer server;

    public void start(int port, DataProvidersRegistry dataRegistry) {
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                server = HttpServer.create(new InetSocketAddress(port), 0);
                server.createContext("/stats", new StatsHandler(dataRegistry));
                server.setExecutor(null);
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void stop() {
        server.stop(0);
        executorService.shutdown();
    }

    static class StatsHandler implements HttpHandler {

        private final DataProvidersRegistry dataRegistry;

        public StatsHandler(DataProvidersRegistry dataRegistry) {
            super();
            this.dataRegistry = dataRegistry;
        }

        private String makeResponse() {
            HashMap<String, Object> result = new HashMap<>();
            for (IDataProvider dataProvider : dataRegistry.getDataProviders()) {
                result.put(dataProvider.getKey(), dataProvider.getData());
            }
            return new Gson().toJson(result);
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = makeResponse();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream stream = exchange.getResponseBody();
            stream.write(response.getBytes());
            stream.close();
        }
    }

}