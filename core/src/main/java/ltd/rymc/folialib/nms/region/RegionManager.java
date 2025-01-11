package ltd.rymc.folialib.nms.region;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

public interface RegionManager {

    List<Region> getAllRegions(World world);

    Region getRegionAt(World world, int chunkX, int chunkZ, boolean sync);

    Region getRegionAt(Location location, boolean sync);

    double getGlobalRegionTps(TpsReportLength length);

    @ApiStatus.Internal
    List<Entity> getLoadedEntities(World world);

}
