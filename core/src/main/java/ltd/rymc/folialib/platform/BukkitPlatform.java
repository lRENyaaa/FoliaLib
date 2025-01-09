package ltd.rymc.folialib.platform;

import ltd.rymc.folialib.nms.worldmanager.BukkitWorldManager;
import ltd.rymc.folialib.nms.worldmanager.WorldManager;
import ltd.rymc.folialib.scheduler.SchedulerProvider;
import ltd.rymc.folialib.scheduler.bukkit.BukkitSchedulerProvider;
import org.bukkit.plugin.Plugin;

public class BukkitPlatform implements Platform {

    private final WorldManager worldManager = new BukkitWorldManager();

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


}
