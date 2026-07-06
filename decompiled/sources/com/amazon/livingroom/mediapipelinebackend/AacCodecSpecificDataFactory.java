package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AacCodecSpecificDataFactory {
    public static final int[] AAC_FREQUENCIES = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
    public static final int[] AAC_CHANNEL_CONFIGURATIONS = {0, 1, 2, 3, 4, 5, 6, 0, 7};

    @NonNull
    public static List<byte[]> buildAacCodecSpecificData(int i, int i2) {
        int aacChannelConfig = (getAacChannelConfig(i) << 3) | (getAacFrequencyIndex(i2) << 7) | 4096 | 2;
        return Collections.singletonList(new byte[]{(byte) (aacChannelConfig >> 8), (byte) aacChannelConfig});
    }

    public static int getAacChannelConfig(int i) {
        int[] iArr = AAC_CHANNEL_CONFIGURATIONS;
        int i2 = i < iArr.length ? iArr[i] : 0;
        if (i2 != 0) {
            return i2;
        }
        String str = "Unsupported number of channels: " + i;
        MpbLog.e(str);
        throw new IllegalArgumentException(str);
    }

    public static int getAacFrequencyIndex(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = AAC_FREQUENCIES;
            if (i2 >= iArr.length) {
                String str = "Unsupported AAC frequency: " + i;
                MpbLog.e(str);
                throw new IllegalArgumentException(str);
            }
            if (iArr[i2] == i) {
                return i2;
            }
            i2++;
        }
    }
}
