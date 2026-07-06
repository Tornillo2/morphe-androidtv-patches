package com.amazon.livingroom.voice.models;

import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class VoiceStartup {

    @NotNull
    public final String messageTrackerId;

    @NotNull
    public final String[] supportedVoiceAssistants;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new VoiceStartup$$ExternalSyntheticLambda0()), null};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<VoiceStartup> serializer() {
            return VoiceStartup$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ VoiceStartup(int i, String[] strArr, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, VoiceStartup$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.supportedVoiceAssistants = strArr;
        this.messageTrackerId = str;
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE);
    }

    public static /* synthetic */ VoiceStartup copy$default(VoiceStartup voiceStartup, String[] strArr, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            strArr = voiceStartup.supportedVoiceAssistants;
        }
        if ((i & 2) != 0) {
            str = voiceStartup.messageTrackerId;
        }
        return voiceStartup.copy(strArr, str);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(VoiceStartup voiceStartup, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, $childSerializers[0].getValue(), voiceStartup.supportedVoiceAssistants);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, voiceStartup.messageTrackerId);
    }

    @NotNull
    public final String[] component1() {
        return this.supportedVoiceAssistants;
    }

    @NotNull
    public final String component2() {
        return this.messageTrackerId;
    }

    @NotNull
    public final VoiceStartup copy(@NotNull String[] supportedVoiceAssistants, @NotNull String messageTrackerId) {
        Intrinsics.checkNotNullParameter(supportedVoiceAssistants, "supportedVoiceAssistants");
        Intrinsics.checkNotNullParameter(messageTrackerId, "messageTrackerId");
        return new VoiceStartup(supportedVoiceAssistants, messageTrackerId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VoiceStartup)) {
            return false;
        }
        VoiceStartup voiceStartup = (VoiceStartup) obj;
        return Intrinsics.areEqual(this.supportedVoiceAssistants, voiceStartup.supportedVoiceAssistants) && Intrinsics.areEqual(this.messageTrackerId, voiceStartup.messageTrackerId);
    }

    @NotNull
    public final String getMessageTrackerId() {
        return this.messageTrackerId;
    }

    @NotNull
    public final String[] getSupportedVoiceAssistants() {
        return this.supportedVoiceAssistants;
    }

    public int hashCode() {
        return this.messageTrackerId.hashCode() + (Arrays.hashCode(this.supportedVoiceAssistants) * 31);
    }

    @NotNull
    public String toString() {
        return "VoiceStartup(supportedVoiceAssistants=" + Arrays.toString(this.supportedVoiceAssistants) + ", messageTrackerId=" + this.messageTrackerId + ")";
    }

    public VoiceStartup(@NotNull String[] supportedVoiceAssistants, @NotNull String messageTrackerId) {
        Intrinsics.checkNotNullParameter(supportedVoiceAssistants, "supportedVoiceAssistants");
        Intrinsics.checkNotNullParameter(messageTrackerId, "messageTrackerId");
        this.supportedVoiceAssistants = supportedVoiceAssistants;
        this.messageTrackerId = messageTrackerId;
    }
}
