package com.amazon.livingroom.voice.models;

import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class VoiceMessage {

    @NotNull
    public final String messageTrackerId;

    @NotNull
    public final VoiceCommand payload;

    @NotNull
    public final String voiceAssistant;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new VoiceMessage$$ExternalSyntheticLambda0())};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<VoiceMessage> serializer() {
            return VoiceMessage$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ VoiceMessage(int i, String str, String str2, VoiceCommand voiceCommand, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, VoiceMessage$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.voiceAssistant = str;
        this.messageTrackerId = str2;
        this.payload = voiceCommand;
    }

    public static /* synthetic */ VoiceMessage copy$default(VoiceMessage voiceMessage, String str, String str2, VoiceCommand voiceCommand, int i, Object obj) {
        if ((i & 1) != 0) {
            str = voiceMessage.voiceAssistant;
        }
        if ((i & 2) != 0) {
            str2 = voiceMessage.messageTrackerId;
        }
        if ((i & 4) != 0) {
            voiceCommand = voiceMessage.payload;
        }
        return voiceMessage.copy(str, str2, voiceCommand);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(VoiceMessage voiceMessage, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        compositeEncoder.encodeStringElement(serialDescriptor, 0, voiceMessage.voiceAssistant);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, voiceMessage.messageTrackerId);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 2, lazyArr[2].getValue(), voiceMessage.payload);
    }

    @NotNull
    public final String component1() {
        return this.voiceAssistant;
    }

    @NotNull
    public final String component2() {
        return this.messageTrackerId;
    }

    @NotNull
    public final VoiceCommand component3() {
        return this.payload;
    }

    @NotNull
    public final VoiceMessage copy(@NotNull String voiceAssistant, @NotNull String messageTrackerId, @NotNull VoiceCommand payload) {
        Intrinsics.checkNotNullParameter(voiceAssistant, "voiceAssistant");
        Intrinsics.checkNotNullParameter(messageTrackerId, "messageTrackerId");
        Intrinsics.checkNotNullParameter(payload, "payload");
        return new VoiceMessage(voiceAssistant, messageTrackerId, payload);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VoiceMessage)) {
            return false;
        }
        VoiceMessage voiceMessage = (VoiceMessage) obj;
        return Intrinsics.areEqual(this.voiceAssistant, voiceMessage.voiceAssistant) && Intrinsics.areEqual(this.messageTrackerId, voiceMessage.messageTrackerId) && Intrinsics.areEqual(this.payload, voiceMessage.payload);
    }

    @NotNull
    public final String getMessageTrackerId() {
        return this.messageTrackerId;
    }

    @NotNull
    public final VoiceCommand getPayload() {
        return this.payload;
    }

    @NotNull
    public final String getVoiceAssistant() {
        return this.voiceAssistant;
    }

    public int hashCode() {
        return this.payload.hashCode() + DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.messageTrackerId, this.voiceAssistant.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        String str = this.voiceAssistant;
        String str2 = this.messageTrackerId;
        VoiceCommand voiceCommand = this.payload;
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("VoiceMessage(voiceAssistant=", str, ", messageTrackerId=", str2, ", payload=");
        sbM.append(voiceCommand);
        sbM.append(")");
        return sbM.toString();
    }

    public VoiceMessage(@NotNull String voiceAssistant, @NotNull String messageTrackerId, @NotNull VoiceCommand payload) {
        Intrinsics.checkNotNullParameter(voiceAssistant, "voiceAssistant");
        Intrinsics.checkNotNullParameter(messageTrackerId, "messageTrackerId");
        Intrinsics.checkNotNullParameter(payload, "payload");
        this.voiceAssistant = voiceAssistant;
        this.messageTrackerId = messageTrackerId;
        this.payload = payload;
    }
}
