package com.amazon.livingroom.mediapipelinebackend;

import java.nio.ByteBuffer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class FtvMpbNativeBridge {

    @NotNull
    public static final Companion Companion = new Companion();
    public final long instancePtr;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final native void bufferUnderrun(long j);

        /* JADX INFO: Access modifiers changed from: private */
        public final native void endOfStream(long j);

        /* JADX INFO: Access modifiers changed from: private */
        public final native void onError(long j, String str, int i, String str2, boolean z);

        /* JADX INFO: Access modifiers changed from: private */
        public final native void onPlaybackStarted(long j);

        /* JADX INFO: Access modifiers changed from: private */
        public final native void onPropertyChanged(long j, String str, String str2);

        /* JADX INFO: Access modifiers changed from: private */
        public final native void onReadyToPlay(long j);

        /* JADX INFO: Access modifiers changed from: private */
        public final native void onSampleAddReference(long j, ByteBuffer byteBuffer);

        /* JADX INFO: Access modifiers changed from: private */
        public final native void onSampleRemoveReference(long j, ByteBuffer byteBuffer);

        @NotNull
        public final FtvMpbNativeBridge create(long j) {
            return new FtvMpbNativeBridge(j);
        }

        public final native void onApiPropertyChanged(long j, long j2, @NotNull String str, @Nullable String str2);

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ FtvMpbNativeBridge(long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(j);
    }

    public final void bufferUnderrun() {
        Companion.bufferUnderrun(this.instancePtr);
    }

    public final void endOfStream() {
        Companion.endOfStream(this.instancePtr);
    }

    public final void onError(@Nullable String str, int i, @Nullable String str2, boolean z) {
        Companion.onError(this.instancePtr, str, i, str2, z);
    }

    public final void onPlaybackStarted() {
        Companion.onPlaybackStarted(this.instancePtr);
    }

    public final void onPropertyChanged(@NotNull String key, @Nullable String str) {
        Intrinsics.checkNotNullParameter(key, "key");
        Companion.onPropertyChanged(this.instancePtr, key, str);
    }

    public final void onReadyToPlay() {
        Companion.onReadyToPlay(this.instancePtr);
    }

    public final void onSampleAddReference(@Nullable ByteBuffer byteBuffer) {
        Companion.onSampleAddReference(this.instancePtr, byteBuffer);
    }

    public final void onSampleRemoveReference(@Nullable ByteBuffer byteBuffer) {
        Companion.onSampleRemoveReference(this.instancePtr, byteBuffer);
    }

    public FtvMpbNativeBridge(long j) {
        this.instancePtr = j;
    }
}
