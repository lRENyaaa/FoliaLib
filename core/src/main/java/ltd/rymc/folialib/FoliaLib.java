package ltd.rymc.folialib;

import ltd.rymc.folialib.nms.region.RegionManager;
import ltd.rymc.folialib.nms.world.WorldManager;
import ltd.rymc.folialib.platform.BukkitPlatform;
import ltd.rymc.folialib.platform.FoliaPlatform;
import ltd.rymc.folialib.platform.Platform;
import ltd.rymc.folialib.scheduler.SchedulerProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FoliaLib {

    private static final String SERVER_VERSION = "v" + Bukkit.getServer().getMinecraftVersion().replace(".", "_");

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
     * Checks if the current platform is Folia.
     *
     * @return {@code true} if running on Folia, {@code false} otherwise.
     */
    public static boolean isFolia() {
        return PLATFORM.isFolia();
    }

    /**
     * Provides access to the scheduling system for the current platform.
     *
     * @param plugin the plugin requesting scheduling capabilities. Must not be {@code null}.
     * @return a {@link SchedulerProvider} for managing tasks.
     */
    public static SchedulerProvider scheduling(Plugin plugin) {
        return PLATFORM.scheduling(plugin);
    }

    /**
     * Retrieves the world manager compatible with both Folia and Bukkit platforms.
     * For Folia, it uses NMS-based implementation. Ensure availability by checking
     * {@link Platform#worldManagerAvailability()} before invoking this method.
     *
     * @return the {@link WorldManager} instance for managing worlds.
     * @throws UnsupportedOperationException if the world manager is unavailable.
     */
    public static WorldManager worldManager() {
        return PLATFORM.worldManager();
    }

    /**
     * Checks whether the world manager is available on the current platform.
     *
     * @return {@code true} if the world manager is available, {@code false} otherwise.
     */
    public static boolean worldManagerAvailability() {
        return PLATFORM.worldManagerAvailability();
    }

    /**
     * Retrieves the region manager for low-level region manipulation.
     * For Folia, it uses an NMS-based implementation. Ensure availability by checking
     * {@link Platform#regionManagerAvailability()} before invoking this method.
     *
     * @return the {@link RegionManager} instance for managing regions.
     * @throws UnsupportedOperationException if the region manager is unavailable.
     */
    public static RegionManager regionManager() {
        return PLATFORM.regionManager();
    }

    /**
     * Checks whether the region manager is available on the current platform.
     *
     * @return {@code true} if the region manager is available, {@code false} otherwise.
     */
    public static boolean regionManagerAvailability() {
        return PLATFORM.regionManagerAvailability();
    }

    /**
     * Retrieves the server's Minecraft version in a formatted string.
     *
     * @return the formatted server version string, e.g., "v1_20_1".
     */
    public static String getServerVersion() {
        return SERVER_VERSION;
    }
}
