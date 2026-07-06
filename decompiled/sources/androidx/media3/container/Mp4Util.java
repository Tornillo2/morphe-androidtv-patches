package androidx.media3.container;

import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class Mp4Util {
    public static final int UNIX_EPOCH_TO_MP4_TIME_DELTA_SECONDS = 2082844800;

    public static long mp4TimeToUnixTimeMs(long j) {
        return (j - 2082844800) * 1000;
    }

    public static long unixTimeToMp4TimeSeconds(long j) {
        return (j / 1000) + 2082844800;
    }
}
