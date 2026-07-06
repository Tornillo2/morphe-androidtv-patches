package kotlin.ranges;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.UInt;
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
public class UIntProgression implements Iterable<UInt>, KMappedMarker {

    @NotNull
    public static final Companion Companion = new Companion();
    public final int first;
    public final int last;
    public final int step;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        /* JADX INFO: renamed from: fromClosedRange-Nkh28Cs, reason: not valid java name */
        public final UIntProgression m1813fromClosedRangeNkh28Cs(int i, int i2, int i3) {
            return new UIntProgression(i, i2, i3);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ UIntProgression(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof UIntProgression)) {
            return false;
        }
        if (isEmpty() && ((UIntProgression) obj).isEmpty()) {
            return true;
        }
        UIntProgression uIntProgression = (UIntProgression) obj;
        return this.first == uIntProgression.first && this.last == uIntProgression.last && this.step == uIntProgression.step;
    }

    /* JADX INFO: renamed from: getFirst-pVg5ArA, reason: not valid java name */
    public final int m1811getFirstpVg5ArA() {
        return this.first;
    }

    /* JADX INFO: renamed from: getLast-pVg5ArA, reason: not valid java name */
    public final int m1812getLastpVg5ArA() {
        return this.last;
    }

    public final int getStep() {
        return this.step;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.first * 31) + this.last) * 31) + this.step;
    }

    public boolean isEmpty() {
        return this.step > 0 ? Integer.compare(this.first ^ Integer.MIN_VALUE, this.last ^ Integer.MIN_VALUE) > 0 : Integer.compare(this.first ^ Integer.MIN_VALUE, this.last ^ Integer.MIN_VALUE) < 0;
    }

    @Override // java.lang.Iterable
    @NotNull
    public final Iterator<UInt> iterator() {
        return new UIntProgressionIterator(this.first, this.last, this.step);
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        int i;
        if (this.step > 0) {
            sb = new StringBuilder();
            sb.append((Object) UInt.m720toStringimpl(this.first));
            sb.append("..");
            sb.append((Object) UInt.m720toStringimpl(this.last));
            sb.append(" step ");
            i = this.step;
        } else {
            sb = new StringBuilder();
            sb.append((Object) UInt.m720toStringimpl(this.first));
            sb.append(" downTo ");
            sb.append((Object) UInt.m720toStringimpl(this.last));
            sb.append(" step ");
            i = -this.step;
        }
        sb.append(i);
        return sb.toString();
    }

    public UIntProgression(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i3 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = i;
        this.last = UProgressionUtilKt.m1795getProgressionLastElementNkh28Cs(i, i2, i3);
        this.step = i3;
    }
}
