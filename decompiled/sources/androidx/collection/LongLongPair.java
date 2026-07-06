package androidx.collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class LongLongPair {
    public final long first;
    public final long second;

    public LongLongPair(long j, long j2) {
        this.first = j;
        this.second = j2;
    }

    public final long component1() {
        return this.first;
    }

    public final long component2() {
        return this.second;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof LongLongPair)) {
            return false;
        }
        LongLongPair longLongPair = (LongLongPair) obj;
        return longLongPair.first == this.first && longLongPair.second == this.second;
    }

    public final long getFirst() {
        return this.first;
    }

    public final long getSecond() {
        return this.second;
    }

    public int hashCode() {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(this.first) ^ FloatFloatPair$$ExternalSyntheticBackport0.m(this.second);
    }

    @NotNull
    public String toString() {
        return "(" + this.first + ", " + this.second + ')';
    }
}
