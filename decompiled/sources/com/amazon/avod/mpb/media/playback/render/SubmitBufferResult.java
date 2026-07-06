package com.amazon.avod.mpb.media.playback.render;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SubmitBufferResult {
    public long adjustedReleaseTimeNs;
    public int bytesRead;

    public SubmitBufferResult() {
        this(0, 0L, 3, null);
    }

    public static SubmitBufferResult copy$default(SubmitBufferResult submitBufferResult, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = submitBufferResult.bytesRead;
        }
        if ((i2 & 2) != 0) {
            j = submitBufferResult.adjustedReleaseTimeNs;
        }
        submitBufferResult.getClass();
        return new SubmitBufferResult(i, j);
    }

    public final int component1() {
        return this.bytesRead;
    }

    public final long component2() {
        return this.adjustedReleaseTimeNs;
    }

    @NotNull
    public final SubmitBufferResult copy(int i, long j) {
        return new SubmitBufferResult(i, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubmitBufferResult)) {
            return false;
        }
        SubmitBufferResult submitBufferResult = (SubmitBufferResult) obj;
        return this.bytesRead == submitBufferResult.bytesRead && this.adjustedReleaseTimeNs == submitBufferResult.adjustedReleaseTimeNs;
    }

    public final long getAdjustedReleaseTimeNs() {
        return this.adjustedReleaseTimeNs;
    }

    public final int getBytesRead() {
        return this.bytesRead;
    }

    public int hashCode() {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(this.adjustedReleaseTimeNs) + (this.bytesRead * 31);
    }

    public final void setAdjustedReleaseTimeNs(long j) {
        this.adjustedReleaseTimeNs = j;
    }

    public final void setBytesRead(int i) {
        this.bytesRead = i;
    }

    @NotNull
    public String toString() {
        return "SubmitBufferResult(bytesRead=" + this.bytesRead + ", adjustedReleaseTimeNs=" + this.adjustedReleaseTimeNs + ")";
    }

    public SubmitBufferResult(int i, long j) {
        this.bytesRead = i;
        this.adjustedReleaseTimeNs = j;
    }

    public /* synthetic */ SubmitBufferResult(int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? -3L : j);
    }
}
