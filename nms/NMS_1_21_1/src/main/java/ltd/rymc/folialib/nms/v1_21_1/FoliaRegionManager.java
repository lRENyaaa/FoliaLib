package ltd.rymc.folialib.nms.v1_21_1;

import io.papermc.paper.threadedregions.RegionizedServer;
import io.papermc.paper.threadedregions.ThreadedRegionizer;
import io.papermc.paper.threadedregions.TickData;
import io.papermc.paper.threadedregions.TickRegionScheduler;
import io.papermc.paper.threadedregions.TickRegions;
import ltd.rymc.folialib.nms.region.Region;
import ltd.rymc.folialib.nms.region.RegionManager;
import ltd.rymc.folialib.nms.region.TpsReportLength;
import net.minecraft.server.level.ServerLevel;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FoliaRegionManager implements RegionManager {

    public static double getAverageTps(TickData.TickReportData report) {
        return report.tpsData().segmentAll().average();
    }

    public static Function<TickRegionScheduler.RegionScheduleHandle, TickData.TickReportData> getReportMethod(TpsReportLength length) {
        switch (length) {
            case SECONDS_5:
                return (handle) -> handle.getTickReport5s(System.nanoTime());
            case SECONDS_15:
                return (handle) -> handle.getTickReport15s(System.nanoTime());
            case MINUTE_1:
                return (handle) -> handle.getTickReport1m(System.nanoTime());
            case MINUTES_5:
                return (handle) -> handle.getTickReport5m(System.nanoTime());
            case MINTUES_15:
                return (handle) -> handle.getTickReport15m(System.nanoTime());
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Region> getAllRegions(World world) {
        ServerLevel level = ((CraftWorld) world).getHandle();
        List<Region> regions = new ArrayList<>();
        level.regioniser.computeForAllRegions(region -> regions.add(new FoliaRegion(region)));
        return regions;
    }

    @Override
    public Region getRegionAt(World world, int chunkX, int chunkZ, boolean sync) {
        ServerLevel level = ((CraftWorld) world).getHandle();
        ThreadedRegionizer.ThreadedRegion<TickRegions.TickRegionData, TickRegions.TickRegionSectionData> region;
        if (sync) {
            region = level.regioniser.getRegionAtSynchronised(chunkX, chunkZ);
        } else {
            region = level.regioniser.getRegionAtUnsynchronised(chunkX, chunkZ);
        }
        return new FoliaRegion(region);
    }

    @Override
    public Region getRegionAt(Location location, boolean sync) {
        return getRegionAt(location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4, sync);
    }

    @Override
    public double getGlobalRegionTps(TpsReportLength length) {
        TickRegionScheduler.RegionScheduleHandle handle = RegionizedServer.getGlobalTickData();
        TickData.TickReportData report = FoliaRegionManager.getReportMethod(length).apply(handle);
        return FoliaRegionManager.getAverageTps(report);
    }

    @Override
    public List<Entity> getLoadedEntities(World world) {
        ServerLevel level = ((CraftWorld) world).getHandle();
        List<Entity> entities = new ArrayList<>();
        for (net.minecraft.world.entity.Entity entity : level.getCurrentWorldData().getLoadedEntities()) {
            entities.add(entity.getBukkitEntity());
        }
        return entities;
    }
}
