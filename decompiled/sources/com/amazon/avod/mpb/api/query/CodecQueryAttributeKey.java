package com.amazon.avod.mpb.api.query;

import com.google.android.gms.common.Scopes;
import java.util.Set;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.serialization.SerialName;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class CodecQueryAttributeKey {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ CodecQueryAttributeKey[] $VALUES;

    @SerialName("bitrate")
    public static final CodecQueryAttributeKey BITRATE;

    @SerialName("channels")
    public static final CodecQueryAttributeKey CHANNELS;

    @SerialName("codecs")
    public static final CodecQueryAttributeKey CODECS;

    @SerialName("colorGamut")
    public static final CodecQueryAttributeKey COLOR_GAMUT;

    @SerialName("framerate")
    public static final CodecQueryAttributeKey FRAMERATE;

    @SerialName("hdrMetadataType")
    public static final CodecQueryAttributeKey HDR_METADATA_TYPE;

    @SerialName("height")
    public static final CodecQueryAttributeKey HEIGHT;

    @SerialName("level")
    public static final CodecQueryAttributeKey LEVEL;

    @SerialName(Scopes.PROFILE)
    public static final CodecQueryAttributeKey PROFILE;

    @SerialName("samplerate")
    public static final CodecQueryAttributeKey SAMPLERATE;

    @SerialName("transferFunction")
    public static final CodecQueryAttributeKey TRANSFER_FUNCTION;

    @SerialName("width")
    public static final CodecQueryAttributeKey WIDTH;

    @NotNull
    public final Set<MediaType> validMediaTypes;

    public static final /* synthetic */ CodecQueryAttributeKey[] $values() {
        return new CodecQueryAttributeKey[]{CODECS, WIDTH, HEIGHT, BITRATE, FRAMERATE, PROFILE, LEVEL, HDR_METADATA_TYPE, COLOR_GAMUT, TRANSFER_FUNCTION, CHANNELS, SAMPLERATE};
    }

    static {
        MediaType mediaType = MediaType.MEDIA_AUDIO;
        MediaType mediaType2 = MediaType.MEDIA_VIDEO;
        CODECS = new CodecQueryAttributeKey("CODECS", 0, ArraysKt___ArraysKt.toSet(new MediaType[]{mediaType, mediaType2}));
        WIDTH = new CodecQueryAttributeKey("WIDTH", 1, SetsKt__SetsJVMKt.setOf(mediaType2));
        HEIGHT = new CodecQueryAttributeKey("HEIGHT", 2, SetsKt__SetsJVMKt.setOf(mediaType2));
        BITRATE = new CodecQueryAttributeKey("BITRATE", 3, ArraysKt___ArraysKt.toSet(new MediaType[]{mediaType, mediaType2}));
        FRAMERATE = new CodecQueryAttributeKey("FRAMERATE", 4, SetsKt__SetsJVMKt.setOf(mediaType2));
        PROFILE = new CodecQueryAttributeKey("PROFILE", 5, SetsKt__SetsJVMKt.setOf(mediaType2));
        LEVEL = new CodecQueryAttributeKey("LEVEL", 6, SetsKt__SetsJVMKt.setOf(mediaType2));
        HDR_METADATA_TYPE = new CodecQueryAttributeKey("HDR_METADATA_TYPE", 7, SetsKt__SetsJVMKt.setOf(mediaType2));
        COLOR_GAMUT = new CodecQueryAttributeKey("COLOR_GAMUT", 8, SetsKt__SetsJVMKt.setOf(mediaType2));
        TRANSFER_FUNCTION = new CodecQueryAttributeKey("TRANSFER_FUNCTION", 9, SetsKt__SetsJVMKt.setOf(mediaType2));
        CHANNELS = new CodecQueryAttributeKey("CHANNELS", 10, SetsKt__SetsJVMKt.setOf(mediaType));
        SAMPLERATE = new CodecQueryAttributeKey("SAMPLERATE", 11, SetsKt__SetsJVMKt.setOf(mediaType));
        CodecQueryAttributeKey[] codecQueryAttributeKeyArr$values = $values();
        $VALUES = codecQueryAttributeKeyArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(codecQueryAttributeKeyArr$values);
    }

    public CodecQueryAttributeKey(String str, int i, Set set) {
        this.validMediaTypes = set;
    }

    @NotNull
    public static EnumEntries<CodecQueryAttributeKey> getEntries() {
        return $ENTRIES;
    }

    public static CodecQueryAttributeKey valueOf(String str) {
        return (CodecQueryAttributeKey) Enum.valueOf(CodecQueryAttributeKey.class, str);
    }

    public static CodecQueryAttributeKey[] values() {
        return (CodecQueryAttributeKey[]) $VALUES.clone();
    }

    @NotNull
    public final Set<MediaType> getValidMediaTypes() {
        return this.validMediaTypes;
    }
}
