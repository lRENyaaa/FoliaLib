package ltd.rymc.folialib;

import ltd.rymc.folialib.platform.BukkitPlatform;
import ltd.rymc.folialib.platform.FoliaPlatform;
import ltd.rymc.folialib.platform.Platform;
import ltd.rymc.folialib.scheduling.GracefulScheduling;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FoliaLib {

    private static final String SERVER_VERSION = "v" + Bukkit.getServer().getMinecraftVersion().replace(".","_");

    private final Platform platform = initialize();
    private final Plugin plugin;

    public FoliaLib(Plugin plugin){
        this.plugin = plugin;
    }

    private Platform initialize() {
        try {
            Bukkit.class.getMethod("getRegionScheduler");
            return new FoliaPlatform(this);
        } catch (NoSuchMethodException e) {
            return new BukkitPlatform(this);
        }
    }

    /**
     * Check if the platform is folia
     *
     * @return result
     */
    public boolean isFolia(){
        return platform.isFolia();
    }


    /**
     * **FROM MORE PAPER LIB**
     * This method is used by MorePaperLib to detect the presence of certain features. It may
     * be used by callers as a convenience. It may also be overridden to force the usage of specific
     * API, but please note that the calling patterns used by MorePaperLib are strictly unspecified.
     *
     * @param clazz the class in which to check
     * @param methodName the name of the method
     * @param parameterTypes the raw parameter types
     * @return true if it exists
     */
    public boolean methodExists(Class<?> clazz, String methodName, Class<?>...parameterTypes) {
        try {
            clazz.getMethod(methodName, parameterTypes);
            return true;
        } catch (NoSuchMethodException ex) {
            return false;
        }
    }

    /**
     * **FROM MORE PAPER LIB**
     * Accesses scheduling
     *
     * @return the scheduling wrapper
     */
    public GracefulScheduling scheduling(){
        return platform.scheduling();
    }


    /**
     * Some experimental API
     * It can implement some APIs that don't exist in Folia, but its not stable, and probably never will be!
     * Before using it you need to check its availability using {@link Platform#experimentalAvailability()}
     *
     * @return experimental API
     */
    public Platform.Experimental experimental() {
        return platform.experimental();
    }

    /**
     * Check if experimental APIs are available
     *
     * @return the state of experimental API
     */
    public boolean experimentalAvailability() {
        return platform.experimentalAvailability();
    }

    /**
     * Returns the plugin associated with this
     *
     * @return the plugin
     */
    public Plugin getPlugin() {
        return plugin;
    }


    public static String getServerVersion(){
        return SERVER_VERSION;
    }


}
