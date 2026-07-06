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
public final class VoiceCommandNamespace {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ VoiceCommandNamespace[] $VALUES;

    @NotNull
    public static final Lazy<KSerializer<Object>> $cachedSerializer$delegate;

    @NotNull
    public static final Companion Companion;

    @SerialName("GenericAssistant.TransportControls")
    public static final VoiceCommandNamespace TRANSPORT_CONTROLS;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final /* synthetic */ KSerializer get$cachedSerializer() {
            return (KSerializer) VoiceCommandNamespace.$cachedSerializer$delegate.getValue();
        }

        @NotNull
        public final KSerializer<VoiceCommandNamespace> serializer() {
            return get$cachedSerializer();
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public static final /* synthetic */ VoiceCommandNamespace[] $values() {
        return new VoiceCommandNamespace[]{TRANSPORT_CONTROLS};
    }

    static {
        VoiceCommandNamespace voiceCommandNamespace = new VoiceCommandNamespace("TRANSPORT_CONTROLS", 0);
        TRANSPORT_CONTROLS = voiceCommandNamespace;
        VoiceCommandNamespace[] voiceCommandNamespaceArr = {voiceCommandNamespace};
        $VALUES = voiceCommandNamespaceArr;
        $ENTRIES = EnumEntriesKt.enumEntries(voiceCommandNamespaceArr);
        Companion = new Companion();
        $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new VoiceCommandNamespace$$ExternalSyntheticLambda0());
    }

    public VoiceCommandNamespace(String str, int i) {
    }

    public static final /* synthetic */ KSerializer _init_$_anonymous_() {
        return EnumsKt.createAnnotatedEnumSerializer("com.amazon.livingroom.voice.models.VoiceCommandNamespace", values(), new String[]{"GenericAssistant.TransportControls"}, new Annotation[][]{null}, null);
    }

    @NotNull
    public static EnumEntries<VoiceCommandNamespace> getEntries() {
        return $ENTRIES;
    }

    public static VoiceCommandNamespace valueOf(String str) {
        return (VoiceCommandNamespace) Enum.valueOf(VoiceCommandNamespace.class, str);
    }

    public static VoiceCommandNamespace[] values() {
        return (VoiceCommandNamespace[]) $VALUES.clone();
    }
}
