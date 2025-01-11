package ltd.rymc.folialib.nms.bukkit;

import ltd.rymc.folialib.nms.region.ChunkPosition;
import ltd.rymc.folialib.nms.region.Region;
import ltd.rymc.folialib.nms.region.TpsReportLength;
import org.bukkit.Bukkit;

public class BukkitRegion implements Region {

    @Override
    public ChunkPosition getCenterChunkPosition() {
        return new ChunkPosition(Bukkit.getWorlds().getFirst(), 0, 0);
    }

    @Override
    public double getTps(TpsReportLength length) {
        return BukkitRegionManager.getTps(length);
    }

}
