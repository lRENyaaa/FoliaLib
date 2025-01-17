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

package ltd.rymc.folialib.scheduler.folia.schedulers;

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import ltd.rymc.folialib.scheduler.MillisBasedScheduler;
import ltd.rymc.folialib.scheduler.ScheduledTask;
import ltd.rymc.folialib.scheduler.folia.FoliaRepeatedScheduledTask;
import ltd.rymc.folialib.scheduler.folia.wrapper.WrapperScheduledTask;
import ltd.rymc.folialib.scheduler.repeat.RepeatPredicate;
import ltd.rymc.folialib.scheduler.response.TaskResponse;
import ltd.rymc.folialib.util.logger.BukkitLogger;
import ltd.rymc.folialib.util.logger.adapter.UniversalLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FoliaAsyncScheduler extends FoliaAbstractScheduler implements MillisBasedScheduler {

    private final AsyncScheduler scheduler;
    private final Plugin bukkitPlugin;

    public FoliaAsyncScheduler(Plugin bukkitPlugin) {
        this.bukkitPlugin = bukkitPlugin;
        this.scheduler = Bukkit.getAsyncScheduler();
    }

    @Override
    public boolean isCurrentThread() {
        return Thread.currentThread().getName().contains("Folia Async Scheduler Thread");
    }

    @Override
    public <R> ScheduledTask<R> schedule(Callable<R> callable) {
        return doSchedule(callable, task -> scheduler.runNow(bukkitPlugin, task));
    }

    @Override
    public <R> ScheduledTask<R> schedule(Callable<R> callable, Duration delay) {
        return doSchedule(callable, task -> scheduler.runDelayed(bukkitPlugin, task, delay.toNanos(), TimeUnit.NANOSECONDS));
    }

    @Override
    public ScheduledTask<?> scheduleAtFixedRate(Runnable runnable, long delayTicks, long intervalTicks) {
        return scheduleAtFixedRate(runnable, Duration.ofMillis(delayTicks * 50), Duration.ofMillis(intervalTicks * 50));
    }

    @Override
    public ScheduledTask<?> scheduleAtFixedRate(Runnable runnable, Duration duration, Duration interval) {
        return scheduleAtFixedRate(() -> {
            runnable.run();
            return TaskResponse.continueTask();
        }, duration, interval, RepeatPredicate.empty());
    }

    @Override
    public ScheduledTask<?> scheduleAtFixedRate(Runnable runnable, long delayTicks, long intervalTicks, RepeatPredicate<?> predicate) {
        return scheduleAtFixedRate(() -> {
            runnable.run();
            return TaskResponse.continueTask();
        }, delayTicks, intervalTicks, predicate);
    }

    @Override
    public <R> ScheduledTask<R> scheduleAtFixedRate(Callable<TaskResponse<R>> callback, Duration delayTicks, Duration intervalTicks, RepeatPredicate<R> repeatPredicate) {
        FoliaRepeatedScheduledTask<R> task = new FoliaRepeatedScheduledTask<>(callback, repeatPredicate);
        task.setLogger(getLogger());
        io.papermc.paper.threadedregions.scheduler.ScheduledTask rawScheduledTask = scheduler.runAtFixedRate(bukkitPlugin, task, delayTicks.toNanos(), intervalTicks.toNanos(), TimeUnit.NANOSECONDS);
        task.setScheduledTask(WrapperScheduledTask.of(rawScheduledTask));

        return task;
    }

    @Override
    public ScheduledTask<?> scheduleAtFixedRate(Runnable runnable, Duration duration, Duration interval, RepeatPredicate<?> predicate) {
        return scheduleAtFixedRate(() -> {
            runnable.run();
            return TaskResponse.continueTask();
        }, duration, interval, predicate);
    }

    @Override
    public <R> ScheduledTask<R> scheduleAtFixedRate(Callable<TaskResponse<R>> callback, long delayTicks, long intervalTicks) {
        return scheduleAtFixedRate(callback, Duration.ofMillis(delayTicks * 50), Duration.ofMillis(intervalTicks * 50));
    }

    @Override
    public <R> ScheduledTask<R> scheduleAtFixedRate(Callable<TaskResponse<R>> callback, Duration duration, Duration interval) {
        return scheduleAtFixedRate(callback, duration, interval, RepeatPredicate.empty());
    }

    @Override
    public <R> ScheduledTask<R> scheduleAtFixedRate(Callable<TaskResponse<R>> callback, long delayTicks, long intervalTicks, RepeatPredicate<R> predicate) {
        return scheduleAtFixedRate(callback, Duration.ofMillis(delayTicks * 50), Duration.ofMillis(intervalTicks * 50), predicate);
    }

    @Override
    public ScheduledTask<?> schedule(Runnable runnable, long delayTicks) {
        return schedule(runnable, Duration.ofMillis(delayTicks * 50));
    }

    @Override
    protected UniversalLogger getLogger() {
        return BukkitLogger.getLogger(bukkitPlugin);
    }
}
