package ltd.rymc.folialib.platform;

import ltd.rymc.folialib.FoliaLib;
import ltd.rymc.folialib.nms.worldmanager.WorldManager;
import ltd.rymc.folialib.scheduling.GracefulScheduling;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class BukkitPlatform implements Platform {

    private final Experimental experimental = BukkitExperimental.create(this);
    private final FoliaLib foliaLib;

    public BukkitPlatform(FoliaLib foliaLib){
        this.foliaLib = foliaLib;
    }

    @Override
    public boolean isFolia() {
        return false;
    }

    @Override
    public GracefulScheduling scheduling() {
        return new GracefulScheduling(foliaLib, this);
    }

    @Override
    public Experimental experimental() {
        return Objects.requireNonNull(experimental);
    }

    @Override
    public boolean experimentalAvailability() {
        return true;
    }

    public static class BukkitExperimental implements Experimental {

        private final Platform platform;
        private final WorldManager worldManager;

        private BukkitExperimental(Platform platform) {
            this.platform = platform;
            this.worldManager = WorldManager.getInstance(platform);
        }

        private static BukkitExperimental create(Platform platform) {
            return new BukkitExperimental(platform);
        }


        @Override
        public WorldManager worldManager() {
            return worldManager;
        }

        @Override
        public void runForAllRegions(Runnable runnable){
            platform.scheduling().globalRegionalScheduler().run(runnable);
        }

        @Override
        public void runForAllRegionsInWorld(World world, Runnable runnable){
            platform.scheduling().globalRegionalScheduler().run(runnable);
        }

        @Override
        public CompletableFuture<Block> getBlockInRegionThread(Location location){
            return CompletableFuture.completedFuture(location.getBlock());
        }
    }
}
