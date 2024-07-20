package serverinfomod.providers;

import java.util.ArrayList;

public class DataProvidersRegistry {

    private final ArrayList<IDataProvider> dataProviders;

    public DataProvidersRegistry() {
        dataProviders = new ArrayList<>();
    }

    public void addDataProvider(IDataProvider data) {
        dataProviders.add(data);
    }

    public ArrayList<IDataProvider> getDataProviders() {
        return dataProviders;
    }
}
