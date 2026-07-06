package com.amazon.avod.mpb.api.sample;

import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.annotate.CalledFromNative;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DiagnosticInfo {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final DiagnosticInfo emptyDiagnosticInfo = new DiagnosticInfo(0, 0, 0, 0, 15, null);
    public int numAudioSamplesInBuffer;
    public int numCorruptedVideoFrames;
    public int numDroppedFrames;
    public int numVideoSamplesInBuffer;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final DiagnosticInfo getEmptyDiagnosticInfo() {
            return DiagnosticInfo.emptyDiagnosticInfo;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getEmptyDiagnosticInfo$annotations() {
        }
    }

    public DiagnosticInfo() {
        this(0, 0, 0, 0, 15, null);
    }

    public static DiagnosticInfo copy$default(DiagnosticInfo diagnosticInfo, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = diagnosticInfo.numDroppedFrames;
        }
        if ((i5 & 2) != 0) {
            i2 = diagnosticInfo.numCorruptedVideoFrames;
        }
        if ((i5 & 4) != 0) {
            i3 = diagnosticInfo.numVideoSamplesInBuffer;
        }
        if ((i5 & 8) != 0) {
            i4 = diagnosticInfo.numAudioSamplesInBuffer;
        }
        diagnosticInfo.getClass();
        return new DiagnosticInfo(i, i2, i3, i4);
    }

    @NotNull
    public static final DiagnosticInfo getEmptyDiagnosticInfo() {
        Companion.getClass();
        return emptyDiagnosticInfo;
    }

    public final int component1() {
        return this.numDroppedFrames;
    }

    public final int component2() {
        return this.numCorruptedVideoFrames;
    }

    public final int component3() {
        return this.numVideoSamplesInBuffer;
    }

    public final int component4() {
        return this.numAudioSamplesInBuffer;
    }

    @NotNull
    public final DiagnosticInfo copy(int i, int i2, int i3, int i4) {
        return new DiagnosticInfo(i, i2, i3, i4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DiagnosticInfo)) {
            return false;
        }
        DiagnosticInfo diagnosticInfo = (DiagnosticInfo) obj;
        return this.numDroppedFrames == diagnosticInfo.numDroppedFrames && this.numCorruptedVideoFrames == diagnosticInfo.numCorruptedVideoFrames && this.numVideoSamplesInBuffer == diagnosticInfo.numVideoSamplesInBuffer && this.numAudioSamplesInBuffer == diagnosticInfo.numAudioSamplesInBuffer;
    }

    @CalledFromNative
    public final int getNumAudioSamplesInBuffer() {
        return this.numAudioSamplesInBuffer;
    }

    @CalledFromNative
    public final int getNumCorruptedVideoFrames() {
        return this.numCorruptedVideoFrames;
    }

    @CalledFromNative
    public final int getNumDroppedFrames() {
        return this.numDroppedFrames;
    }

    @CalledFromNative
    public final int getNumVideoSamplesInBuffer() {
        return this.numVideoSamplesInBuffer;
    }

    public int hashCode() {
        return (((((this.numDroppedFrames * 31) + this.numCorruptedVideoFrames) * 31) + this.numVideoSamplesInBuffer) * 31) + this.numAudioSamplesInBuffer;
    }

    public final void setNumAudioSamplesInBuffer(int i) {
        this.numAudioSamplesInBuffer = i;
    }

    public final void setNumCorruptedVideoFrames(int i) {
        this.numCorruptedVideoFrames = i;
    }

    public final void setNumDroppedFrames(int i) {
        this.numDroppedFrames = i;
    }

    public final void setNumVideoSamplesInBuffer(int i) {
        this.numVideoSamplesInBuffer = i;
    }

    @NotNull
    public String toString() {
        int i = this.numDroppedFrames;
        int i2 = this.numCorruptedVideoFrames;
        int i3 = this.numVideoSamplesInBuffer;
        int i4 = this.numAudioSamplesInBuffer;
        StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("DiagnosticInfo(numDroppedFrames=", i, ", numCorruptedVideoFrames=", i2, ", numVideoSamplesInBuffer=");
        sbM.append(i3);
        sbM.append(", numAudioSamplesInBuffer=");
        sbM.append(i4);
        sbM.append(")");
        return sbM.toString();
    }

    public DiagnosticInfo(int i, int i2, int i3, int i4) {
        this.numDroppedFrames = i;
        this.numCorruptedVideoFrames = i2;
        this.numVideoSamplesInBuffer = i3;
        this.numAudioSamplesInBuffer = i4;
    }

    public /* synthetic */ DiagnosticInfo(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4);
    }
}
