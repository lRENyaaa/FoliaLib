package ltd.rymc.folialib.nms.worldmanager;

import ltd.rymc.folialib.FoliaLib;
import ltd.rymc.folialib.nms.VersionMap;
import ltd.rymc.folialib.platform.Platform;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;

public interface WorldManager {

    static WorldManager getInstance(Platform platform){
        if (!platform.isFolia()){
            return new BukkitWorldManager();
        }

        try {
            String managerPath = FoliaLib.class.getPackage().getName() + ".nms." + VersionMap.mapVersion(FoliaLib.getServerVersion()) + ".FoliaWorldManager";
            return (WorldManager) Class.forName(managerPath).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    boolean unloadWorld(@NotNull World world, boolean save);

    boolean unloadWorld(@NotNull String name, boolean save);

    @Nullable
    World createWorld(@NotNull WorldCreator creator);
}
