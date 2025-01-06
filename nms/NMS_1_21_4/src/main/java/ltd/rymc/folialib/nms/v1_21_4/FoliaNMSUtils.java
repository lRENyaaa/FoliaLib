package ltd.rymc.folialib.nms.v1_21_4;

import ltd.rymc.folialib.nms.utils.FoliaUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;

import java.util.ArrayList;
import java.util.List;

public class FoliaNMSUtils implements FoliaUtils {

    @Override
    public List<int[]> getAllRegionCenterChunk(World world) {
        ArrayList<int[]> regions = new ArrayList<>();
        ServerLevel level = ((CraftWorld) world).getHandle();
        level.regioniser.computeForAllRegions(region -> {
            ChunkPos centerChunk = region.getCenterChunk();
            regions.add(new int[]{centerChunk.x, centerChunk.z});
        });
        return regions;
    }
}
