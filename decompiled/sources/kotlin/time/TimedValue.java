package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = {ExperimentalTime.class})
public final class TimedValue<T> {
    public final long duration;
    public final T value;

    public /* synthetic */ TimedValue(Object obj, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, j);
    }

    /* JADX INFO: renamed from: copy-RFiDyg4$default, reason: not valid java name */
    public static TimedValue m2059copyRFiDyg4$default(TimedValue timedValue, Object obj, long j, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = timedValue.value;
        }
        if ((i & 2) != 0) {
            j = timedValue.duration;
        }
        timedValue.getClass();
        return new TimedValue(obj, j);
    }

    public final T component1() {
        return this.value;
    }

    /* JADX INFO: renamed from: component2-UwyO8pc, reason: not valid java name */
    public final long m2060component2UwyO8pc() {
        return this.duration;
    }

    @NotNull
    /* JADX INFO: renamed from: copy-RFiDyg4, reason: not valid java name */
    public final TimedValue<T> m2061copyRFiDyg4(T t, long j) {
        return new TimedValue<>(t, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimedValue)) {
            return false;
        }
        TimedValue timedValue = (TimedValue) obj;
        return Intrinsics.areEqual(this.value, timedValue.value) && Duration.m1930equalsimpl0(this.duration, timedValue.duration);
    }

    /* JADX INFO: renamed from: getDuration-UwyO8pc, reason: not valid java name */
    public final long m2062getDurationUwyO8pc() {
        return this.duration;
    }

    public final T getValue() {
        return this.value;
    }

    public int hashCode() {
        T t = this.value;
        return Duration.m1946hashCodeimpl(this.duration) + ((t == null ? 0 : t.hashCode()) * 31);
    }

    @NotNull
    public String toString() {
        return "TimedValue(value=" + this.value + ", duration=" + ((Object) Duration.m1965toStringimpl(this.duration)) + ')';
    }

    public TimedValue(T t, long j) {
        this.value = t;
        this.duration = j;
    }
}
