package fr.core.os;

public enum CoreVersions {

	DESERT(1.0, "Desert", 2021, "/resources/versions/desert.jpg"),
    VOLCANO(2.0, "Volcano", 2021, "/resources/versions/volcano.jpg"),
    CLIFF(3.0, "Cliff", 2021, "/resources/versions/cliff.jpg"),
    SWAMP(4.0, "Swamp", 2021, "/resources/versions/swamp.jpg");

    private final double version;
    private final String name;
    private final long date;
    private final String path;

    CoreVersions(double version, String name, long date, String path) {
        this.version = version;
        this.name = name;
        this.date = date;
        this.path = path;
    }

    public double getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public long getDate() {
        return date;
    }

    public String getPath() {
        return path;
    }

}
