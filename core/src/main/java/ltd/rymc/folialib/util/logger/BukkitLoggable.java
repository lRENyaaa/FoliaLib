package ltd.rymc.folialib.util.logger;

import lombok.Setter;
import ltd.rymc.folialib.util.adapter.UniversalLogger;

@Setter
public class BukkitLoggable {

    protected UniversalLogger logger = BukkitLogger.getDefaultLogger();

}
