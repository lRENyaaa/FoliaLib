package ltd.rymc.folialib.platform;

import ltd.rymc.folialib.FoliaLib;
import ltd.rymc.folialib.nms.VersionMap;
import ltd.rymc.folialib.nms.region.RegionManager;
import ltd.rymc.folialib.scheduler.SchedulerProvider;
import ltd.rymc.folialib.nms.world.WorldManager;
import ltd.rymc.folialib.scheduler.folia.FoliaSchedulerProvider;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;

public class FoliaPlatform implements Platform {

    private final String nmsPath =
            FoliaLib.class.getPackage().getName() +
            ".nms." +
            VersionMap.mapVersion(FoliaLib.getServerVersion());

    private final WorldManager worldManager = getWorldManager();
    private final RegionManager regionManager = getRegionManager();

    private WorldManager getWorldManager(){
        try {
            return (WorldManager) Class.forName(nmsPath + ".FoliaWorldManager").getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | ClassNotFoundException e) {
            return null;
        }
    }

    private RegionManager getRegionManager(){
        try {
            return (RegionManager) Class.forName(nmsPath + ".FoliaRegionManager").getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean isFolia() {
        return true;
    }

    @Override
    public SchedulerProvider scheduling(Plugin plugin) {
        return new FoliaSchedulerProvider(plugin);
    }

    @Override
    public WorldManager worldManager() {
        return worldManager;
    }

    @Override
    public boolean worldManagerAvailability() {
        return worldManager != null;
    }

    @Override
    public RegionManager regionManager() {
        return regionManager;
    }

    @Override
    public boolean regionManagerAvailability() {
        return regionManager != null;
    }


}
