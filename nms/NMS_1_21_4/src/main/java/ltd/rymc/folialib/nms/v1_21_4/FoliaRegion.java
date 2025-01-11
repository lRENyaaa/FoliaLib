package ltd.rymc.folialib.nms.v1_21_4;

import io.papermc.paper.threadedregions.ThreadedRegionizer;
import io.papermc.paper.threadedregions.TickData;
import io.papermc.paper.threadedregions.TickRegionScheduler;
import io.papermc.paper.threadedregions.TickRegions;
import ltd.rymc.folialib.nms.region.ChunkPosition;
import ltd.rymc.folialib.nms.region.Region;
import ltd.rymc.folialib.nms.region.TpsReportLength;
import net.minecraft.world.level.ChunkPos;


public class FoliaRegion implements Region {

    private final ThreadedRegionizer.ThreadedRegion<TickRegions.TickRegionData, TickRegions.TickRegionSectionData> region;

    public FoliaRegion(ThreadedRegionizer.ThreadedRegion<TickRegions.TickRegionData, TickRegions.TickRegionSectionData> region) {
        this.region = region;
    }

    @SuppressWarnings("unused")
    public ThreadedRegionizer.ThreadedRegion<TickRegions.TickRegionData, TickRegions.TickRegionSectionData> getHandle() {
        return region;
    }

    @Override
    public ChunkPosition getCenterChunkPosition() {
        ChunkPos centerChunk = region.getCenterChunk();
        return new ChunkPosition(region.regioniser.world.getWorld(), centerChunk.x, centerChunk.z);
    }


    @Override
    public double getTps(TpsReportLength length) {
        TickRegionScheduler.RegionScheduleHandle handle = region.getData().getRegionSchedulingHandle();
        TickData.TickReportData report = FoliaRegionManager.getReportMethod(length).apply(handle);
        return FoliaRegionManager.getAverageTps(report);
    }

}
