package ltd.rymc.folialib.scheduler.repeat;

import ltd.rymc.folialib.scheduler.ScheduledTask;

public class EmptyRepeatPredicate implements RepeatPredicate<Object> {

    @Override
    public boolean shouldContinue(ScheduledTask<?> task) {
        return true;
    }

    @Override
    public Object getDefaultValue() {
        throw new UnsupportedOperationException("EmptyRepeatPredicate does not have a default value");
    }
}
