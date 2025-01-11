package ltd.rymc.folialib.nms.bukkit;

import ltd.rymc.folialib.nms.region.Region;
import ltd.rymc.folialib.nms.region.RegionManager;
import ltd.rymc.folialib.nms.region.TpsReportLength;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.Collections;
import java.util.List;

public class BukkitRegionManager implements RegionManager {
    private final BukkitRegion region = new BukkitRegion();

    public static double getTps(TpsReportLength length) {
        switch (length) {
            // TODO: We need to implement TPS fetching in Bukkit separately,
            //  Maybe we can refer to the spark implementation.
            case SECONDS_5:
            case SECONDS_15:
            case MINUTE_1:
                return Bukkit.getTPS()[0];
            case MINUTES_5:
                return Bukkit.getTPS()[1];
            case MINTUES_15:
                return Bukkit.getTPS()[2];
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Region> getAllRegions(World world) {
        return Collections.singletonList(region);
    }

    @Override
    public Region getRegionAt(World world, int chunkX, int chunkZ, boolean sync) {
        return region;
    }

    @Override
    public Region getRegionAt(Location location, boolean sync) {
        return region;
    }

    @Override
    public double getGlobalRegionTps(TpsReportLength length) {
        return getTps(length);
    }

    @Override
    public List<Entity> getLoadedEntities(World world) {
        return world.getEntities();
    }

}
