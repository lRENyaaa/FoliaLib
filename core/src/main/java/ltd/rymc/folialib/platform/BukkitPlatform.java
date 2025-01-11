package ltd.rymc.folialib.platform;

import ltd.rymc.folialib.nms.bukkit.BukkitRegionManager;
import ltd.rymc.folialib.nms.bukkit.BukkitWorldManager;
import ltd.rymc.folialib.nms.region.RegionManager;
import ltd.rymc.folialib.nms.world.WorldManager;
import ltd.rymc.folialib.scheduler.SchedulerProvider;
import ltd.rymc.folialib.scheduler.bukkit.BukkitSchedulerProvider;
import org.bukkit.plugin.Plugin;

public class BukkitPlatform implements Platform {

    private final WorldManager worldManager = new BukkitWorldManager();
    private final RegionManager regionManager = new BukkitRegionManager();

    @Override
    public boolean isFolia() {
        return false;
    }

    @Override
    public SchedulerProvider scheduling(Plugin plugin) {
        return new BukkitSchedulerProvider(plugin);
    }

    @Override
    public WorldManager worldManager() {
        return worldManager;
    }

    @Override
    public boolean worldManagerAvailability() {
        return true;
    }

    @Override
    public RegionManager regionManager() {
        return regionManager;
    }

    @Override
    public boolean regionManagerAvailability() {
        return true;
    }


}
