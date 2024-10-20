/*
 * MorePaperLib
 * Copyright © 2024 Anand Beh
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

import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Fallback for older Bukkit versions which do not support Consumer methods
 */
@SuppressWarnings("deprecation")
final class BukkitTaskConsumerToRunnable implements Runnable {

	private final BukkitScheduler scheduler;
	private final CompletableFuture<BukkitTask> taskHolder;
	private final Consumer<ScheduledTask> command;

	BukkitTaskConsumerToRunnable(BukkitScheduler scheduler, CompletableFuture<BukkitTask> taskHolder, Consumer<ScheduledTask> command) {
		this.scheduler = scheduler;
		this.taskHolder = taskHolder;
		this.command = command;
	}

	static void setup(BukkitScheduler scheduler, Consumer<ScheduledTask> command, Function<Runnable, BukkitTask> taskSubmitter) {
		CompletableFuture<BukkitTask> futureTask = new CompletableFuture<>();
		BukkitTask task = null;
		try {
			task = taskSubmitter.apply(new BukkitTaskConsumerToRunnable(scheduler, futureTask, command));
		} finally {
			futureTask.complete(task);
		}
	}

	@Override
	public void run() {
		BukkitTask task = taskHolder.join();
		command.accept(new PaperTask(scheduler, task));
	}

}
