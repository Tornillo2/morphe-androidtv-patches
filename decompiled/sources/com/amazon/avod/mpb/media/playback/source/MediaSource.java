package com.amazon.avod.mpb.media.playback.source;

import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.sample.BufferedMediaSample;
import com.amazon.avod.mpb.api.sample.MediaSample;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MediaSource {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        public final byte[] getContentsAsByteArray(@NotNull ByteBuffer byteBuffer) {
            Intrinsics.checkNotNullParameter(byteBuffer, "byteBuffer");
            byteBuffer.clear();
            byte[] bArr = new byte[byteBuffer.capacity()];
            byteBuffer.get(bArr);
            byteBuffer.clear();
            return bArr;
        }
    }

    void advance();

    void flush();

    int getNumberOfSamplesInBuffer();

    boolean hasNext();

    boolean hasStreamFinished();

    boolean hasUnderrun();

    void onPipelineUnderrun();

    void onStreamFinish();

    @NotNull
    BufferedMediaSample readSampleData(@NotNull ByteBuffer byteBuffer) throws MediaPipelineBackendException;

    void setDisplayAspectRatio(float f);

    int submitSample(@NotNull MediaSample mediaSample);
}
