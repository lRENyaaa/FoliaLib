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

package ltd.rymc.folialib.scheduler.bukkit;

import ltd.rymc.folialib.nms.region.Region;
import ltd.rymc.folialib.scheduler.Scheduler;
import ltd.rymc.folialib.scheduler.SchedulerProvider;
import ltd.rymc.folialib.scheduler.bukkit.schedulers.BukkitAsyncScheduler;
import ltd.rymc.folialib.scheduler.bukkit.schedulers.BukkitMainScheduler;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

public class BukkitSchedulerProvider implements SchedulerProvider {

    private final BukkitMainScheduler bukkitMainScheduler;
    private final BukkitAsyncScheduler bukkitAsyncScheduler;

    public BukkitSchedulerProvider(Plugin plugin) {
        this.bukkitMainScheduler = new BukkitMainScheduler(plugin);
        this.bukkitAsyncScheduler = new BukkitAsyncScheduler(plugin);
    }

    @Override
    public Scheduler getGlobalScheduler() {
        return this.bukkitMainScheduler;
    }

    @Override
    public Scheduler getAsyncScheduler() {
        return this.bukkitAsyncScheduler;
    }

    @Override
    public Scheduler getEntityScheduler(Entity entity) {
        return this.bukkitMainScheduler;
    }

    @Override
    public Scheduler getLocationScheduler(Location position) {
        return this.bukkitMainScheduler;
    }

    @Override
    public Scheduler getChunkScheduler(World world, int chunkX, int chunkZ) {
        return this.bukkitMainScheduler;
    }

    @Override
    public Scheduler getRegionScheduler(Region region) {
        return this.bukkitMainScheduler;
    }

}
