package ltd.rymc.folialib.platform;

import ltd.rymc.folialib.scheduling.GracefulScheduling;
import ltd.rymc.folialib.nms.worldmanager.WorldManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.concurrent.CompletableFuture;

public interface Platform {

    /**
     * Check if the platform is folia
     *
     * @return result
     */
    boolean isFolia();

    /**
     * **FROM MORE PAPER LIB**
     * Accesses scheduling
     *
     * @return the scheduling wrapper
     */
    GracefulScheduling scheduling();


    /**
     * Some experimental API
     * It can implement some APIs that don't exist in Folia, but its not stable, and probably never will be!
     * Before using it you need to check its availability using {@link Platform#experimentalAvailability()}
     *
     * @return experimental API
     */
    Experimental experimental();

    /**
     * Check if experimental APIs are available
     *
     * @return the state of experimental API
     */
    boolean experimentalAvailability();


    interface Experimental {

        /**
         * A world manager available in both Folia and Bukkit.
         * Loading or unloading worlds in Folia is possible, but requires heavy use of NMS
         *
         * @return the world manager
         */
        WorldManager worldManager();

        /**
         *
         * A method that lets you iterate through all regions to run
         * It's only executed once in Bukkit.
         *
         */
        void runForAllRegions(Runnable runnable);

        /**
         *
         * A method that lets you iterate through all regions to run
         * It's only executed once in Bukkit.
         * NOTE: On Bukkit since there is only one thread, it will still act on the whole world
         *
         */
        void runForAllRegionsInWorld(World world, Runnable runnable);

        /**
         *
         * A method to easily get block in region thread
         * DO NOT USE {@link CompletableFuture#get()} OR {@link CompletableFuture#join()} HERE!
         *
         */
        CompletableFuture<Block> getBlockInRegionThread(Location location);


    }

}
