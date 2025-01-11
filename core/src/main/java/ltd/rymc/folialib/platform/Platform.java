package ltd.rymc.folialib.platform;

import ltd.rymc.folialib.nms.region.RegionManager;
import ltd.rymc.folialib.nms.world.WorldManager;
import ltd.rymc.folialib.scheduler.SchedulerProvider;
import org.bukkit.plugin.Plugin;

public interface Platform {

    /**
     * Checks if the current platform is Folia.
     *
     * @return {@code true} if running on Folia, {@code false} otherwise.
     */
    boolean isFolia();

    /**
     * Provides access to the scheduling system for the current platform.
     *
     * @param plugin the plugin requesting scheduling capabilities. Must not be {@code null}.
     * @return a {@link SchedulerProvider} for managing tasks.
     */
    SchedulerProvider scheduling(Plugin plugin);


    /**
     * Retrieves the world manager compatible with both Folia and Bukkit platforms.
     * For Folia, it uses NMS-based implementation. Ensure availability by checking
     * {@link Platform#worldManagerAvailability()} before invoking this method.
     *
     * @return the {@link WorldManager} instance for managing worlds.
     * @throws UnsupportedOperationException if the world manager is unavailable.
     */
    WorldManager worldManager();

    /**
     * Checks whether the world manager is available on the current platform.
     *
     * @return {@code true} if the world manager is available, {@code false} otherwise.
     */
    boolean worldManagerAvailability();

    /**
     * Retrieves the region manager for low-level region manipulation.
     * For Folia, it uses an NMS-based implementation. Ensure availability by checking
     * {@link Platform#regionManagerAvailability()} before invoking this method.
     *
     * @return the {@link RegionManager} instance for managing regions.
     * @throws UnsupportedOperationException if the region manager is unavailable.
     */
    RegionManager regionManager();

    /**
     * Checks whether the region manager is available on the current platform.
     *
     * @return {@code true} if the region manager is available, {@code false} otherwise.
     */
    boolean regionManagerAvailability();

}
