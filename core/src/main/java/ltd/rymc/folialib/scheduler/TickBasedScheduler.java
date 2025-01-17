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

package ltd.rymc.folialib.scheduler;

import ltd.rymc.folialib.scheduler.repeat.RepeatPredicate;
import ltd.rymc.folialib.scheduler.response.TaskResponse;

import java.time.Duration;
import java.util.concurrent.Callable;

public interface TickBasedScheduler extends Scheduler {

    default ScheduledTask<?> schedule(Runnable runnable, Duration duration) {
        return schedule(runnable, duration.toMillis() / MILLISECONDS_PER_TICK);
    }

    default ScheduledTask<?> scheduleAtFixedRate(Runnable runnable, Duration duration, Duration interval) {
        return scheduleAtFixedRate(runnable, duration.toMillis() / MILLISECONDS_PER_TICK, interval.toMillis() / MILLISECONDS_PER_TICK);
    }

    default ScheduledTask<?> scheduleAtFixedRate(Runnable runnable, Duration duration, Duration interval, RepeatPredicate<?> predicate) {
        return scheduleAtFixedRate(runnable, duration.toMillis() / MILLISECONDS_PER_TICK, interval.toMillis() / MILLISECONDS_PER_TICK, predicate);
    }

    default <R> ScheduledTask<R> schedule(Callable<R> callable, Duration duration) {
        return schedule(callable, duration.toMillis() / MILLISECONDS_PER_TICK);
    }

    default <R> ScheduledTask<R> scheduleAtFixedRate(Callable<TaskResponse<R>> callback, Duration duration, Duration interval) {
        return scheduleAtFixedRate(callback, duration.toMillis() / MILLISECONDS_PER_TICK, interval.toMillis() / MILLISECONDS_PER_TICK);
    }

    default <R> ScheduledTask<R> scheduleAtFixedRate(Callable<TaskResponse<R>> callback, Duration duration, Duration interval, RepeatPredicate<R> predicate) {
        return scheduleAtFixedRate(callback, duration.toMillis() / MILLISECONDS_PER_TICK, interval.toMillis() / MILLISECONDS_PER_TICK, predicate);
    }

}
