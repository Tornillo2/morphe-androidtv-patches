package kotlin.ranges;

import java.util.Iterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class LongProgression implements Iterable<Long>, KMappedMarker {

    @NotNull
    public static final Companion Companion = new Companion();
    public final long first;
    public final long last;
    public final long step;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final LongProgression fromClosedRange(long j, long j2, long j3) {
            return new LongProgression(j, j2, j3);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public LongProgression(long j, long j2, long j3) {
        if (j3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j3 == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = j;
        this.last = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
        this.step = j3;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof LongProgression)) {
            return false;
        }
        if (isEmpty() && ((LongProgression) obj).isEmpty()) {
            return true;
        }
        LongProgression longProgression = (LongProgression) obj;
        return this.first == longProgression.first && this.last == longProgression.last && this.step == longProgression.step;
    }

    public final long getFirst() {
        return this.first;
    }

    public final long getLast() {
        return this.last;
    }

    public final long getStep() {
        return this.step;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j = 31;
        long j2 = this.first;
        long j3 = this.last;
        long j4 = (((j2 ^ (j2 >>> 32)) * j) + (j3 ^ (j3 >>> 32))) * j;
        long j5 = this.step;
        return (int) (j4 + (j5 ^ (j5 >>> 32)));
    }

    public boolean isEmpty() {
        long j = this.step;
        long j2 = this.first;
        long j3 = this.last;
        return j > 0 ? j2 > j3 : j2 < j3;
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        long j;
        if (this.step > 0) {
            sb = new StringBuilder();
            sb.append(this.first);
            sb.append("..");
            sb.append(this.last);
            sb.append(" step ");
            j = this.step;
        } else {
            sb = new StringBuilder();
            sb.append(this.first);
            sb.append(" downTo ");
            sb.append(this.last);
            sb.append(" step ");
            j = -this.step;
        }
        sb.append(j);
        return sb.toString();
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Long> iterator() {
        return new LongProgressionIterator(this.first, this.last, this.step);
    }
}
