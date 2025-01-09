package ltd.rymc.folialib;

import ltd.rymc.folialib.nms.worldmanager.WorldManager;
import ltd.rymc.folialib.platform.BukkitPlatform;
import ltd.rymc.folialib.platform.FoliaPlatform;
import ltd.rymc.folialib.platform.Platform;
import ltd.rymc.folialib.scheduler.SchedulerProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FoliaLib {

    private static final String SERVER_VERSION = "v" + Bukkit.getServer().getMinecraftVersion().replace(".","_");

    private static final Platform PLATFORM = initialize();

    private static Platform initialize() {
        try {
            Bukkit.class.getMethod("getRegionScheduler");
            return new FoliaPlatform();
        } catch (NoSuchMethodException e) {
            return new BukkitPlatform();
        }
    }

    /**
     * Check if the platform is folia
     *
     * @return result
     */
    public static boolean isFolia(){
        return PLATFORM.isFolia();
    }

    /**
     * Accesses scheduling
     *
     * @return the scheduling wrapper
     */
    public static SchedulerProvider scheduling(Plugin plugin){
        return PLATFORM.scheduling(plugin);
    }


    /**
     * Get world manager that is available for both Folia and Bukkit.
     * Implemented using NMS in Folia, and availability must be checked with {@link Platform#worldManagerAvailability()} before use.
     *
     * @return the instance of world manager
     */
    public static WorldManager worldManager(){
        return PLATFORM.worldManager();
    }

    /**
     * Check if world manager are available
     *
     * @return the state of world manager
     */
    public static boolean worldManagerAvailability(){
        return PLATFORM.worldManagerAvailability();
    }

    public static String getServerVersion(){
        return SERVER_VERSION;
    }


}
