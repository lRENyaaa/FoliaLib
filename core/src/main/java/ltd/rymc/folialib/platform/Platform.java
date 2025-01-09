package ltd.rymc.folialib.platform;

import ltd.rymc.folialib.scheduler.SchedulerProvider;
import ltd.rymc.folialib.nms.worldmanager.WorldManager;
import org.bukkit.plugin.Plugin;

public interface Platform {

    /**
     * Check if the platform is folia
     *
     * @return result
     */
    boolean isFolia();

    /**
     * Accesses scheduling
     *
     * @return the scheduling wrapper
     */
    SchedulerProvider scheduling(Plugin plugin);


    /**
     * Get world manager that is available for both Folia and Bukkit.
     * Implemented using NMS in Folia, and availability must be checked with {@link Platform#worldManagerAvailability()} before use.
     *
     * @return the instance of world manager
     */
    WorldManager worldManager();

    /**
     * Check if world manager are available
     *
     * @return the state of world manager
     */
    boolean worldManagerAvailability();



}
