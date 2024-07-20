# Server Info API Mod for Necesse.

This simple mod provide http handler that's provide information about server like online, map name, TPS and other.

By default, information available in /stats route, on TCP port 61111.

Example response:

```json
{
  "server": {
    "players": {
      "online": 10,
      "slots": 16
    },
    "world": {
      "name": "world.zip",
      "loadedLevels": 0,
      "day": 0,
      "gameTime": 132
    },
    "performance": {
      "tickTime": "14.05 us",
      "TPS": 20,
      "totalTicks": 2758,
      "skippedTicks": 0
    },
    "network": {
      "averageIn": "0 B",
      "averageInPackets": 0,
      "totalIn": "0 B",
      "totalInPackets": 0,
      "averageOut": "0 B",
      "averageOutPackets": 0,
      "totalOut": "0 B",
      "totalOutPackets": 0
    }
  }
}
```