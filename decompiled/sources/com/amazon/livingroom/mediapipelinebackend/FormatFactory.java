package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.OptIn;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.UnstableApi;
import com.amazon.livingroom.mediapipelinebackend.AvCodecType;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public class FormatFactory {
    public static List<byte[]> buildAudioInitData(AvCodecType.Audio audio, int i, int i2) {
        if (audio != AvCodecType.Audio.kCodecTypeAAC) {
            return null;
        }
        return AacCodecSpecificDataFactory.buildAacCodecSpecificData(i, i2);
    }

    public static Format createAudioFormat(int i, int i2, int i3, UUID uuid, byte[] bArr) {
        AvCodecType.Audio audioFindById = AvCodecType.Audio.findById(i);
        String str = audioFindById.mimeType;
        List<byte[]> listBuildAudioInitData = buildAudioInitData(audioFindById, i2, i3);
        DrmInitData drmInitData = getDrmInitData(uuid, bArr, str);
        Format.Builder builder = new Format.Builder();
        builder.id = "audio";
        builder.sampleMimeType = MimeTypes.normalizeMimeType(str);
        builder.initializationData = listBuildAudioInitData;
        builder.channelCount = i2;
        builder.sampleRate = i3;
        if (drmInitData != null) {
            builder.cryptoType = 2;
            builder.drmInitData = drmInitData;
        }
        return new Format(builder);
    }

    public static Format createVideoFormat(String str, int i, int i2, float f, float f2, UUID uuid, byte[] bArr) {
        DrmInitData drmInitData = getDrmInitData(uuid, bArr, str);
        Format.Builder builder = new Format.Builder();
        builder.id = "video";
        builder.sampleMimeType = MimeTypes.normalizeMimeType(str);
        builder.width = i;
        builder.height = i2;
        builder.frameRate = f2;
        builder.pixelWidthHeightRatio = f;
        if (drmInitData != null) {
            builder.cryptoType = 2;
            builder.drmInitData = drmInitData;
        }
        return new Format(builder);
    }

    public static DrmInitData getDrmInitData(UUID uuid, byte[] bArr, String str) {
        if (bArr != null) {
            return new DrmInitData(new DrmInitData.SchemeData(uuid, null, str, bArr));
        }
        return null;
    }

    public static Format createVideoFormat(int i, int i2, int i3, float f, UUID uuid, byte[] bArr) {
        return createVideoFormat(AvCodecType.Video.findById(i).mimeType, i2, i3, -1.0f, f, uuid, bArr);
    }
}
