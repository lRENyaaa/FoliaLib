package ltd.rymc.folialib.nms.region;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.World;

@RequiredArgsConstructor
@Getter
public class ChunkPosition {

    private final World world;
    private final int x;
    private final int z;


    public Location getCenterLocation() {
        return new Location(world, getCenterBlockX(), 0, getCenterBlockZ());
    }

    public int getCenterBlockX() {
        return x << 4;
    }

    public int getCenterBlockZ() {
        return z << 4;
    }

}
