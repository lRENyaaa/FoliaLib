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

package ltd.rymc.folialib.scheduler.bukkit.schedulers;

import ltd.rymc.folialib.scheduler.ScheduledTask;
import ltd.rymc.folialib.scheduler.TickBasedScheduler;
import ltd.rymc.folialib.scheduler.bukkit.BukkitScheduledTask;
import ltd.rymc.folialib.scheduler.repeat.RepeatPredicate;
import ltd.rymc.folialib.scheduler.response.TaskResponse;
import ltd.rymc.folialib.util.logger.adapter.UniversalLogger;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public abstract class BukkitAbstractScheduler implements TickBasedScheduler {

    protected <R> ScheduledTask<R> doSchedule(Callable<R> callable, Function<Runnable, BukkitTask> schedule) {
        CompletableFuture<R> future = new CompletableFuture<>();
        return new BukkitScheduledTask<>(schedule.apply(() -> {
            if (future.isDone())
                return;

            try {
                future.complete(callable.call());
            } catch (Throwable throwable) {
                getLogger().error("An error occurred while executing a scheduled task", throwable);

                future.completeExceptionally(throwable);
            }
        }), future);
    }

    @Override
    public ScheduledTask<?> schedule(Runnable runnable) {
        return schedule(() -> {
            runnable.run();
            return null;
        });
    }

    @Override
    public ScheduledTask<?> schedule(Runnable runnable, long delayTicks) {
        return schedule(() -> {
            runnable.run();
            return null;
        }, delayTicks);
    }

    @Override
    public ScheduledTask<?> scheduleAtFixedRate(Runnable runnable, long delayTicks, long intervalTicks, RepeatPredicate<?> predicate) {
        return scheduleAtFixedRate(() -> {
            runnable.run();
            return TaskResponse.continueTask();
        }, delayTicks, intervalTicks, predicate);
    }

    abstract UniversalLogger getLogger();

}
