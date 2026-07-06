package com.amazon.avod.mpb.media.playback.source;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AacCodecSpecificDataFactory {

    @NotNull
    public static final AacCodecSpecificDataFactory INSTANCE = new AacCodecSpecificDataFactory();

    @NotNull
    public static final int[] AAC_FREQUENCIES = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};

    @NotNull
    public static final int[] AAC_CHANNEL_CONFIGURATIONS = {0, 1, 2, 3, 4, 5, 6, 0, 7};

    @NotNull
    public final byte[] buildAacCodecSpecificData(int i, int i2) {
        int aacChannelConfig = (getAacChannelConfig(i) << 3) | (getAacFrequencyIndex(i2) << 7) | 4096 | 2;
        return new byte[]{(byte) (aacChannelConfig >> 8), (byte) aacChannelConfig};
    }

    public final int getAacChannelConfig(int i) throws MediaPipelineBackendException {
        int[] iArr = AAC_CHANNEL_CONFIGURATIONS;
        int i2 = i < iArr.length ? iArr[i] : 0;
        if (i2 != 0 && i2 <= 7) {
            return i2;
        }
        String strM = MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unsupported number of channels: ", i);
        MpbLog.errorf(strM, new Object[0]);
        throw new MediaPipelineBackendException(strM, MediaPipelineBackendResultCode.AV_MPB_AUDIO_UNSUPPORTED_CHANNEL_COUNT);
    }

    public final int getAacFrequencyIndex(int i) throws MediaPipelineBackendException {
        int length = AAC_FREQUENCIES.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (AAC_FREQUENCIES[i2] == i) {
                return i2;
            }
        }
        String strM = MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unsupported AAC frequency: ", i);
        MpbLog.errorf(strM, new Object[0]);
        throw new MediaPipelineBackendException(strM, MediaPipelineBackendResultCode.AV_MPB_AUDIO_UNSUPPORTED_FREQUENCY);
    }
}
