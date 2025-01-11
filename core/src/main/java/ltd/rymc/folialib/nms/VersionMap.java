package ltd.rymc.folialib.nms;

import java.util.HashMap;
import java.util.Map;

public final class VersionMap {

    private static final Map<String, String> VERSION_MAP = new HashMap<>();

    static {
        VERSION_MAP.put("v1_21_3", "v1_21_4");
        VERSION_MAP.put("v1_21_2", "v1_21_1");
        VERSION_MAP.put("v1_20_5", "v1_20_6");
        VERSION_MAP.put("v1_20_3", "v1_20_4");
    }

    public static String mapVersion(String version) {
        String mappedVersion = VERSION_MAP.get(version);
        return mappedVersion != null ? mappedVersion : version;
    }
}
