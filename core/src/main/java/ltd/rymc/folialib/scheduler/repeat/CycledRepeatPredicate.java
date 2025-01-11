package ltd.rymc.folialib.scheduler.repeat;

import lombok.RequiredArgsConstructor;
import ltd.rymc.folialib.scheduler.ScheduledTask;

@RequiredArgsConstructor
public class CycledRepeatPredicate<T> implements RepeatPredicate<T> {

    private final int cycles;
    private final T defaultValue;
    private int currentCycle = 0;

    @Override
    public boolean shouldContinue(ScheduledTask<?> task) {
        return ++currentCycle < cycles;
    }

    @Override
    public T getDefaultValue() {
        return defaultValue;
    }

}
