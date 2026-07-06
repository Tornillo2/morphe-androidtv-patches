package com.amazon.avod.mpb.api.sample;

import com.amazon.avod.mpb.media.AudioStreamType;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioCodecType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ AudioCodecType[] $VALUES;
    public static final AudioCodecType AUDIO_CODEC_AAC;
    public static final AudioCodecType AUDIO_CODEC_AACH;
    public static final AudioCodecType AUDIO_CODEC_AACHV2;
    public static final AudioCodecType AUDIO_CODEC_AACHX;
    public static final AudioCodecType AUDIO_CODEC_AACLC;
    public static final AudioCodecType AUDIO_CODEC_AC3;
    public static final AudioCodecType AUDIO_CODEC_EAC3;
    public static final AudioCodecType AUDIO_CODEC_FLAC;
    public static final AudioCodecType AUDIO_CODEC_MP3;
    public static final AudioCodecType AUDIO_CODEC_OPUS;
    public static final AudioCodecType AUDIO_CODEC_PCM;
    public static final AudioCodecType AUDIO_CODEC_VORBIS;

    @Nullable
    public final AudioStreamType audioStreamType;

    @NotNull
    public final String codecType;

    public static final /* synthetic */ AudioCodecType[] $values() {
        return new AudioCodecType[]{AUDIO_CODEC_AAC, AUDIO_CODEC_AACLC, AUDIO_CODEC_AACH, AUDIO_CODEC_AACHX, AUDIO_CODEC_AC3, AUDIO_CODEC_EAC3, AUDIO_CODEC_PCM, AUDIO_CODEC_AACHV2, AUDIO_CODEC_OPUS, AUDIO_CODEC_VORBIS, AUDIO_CODEC_MP3, AUDIO_CODEC_FLAC};
    }

    static {
        AudioStreamType audioStreamType = AudioStreamType.AACL;
        AUDIO_CODEC_AAC = new AudioCodecType("AUDIO_CODEC_AAC", 0, "AUDIO_CODEC_AAC", audioStreamType);
        AUDIO_CODEC_AACLC = new AudioCodecType("AUDIO_CODEC_AACLC", 1, "AUDIO_CODEC_AACLC", audioStreamType);
        AudioStreamType audioStreamType2 = AudioStreamType.AACHV2;
        AUDIO_CODEC_AACH = new AudioCodecType("AUDIO_CODEC_AACH", 2, "AUDIO_CODEC_AACH", audioStreamType2);
        AUDIO_CODEC_AACHX = new AudioCodecType("AUDIO_CODEC_AACHX", 3, "AUDIO_CODEC_AACHX", null, 2, null);
        AudioStreamType audioStreamType3 = AudioStreamType.DDP;
        AUDIO_CODEC_AC3 = new AudioCodecType("AUDIO_CODEC_AC3", 4, "AUDIO_CODEC_AC3", audioStreamType3);
        AUDIO_CODEC_EAC3 = new AudioCodecType("AUDIO_CODEC_EAC3", 5, "AUDIO_CODEC_EAC3", audioStreamType3);
        DefaultConstructorMarker defaultConstructorMarker = null;
        AUDIO_CODEC_PCM = new AudioCodecType("AUDIO_CODEC_PCM", 6, "AUDIO_CODEC_PCM", null, 2, defaultConstructorMarker);
        AUDIO_CODEC_AACHV2 = new AudioCodecType("AUDIO_CODEC_AACHV2", 7, "AUDIO_CODEC_AACHV2", audioStreamType2);
        AUDIO_CODEC_OPUS = new AudioCodecType("AUDIO_CODEC_OPUS", 8, "AUDIO_CODEC_OPUS", null, 2, null);
        AUDIO_CODEC_VORBIS = new AudioCodecType("AUDIO_CODEC_VORBIS", 9, "AUDIO_CODEC_VORBIS", null, 2, defaultConstructorMarker);
        AUDIO_CODEC_MP3 = new AudioCodecType("AUDIO_CODEC_MP3", 10, "AUDIO_CODEC_MP3", null, 2, null);
        AUDIO_CODEC_FLAC = new AudioCodecType("AUDIO_CODEC_FLAC", 11, "AUDIO_CODEC_FLAC", null, 2, null);
        AudioCodecType[] audioCodecTypeArr$values = $values();
        $VALUES = audioCodecTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(audioCodecTypeArr$values);
    }

    public AudioCodecType(String str, int i, String str2, AudioStreamType audioStreamType) {
        this.codecType = str2;
        this.audioStreamType = audioStreamType;
    }

    @NotNull
    public static EnumEntries<AudioCodecType> getEntries() {
        return $ENTRIES;
    }

    public static AudioCodecType valueOf(String str) {
        return (AudioCodecType) Enum.valueOf(AudioCodecType.class, str);
    }

    public static AudioCodecType[] values() {
        return (AudioCodecType[]) $VALUES.clone();
    }

    @Nullable
    public final AudioStreamType getAudioStreamType() {
        return this.audioStreamType;
    }

    public /* synthetic */ AudioCodecType(String str, int i, String str2, AudioStreamType audioStreamType, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, (i2 & 2) != 0 ? null : audioStreamType);
    }
}
