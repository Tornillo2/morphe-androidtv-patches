package androidx.media3.extractor;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class SeekPoint {
    public static final SeekPoint START = new SeekPoint(0, 0);
    public final long position;
    public final long timeUs;

    public SeekPoint(long j, long j2) {
        this.timeUs = j;
        this.position = j2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && SeekPoint.class == obj.getClass()) {
            SeekPoint seekPoint = (SeekPoint) obj;
            if (this.timeUs == seekPoint.timeUs && this.position == seekPoint.position) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((int) this.timeUs) * 31) + ((int) this.position);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[timeUs=");
        sb.append(this.timeUs);
        sb.append(", position=");
        return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, this.position, "]");
    }
}
