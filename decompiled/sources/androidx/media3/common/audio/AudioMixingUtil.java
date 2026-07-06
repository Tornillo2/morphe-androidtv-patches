package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class AudioMixingUtil {
    public static final float FLOAT_PCM_MAX_VALUE = 1.0f;
    public static final float FLOAT_PCM_MIN_VALUE = -1.0f;

    public static boolean canMix(AudioProcessor.AudioFormat audioFormat) {
        if (audioFormat.sampleRate == -1 || audioFormat.channelCount == -1) {
            return false;
        }
        int i = audioFormat.encoding;
        return i == 2 || i == 4;
    }

    public static float floatSampleToInt16Pcm(float f) {
        return Util.constrainValue(f * (f < 0.0f ? 32768 : 32767), -32768.0f, 32767.0f);
    }

    public static float getPcmSample(ByteBuffer byteBuffer, boolean z, boolean z2) {
        return z2 ? z ? byteBuffer.getShort() : floatSampleToInt16Pcm(byteBuffer.getFloat()) : z ? int16SampleToFloatPcm(byteBuffer.getShort()) : byteBuffer.getFloat();
    }

    public static float int16SampleToFloatPcm(short s) {
        return s / (s < 0 ? 32768 : 32767);
    }

    public static ByteBuffer mix(ByteBuffer byteBuffer, AudioProcessor.AudioFormat audioFormat, ByteBuffer byteBuffer2, AudioProcessor.AudioFormat audioFormat2, ChannelMixingMatrix channelMixingMatrix, int i, boolean z) {
        boolean z2 = audioFormat.encoding == 2;
        boolean z3 = audioFormat2.encoding == 2;
        int i2 = channelMixingMatrix.inputChannelCount;
        int i3 = channelMixingMatrix.outputChannelCount;
        float[] fArr = new float[i2];
        float[] fArr2 = new float[i3];
        for (int i4 = 0; i4 < i; i4++) {
            if (z) {
                int iPosition = byteBuffer2.position();
                for (int i5 = 0; i5 < i3; i5++) {
                    fArr2[i5] = getPcmSample(byteBuffer2, z3, z3);
                }
                byteBuffer2.position(iPosition);
            }
            for (int i6 = 0; i6 < i2; i6++) {
                fArr[i6] = getPcmSample(byteBuffer, z2, z3);
            }
            for (int i7 = 0; i7 < i3; i7++) {
                for (int i8 = 0; i8 < i2; i8++) {
                    fArr2[i7] = (channelMixingMatrix.getMixingCoefficient(i8, i7) * fArr[i8]) + fArr2[i7];
                }
                if (z3) {
                    byteBuffer2.putShort((short) Util.constrainValue(fArr2[i7], -32768.0f, 32767.0f));
                } else {
                    byteBuffer2.putFloat(Util.constrainValue(fArr2[i7], -1.0f, 1.0f));
                }
                fArr2[i7] = 0.0f;
            }
        }
        return byteBuffer2;
    }

    public static boolean canMix(AudioProcessor.AudioFormat audioFormat, AudioProcessor.AudioFormat audioFormat2) {
        return audioFormat.sampleRate == audioFormat2.sampleRate && canMix(audioFormat) && canMix(audioFormat2);
    }
}
