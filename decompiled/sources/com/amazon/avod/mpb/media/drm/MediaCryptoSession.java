package com.amazon.avod.mpb.media.drm;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.sample.BufferedMediaSample;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MediaCryptoSession {
    @Nullable
    MediaCodec.CryptoInfo generateCryptoInfo(@NotNull BufferedMediaSample bufferedMediaSample) throws MediaPipelineBackendException;

    @NotNull
    MediaCrypto getMediaCrypto(boolean z);

    void initialize() throws MediaPipelineBackendException;

    void release();
}
