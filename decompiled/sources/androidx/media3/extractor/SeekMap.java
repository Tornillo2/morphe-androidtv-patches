package androidx.media3.extractor;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface SeekMap {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SeekPoints {
        public final SeekPoint first;
        public final SeekPoint second;

        public SeekPoints(SeekPoint seekPoint) {
            this(seekPoint, seekPoint);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && SeekPoints.class == obj.getClass()) {
                SeekPoints seekPoints = (SeekPoints) obj;
                if (this.first.equals(seekPoints.first) && this.second.equals(seekPoints.second)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.second.hashCode() + (this.first.hashCode() * 31);
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("[");
            sb.append(this.first);
            if (this.first.equals(this.second)) {
                str = "";
            } else {
                str = ", " + this.second;
            }
            return ActivityCompat$$ExternalSyntheticOutline0.m(sb, str, "]");
        }

        public SeekPoints(SeekPoint seekPoint, SeekPoint seekPoint2) {
            seekPoint.getClass();
            this.first = seekPoint;
            seekPoint2.getClass();
            this.second = seekPoint2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Unseekable implements SeekMap {
        public final long durationUs;
        public final SeekPoints startSeekPoints;

        public Unseekable(long j) {
            this(j, 0L);
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekPoints getSeekPoints(long j) {
            return this.startSeekPoints;
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return false;
        }

        public Unseekable(long j, long j2) {
            this.durationUs = j;
            SeekPoint seekPoint = j2 == 0 ? SeekPoint.START : new SeekPoint(0L, j2);
            this.startSeekPoints = new SeekPoints(seekPoint, seekPoint);
        }
    }

    long getDurationUs();

    SeekPoints getSeekPoints(long j);

    boolean isSeekable();
}
