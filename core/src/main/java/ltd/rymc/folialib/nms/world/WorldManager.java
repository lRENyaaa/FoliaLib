package ltd.rymc.folialib.nms.world;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface for managing worlds in server environments.
 * Designed to support both Bukkit and Folia servers.
 */
public interface WorldManager {

    /**
     * Unloads a given world from the server.
     *
     * @param world the world to be unloaded. Must not be {@code null}.
     * @param save  whether to save the world's current state to disk before unloading.
     * @return {@code true} if the world was successfully unloaded, {@code false} otherwise.
     */
    boolean unloadWorld(@NotNull World world, boolean save);

    /**
     * Unloads a world by its name from the server.
     *
     * @param name the name of the world to be unloaded. Must not be {@code null}.
     * @param save whether to save the world's current state to disk before unloading.
     * @return {@code true} if the world was successfully unloaded, {@code false} otherwise.
     */
    boolean unloadWorld(@NotNull String name, boolean save);

    /**
     * Creates a new world on the server based on the specified {@link WorldCreator}.
     *
     * @param creator the {@link WorldCreator} containing the parameters for the world creation. Must not be {@code null}.
     * @return the newly created {@link World}, or {@code null} if the creation failed.
     */
    @Nullable
    World createWorld(@NotNull WorldCreator creator);
}
