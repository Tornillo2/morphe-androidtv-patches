package com.amazon.livingroom.voice.models;

import java.lang.annotation.Annotation;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.EnumsKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class VoiceCommandName {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ VoiceCommandName[] $VALUES;

    @NotNull
    public static final Lazy<KSerializer<Object>> $cachedSerializer$delegate;

    @NotNull
    public static final Companion Companion;

    @SerialName("Pause")
    public static final VoiceCommandName PAUSE = new VoiceCommandName("PAUSE", 0);

    @SerialName("Play")
    public static final VoiceCommandName PLAY = new VoiceCommandName("PLAY", 1);

    @SerialName("SeekToPosition")
    public static final VoiceCommandName SEEK_TO_POSITION = new VoiceCommandName("SEEK_TO_POSITION", 2);

    @SerialName("Rewind")
    public static final VoiceCommandName REWIND = new VoiceCommandName("REWIND", 3);

    @SerialName("FastForward")
    public static final VoiceCommandName FAST_FORWARD = new VoiceCommandName("FAST_FORWARD", 4);

    @SerialName("Next")
    public static final VoiceCommandName NEXT = new VoiceCommandName("NEXT", 5);

    @SerialName("Stop")
    public static final VoiceCommandName STOP = new VoiceCommandName("STOP", 6);

    @SerialName("EnableCaptions")
    public static final VoiceCommandName ENABLE_CAPTIONS = new VoiceCommandName("ENABLE_CAPTIONS", 7);

    @SerialName("DisableCaptions")
    public static final VoiceCommandName DISABLE_CAPTIONS = new VoiceCommandName("DISABLE_CAPTIONS", 8);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final /* synthetic */ KSerializer get$cachedSerializer() {
            return (KSerializer) VoiceCommandName.$cachedSerializer$delegate.getValue();
        }

        @NotNull
        public final KSerializer<VoiceCommandName> serializer() {
            return get$cachedSerializer();
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public static final /* synthetic */ VoiceCommandName[] $values() {
        return new VoiceCommandName[]{PAUSE, PLAY, SEEK_TO_POSITION, REWIND, FAST_FORWARD, NEXT, STOP, ENABLE_CAPTIONS, DISABLE_CAPTIONS};
    }

    static {
        VoiceCommandName[] voiceCommandNameArr$values = $values();
        $VALUES = voiceCommandNameArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(voiceCommandNameArr$values);
        Companion = new Companion();
        $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new VoiceCommandName$$ExternalSyntheticLambda0());
    }

    public VoiceCommandName(String str, int i) {
    }

    public static final /* synthetic */ KSerializer _init_$_anonymous_() {
        return EnumsKt.createAnnotatedEnumSerializer("com.amazon.livingroom.voice.models.VoiceCommandName", values(), new String[]{"Pause", "Play", "SeekToPosition", "Rewind", "FastForward", "Next", "Stop", "EnableCaptions", "DisableCaptions"}, new Annotation[][]{null, null, null, null, null, null, null, null, null}, null);
    }

    @NotNull
    public static EnumEntries<VoiceCommandName> getEntries() {
        return $ENTRIES;
    }

    public static VoiceCommandName valueOf(String str) {
        return (VoiceCommandName) Enum.valueOf(VoiceCommandName.class, str);
    }

    public static VoiceCommandName[] values() {
        return (VoiceCommandName[]) $VALUES.clone();
    }
}
