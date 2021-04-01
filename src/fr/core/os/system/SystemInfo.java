package fr.core.os.system;

public class SystemInfo {

	public static String getCores() {
        String cores = null;
        cores = "Available processors (cores): "+ Runtime.getRuntime().availableProcessors();
        return cores;
    }

    public static String getFreeMemory() {
        String free_memory = null;
        free_memory = "Free memory (bytes): "+ Runtime.getRuntime().freeMemory();
        return free_memory;
    }

    public static String getMaxMemory() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        String max_memory = null;
        max_memory = "Maximum memory (bytes): "+ (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory);
        return max_memory;
    }

    public static String getTotalMemory() {
        String total_memory = null;
        total_memory = "Total memory (bytes): "+ Runtime.getRuntime().totalMemory();
        return total_memory;
    }
}
