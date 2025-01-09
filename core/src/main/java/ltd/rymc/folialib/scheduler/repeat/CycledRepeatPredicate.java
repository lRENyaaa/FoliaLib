package ltd.rymc.folialib.scheduler.repeat;

import ltd.rymc.folialib.scheduler.ScheduledTask;
import lombok.RequiredArgsConstructor;

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
