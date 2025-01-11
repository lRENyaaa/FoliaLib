package ltd.rymc.folialib.util.logger;

import ltd.rymc.folialib.util.logger.adapter.JulLoggerAdapter;
import ltd.rymc.folialib.util.logger.adapter.UniversalLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class BukkitLogger {

    @SuppressWarnings("UnstableApiUsage")
    public static UniversalLogger getDefaultLogger(){
        return new JulLoggerAdapter(Bukkit.getLogger());
    }

    public static UniversalLogger getLogger(Plugin plugin){
        return new JulLoggerAdapter(plugin.getLogger());
    }

}
