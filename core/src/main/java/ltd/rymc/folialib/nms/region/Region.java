package ltd.rymc.folialib.nms.region;

public interface Region {

    ChunkPosition getCenterChunkPosition();

    double getTps(TpsReportLength length);

}
