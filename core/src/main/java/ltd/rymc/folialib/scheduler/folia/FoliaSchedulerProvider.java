/*
 * MIT License
 *
 * Copyright (c) 2022 Fairy Project
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ltd.rymc.folialib.scheduler.folia;

import lombok.RequiredArgsConstructor;
import ltd.rymc.folialib.nms.region.ChunkPosition;
import ltd.rymc.folialib.nms.region.Region;
import ltd.rymc.folialib.scheduler.Scheduler;
import ltd.rymc.folialib.scheduler.SchedulerProvider;
import ltd.rymc.folialib.scheduler.folia.schedulers.FoliaAsyncScheduler;
import ltd.rymc.folialib.scheduler.folia.schedulers.FoliaEntityScheduler;
import ltd.rymc.folialib.scheduler.folia.schedulers.FoliaGlobalRegionScheduler;
import ltd.rymc.folialib.scheduler.folia.schedulers.FoliaRegionScheduler;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

@RequiredArgsConstructor
public class FoliaSchedulerProvider implements SchedulerProvider {

    private final Plugin plugin;

    @Override
    public Scheduler getGlobalScheduler() {
        return new FoliaGlobalRegionScheduler(plugin);
    }

    @Override
    public Scheduler getAsyncScheduler() {
        return new FoliaAsyncScheduler(plugin);
    }

    @Override
    public Scheduler getEntityScheduler(Entity entity) {
        return new FoliaEntityScheduler(entity, plugin);
    }

    @Override
    public Scheduler getLocationScheduler(Location location) {
        return new FoliaRegionScheduler(plugin, location);
    }

    @Override
    public Scheduler getChunkScheduler(World world, int chunkX, int chunkZ) {
        return new FoliaRegionScheduler(plugin, world, chunkX, chunkZ);
    }

    @Override
    public Scheduler getRegionScheduler(Region<?> region) {
        ChunkPosition chunkPosition = region.getCenterChunkPosition();
        return new FoliaRegionScheduler(plugin, chunkPosition.getWorld(), chunkPosition.getX(), chunkPosition.getCenterBlockZ());
    }
}
