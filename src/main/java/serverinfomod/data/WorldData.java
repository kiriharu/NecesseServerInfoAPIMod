package serverinfomod.data;

public class WorldData {
    private final String name;
    private final long loadedLevels;
    private final long day;
    private final long gameTime;

    public WorldData(String name, long loadedLevels, long day, long gameTime) {
        this.name = name;
        this.loadedLevels = loadedLevels;
        this.day = day;
        this.gameTime = gameTime;
    }
}
