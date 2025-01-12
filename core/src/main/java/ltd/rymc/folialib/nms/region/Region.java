package ltd.rymc.folialib.nms.region;

public interface Region <T> {

    /**
     * Retrieves the center chunk position of the region.
     * <p>Note: On Bukkit, this method always returns a fixed position, which is the center of the overworld (x: 0, z: 0).</p>
     *
     * @return the {@link ChunkPosition} representing the center of the region.
     */
    ChunkPosition getCenterChunkPosition();

    /**
     * Retrieves the TPS (ticks per second) for the region over a specified time interval.
     * <p>Note: The {@link TpsReportLength} values {@code SECONDS_5} and {@code SECONDS_15} are not valid on Bukkit
     * and will default to returning the value for {@link TpsReportLength#MINUTE_1}.</p>
     *
     * @param length the length of the reporting interval, specified as a {@link TpsReportLength} enum value.
     * @return the TPS as a double value for the specified interval.
     */
    double getTps(TpsReportLength length);

    T getHandle();
}
