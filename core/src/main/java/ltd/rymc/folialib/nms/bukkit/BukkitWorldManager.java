package ltd.rymc.folialib.nms.bukkit;

import ltd.rymc.folialib.nms.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BukkitWorldManager implements WorldManager {
    @Override
    public boolean unloadWorld(@NotNull World world, boolean save) {
        return Bukkit.getServer().unloadWorld(world, save);
    }

    @Override
    public boolean unloadWorld(@NotNull String name, boolean save) {
        return Bukkit.getServer().unloadWorld(name, save);
    }

    @Override
    public @Nullable World createWorld(@NotNull WorldCreator creator) {
        return Bukkit.createWorld(creator);
    }

}
