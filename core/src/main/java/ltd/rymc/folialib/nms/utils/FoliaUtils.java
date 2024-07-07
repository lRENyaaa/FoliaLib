package ltd.rymc.folialib.nms.utils;

import ltd.rymc.folialib.FoliaLib;
import ltd.rymc.folialib.platform.Platform;
import org.bukkit.World;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface FoliaUtils {
    static FoliaUtils getInstance(Platform platform){
        if (!platform.isFolia()){
            return null;
        }

        try {
            String managerPath = FoliaLib.class.getPackage().getName() + ".nms." + FoliaLib.getServerVersion() + ".FoliaNMSUtils";
            return (FoliaUtils) Class.forName(managerPath).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    List<int[]> getAllRegionCenterChunk(World world);

}
