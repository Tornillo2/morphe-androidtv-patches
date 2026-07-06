package kotlin.ranges;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.5")
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
public class ULongProgression implements Iterable<ULong>, KMappedMarker {

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
        /* JADX INFO: renamed from: fromClosedRange-7ftBX0g, reason: not valid java name */
        public final ULongProgression m1822fromClosedRange7ftBX0g(long j, long j2, long j3) {
            return new ULongProgression(j, j2, j3);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ ULongProgression(long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ULongProgression)) {
            return false;
        }
        if (isEmpty() && ((ULongProgression) obj).isEmpty()) {
            return true;
        }
        ULongProgression uLongProgression = (ULongProgression) obj;
        return this.first == uLongProgression.first && this.last == uLongProgression.last && this.step == uLongProgression.step;
    }

    /* JADX INFO: renamed from: getFirst-s-VKNKU, reason: not valid java name */
    public final long m1820getFirstsVKNKU() {
        return this.first;
    }

    /* JADX INFO: renamed from: getLast-s-VKNKU, reason: not valid java name */
    public final long m1821getLastsVKNKU() {
        return this.last;
    }

    public final long getStep() {
        return this.step;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j = this.first;
        long j2 = this.last;
        int i = ((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.step;
        return i + ((int) (j3 ^ (j3 >>> 32)));
    }

    public boolean isEmpty() {
        long j = this.step;
        int iCompare = Long.compare(this.first ^ Long.MIN_VALUE, this.last ^ Long.MIN_VALUE);
        return j > 0 ? iCompare > 0 : iCompare < 0;
    }

    @Override // java.lang.Iterable
    @NotNull
    public final Iterator<ULong> iterator() {
        return new ULongProgressionIterator(this.first, this.last, this.step);
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        long j;
        if (this.step > 0) {
            sb = new StringBuilder();
            sb.append((Object) UnsignedKt.ulongToString(this.first, 10));
            sb.append("..");
            sb.append((Object) UnsignedKt.ulongToString(this.last, 10));
            sb.append(" step ");
            j = this.step;
        } else {
            sb = new StringBuilder();
            sb.append((Object) UnsignedKt.ulongToString(this.first, 10));
            sb.append(" downTo ");
            sb.append((Object) UnsignedKt.ulongToString(this.last, 10));
            sb.append(" step ");
            j = -this.step;
        }
        sb.append(j);
        return sb.toString();
    }

    public ULongProgression(long j, long j2, long j3) {
        if (j3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j3 == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = j;
        this.last = UProgressionUtilKt.m1794getProgressionLastElement7ftBX0g(j, j2, j3);
        this.step = j3;
    }
}
