package ltd.rymc.folialib.nms.region;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

/**
 * Interface for managing regions in a server environment.
 * Provides access to region information and server TPS metrics for different time intervals.
 * Compatible with both Bukkit and Folia platforms, with certain behavior differences noted.
 */
public interface RegionManager {

    /**
     * Retrieves all regions in the specified world.
     * Each region is represented by a wrapped {@link Region} interface.
     * <p>Note: On Bukkit, this method always returns a single fixed region representing the entire world.</p>
     *
     * @param world the world for which to retrieve regions. Must not be {@code null}.
     * @return a list of wrapped {@link Region} objects for the specified world.
     */
    List<Region<?>> getAllRegions(World world);

    /**
     * Retrieves the wrapped region located at the specified chunk coordinates in the given world.
     * <p>Note: On Bukkit, this method always returns a single fixed region representing the entire world.</p>
     *
     * @param world  the world containing the region. Must not be {@code null}.
     * @param chunkX the x-coordinate of the chunk.
     * @param chunkZ the z-coordinate of the chunk.
     * @param sync   whether the operation should be synchronous.
     * @return the wrapped {@link Region} at the specified chunk coordinates.
     */
    Region<?> getRegionAt(World world, int chunkX, int chunkZ, boolean sync);

    /**
     * Retrieves the wrapped region at the specified location in the given world.
     * <p>Note: On Bukkit, this method always returns a single fixed region representing the entire world.</p>
     *
     * @param location the {@link Location} for which to retrieve the region. Must not be {@code null}.
     * @param sync     whether the operation should be synchronous.
     * @return the wrapped {@link Region} at the specified location.
     */
    Region<?> getRegionAt(Location location, boolean sync);

    /**
     * Retrieves the global TPS (ticks per second) for regions over a specified time interval.
     * <p>Note: The {@link TpsReportLength} values {@code SECONDS_5} and {@code SECONDS_15} are not valid on Bukkit
     * and will default to returning the value for {@link TpsReportLength#MINUTE_1}.</p>
     *
     * @param length the length of the reporting interval, specified as a {@link TpsReportLength} enum value.
     * @return the global region TPS as a double value.
     */
    double getGlobalRegionTps(TpsReportLength length);

    /**
     * Retrieves all currently loaded entities in the specified world.
     * <p>This is an internal method and should not be used by external APIs.</p>
     *
     * @param world the world for which to retrieve loaded entities. Must not be {@code null}.
     * @return a list of loaded {@link Entity} objects in the specified world.
     */
    @ApiStatus.Internal
    List<Entity> getLoadedEntities(World world);

}
