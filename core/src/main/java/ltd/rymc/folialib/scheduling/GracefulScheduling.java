/*
 * MorePaperLib
 * Copyright © 2023 Anand Beh
 *
 * MorePaperLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MorePaperLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MorePaperLib. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Lesser General Public License.
 */

package ltd.rymc.folialib.scheduling;

import ltd.rymc.folialib.FoliaLib;
import ltd.rymc.folialib.platform.Platform;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Contract;

/**
 * Bridges scheduling APIs between Folia and Bukkit. Callers must specify where tasks are run:
 * whether the global region, a certain region, or on an entity scheduler, per Folia mechanics.
 * When running on Bukkit, automatic fallback occurs to the BukkitScheduler.
 *
 */
public class GracefulScheduling {

	private final FoliaLib foliaLib;
	private final Platform platform;

	/**
	 *
	 * @param foliaLib central command
	 * @param platform server platform
	 *
	 */
	public GracefulScheduling(FoliaLib foliaLib, Platform platform) {
		this.foliaLib = foliaLib;
		this.platform = platform;
	}

	/**
	 * Determines whether Folia APIs are being used
	 *
	 * @return true if Folia APIs will be used
	 */
	public boolean isUsingFolia() {
		return platform.isFolia();
	}

	/**
	 * Obtains the asynchronous scheduler, which performs async scheduling appropriately on Folia or Bukkit
	 * using either the Folia AsyncScheduler or Bukkit BukkitScheduler
	 *
	 * @return the asynchronous scheduler
	 */
	@Contract(value = "-> new", pure = true)
	public AsynchronousScheduler asyncScheduler() {
		if (isUsingFolia()) {
			return new GlobalAsyncScheduler(foliaLib.getPlugin());
		}
		return new BukkitSchedulerAsAsynchronousScheduler(foliaLib);
	}

	/**
	 * Obtains a scheduler which posts tasks to the global region on Folia, or the main thread on Bukkit
	 *
	 * @return a scheduler for the global region
	 */
	@Contract(value = "-> new", pure = true)
	public RegionalScheduler globalRegionalScheduler() {
		if (isUsingFolia()) {
			return new GlobalScheduler(foliaLib.getPlugin());
		}
		return new BukkitSchedulerAsRegionalScheduler(foliaLib);
	}

	/**
	 * Obtains a scheduler which posts tasks to a specific region on Folia. <b>Remember that it is inappropriate
	 * to use region specific scheduling for entities</b> <br>
	 * <br>
	 * On Bukkit, this scheduler simply posts to the main thread.
	 *
	 * @param location the location
	 * @return a scheduler for the specific region
	 */
	@Contract(value = "_ -> new", pure = true)
	public RegionalScheduler regionSpecificScheduler(Location location) {
		return regionSpecificScheduler(location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4);
	}

	/**
	 * Obtains a scheduler which posts tasks to a specific region on Folia. <b>Remember that it is inappropriate
	 * to use region specific scheduling for entities</b> <br>
	 * <br>
	 * On Bukkit, this scheduler simply posts to the main thread.
	 *
	 * @param world the world
	 * @param chunkX the chunk X coordinate
	 * @param chunkZ the chunk Z coordinate
	 * @return a scheduler for the specific region
	 */
	@Contract(value = "_, _, _ -> new", pure = true)
	public RegionalScheduler regionSpecificScheduler(World world, int chunkX, int chunkZ) {
		if (isUsingFolia()) {
			return new RegionSpecificScheduler(foliaLib.getPlugin(), world, chunkX, chunkZ);
		}
		return new BukkitSchedulerAsRegionalScheduler(foliaLib);
	}

	/**
	 * Obtains a scheduler which runs tasks for a certain entity. On Bukkit, this scheduler simply posts to
	 * the main thread
	 *
	 * @param entity the entity
	 * @return a scheduler for the entity
	 */
	@Contract(value = "_ -> new", pure = true)
	public AttachedScheduler entitySpecificScheduler(Entity entity) {
		if (isUsingFolia()) {
			return new EntitySpecificScheduler(entity, foliaLib.getPlugin());
		}
		return new BukkitSchedulerAsRegionalScheduler(foliaLib).asAttachedScheduler();
	}

	/**
	 * Whether the current thread is the thread ticking the global region on Folia, or the main thread on Bukkit
	 *
	 * @return true if currently running on the global region thread, or the main thread on Bukkit
	 */
	public boolean isOnGlobalRegionThread() {
		Server server = Bukkit.getServer();
		if (isUsingFolia()) {
			return server.isGlobalTickThread();
		}
		return server.isPrimaryThread();
	}

	/**
	 * Cancels tasks submitted by the owning plugin, where possible. It is not possible to clear tasks
	 * on specific entity/region schedulers when running on Folia: no such API exists.
	 *
	 */
	public void cancelGlobalTasks() {
		if (isUsingFolia()) {
			new GlobalAsyncScheduler(foliaLib.getPlugin()).cancelTasks();
			new GlobalScheduler(foliaLib.getPlugin()).cancelTasks();
			return;
		}
		new BukkitSchedulerAsRegionalScheduler(foliaLib).cancelTasks();
	}

}
