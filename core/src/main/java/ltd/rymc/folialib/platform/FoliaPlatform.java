package ltd.rymc.folialib.platform;

import ltd.rymc.folialib.FoliaLib;
import ltd.rymc.folialib.nms.utils.FoliaUtils;
import ltd.rymc.folialib.scheduling.GracefulScheduling;
import ltd.rymc.folialib.nms.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.concurrent.CompletableFuture;

public class FoliaPlatform implements Platform {

    private final Experimental experimental = FoliaExperimental.create(this);
    private final FoliaLib foliaLib;

    public FoliaPlatform(FoliaLib foliaLib){
        this.foliaLib = foliaLib;
    }

    @Override
    public boolean isFolia() {
        return true;
    }

    @Override
    public GracefulScheduling scheduling() {
        return new GracefulScheduling(foliaLib, this);
    }

    @Override
    public Experimental experimental() {
        return experimental;
    }

    @Override
    public boolean experimentalAvailability() {
        return experimental != null;
    }

    public static class FoliaExperimental implements Experimental {

        private final Platform platform;
        private final WorldManager worldManager;
        private final FoliaUtils foliaUtils;

        private FoliaExperimental(Platform platform) {
            this.platform = platform;
            this.worldManager = WorldManager.getInstance(platform);
            this.foliaUtils = FoliaUtils.getInstance(platform);
            if (!platform.isFolia()) throw new RuntimeException("Platform should be folia");
        }

        private static FoliaExperimental create(Platform platform) {
            try {
                return new FoliaExperimental(platform);
            } catch (Exception e){
                Bukkit.getLogger().warning("FoliaLib has a problem loading the experimental API, the experimental API will be unavailable, this may be caused by version incompatibility.");
                return null;
            }
        }

        @Override
        public WorldManager worldManager() {
            return worldManager;
        }

        @Override
        public void runForAllRegions(Runnable runnable){
            for (World world : Bukkit.getWorlds()) {
                runForAllRegionsInWorld(world,runnable);
            }
        }

        @Override
        public void runForAllRegionsInWorld(World world, Runnable runnable){
            for (int[] chunkPos : foliaUtils.getAllRegionCenterChunk(world)) {
                platform.scheduling().regionSpecificScheduler(world, chunkPos[0], chunkPos[1]).run(runnable);
            }
        }

        public CompletableFuture<Block> getBlockInRegionThread(Location location){
            CompletableFuture<Block> blockFuture = new CompletableFuture<>();
            platform.scheduling().regionSpecificScheduler(location).run(() -> blockFuture.complete(location.getBlock()));
            return blockFuture;
        }
    }

}
