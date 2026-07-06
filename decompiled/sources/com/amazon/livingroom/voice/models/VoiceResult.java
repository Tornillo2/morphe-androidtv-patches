package com.amazon.livingroom.voice.models;

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
public final class VoiceResult {

    @NotNull
    public final String messageTrackerId;

    @NotNull
    public final ResultCode result;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new VoiceResult$$ExternalSyntheticLambda0())};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<VoiceResult> serializer() {
            return VoiceResult$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ VoiceResult(int i, String str, ResultCode resultCode, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, VoiceResult$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.messageTrackerId = str;
        this.result = resultCode;
    }

    public static /* synthetic */ VoiceResult copy$default(VoiceResult voiceResult, String str, ResultCode resultCode, int i, Object obj) {
        if ((i & 1) != 0) {
            str = voiceResult.messageTrackerId;
        }
        if ((i & 2) != 0) {
            resultCode = voiceResult.result;
        }
        return voiceResult.copy(str, resultCode);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(VoiceResult voiceResult, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        compositeEncoder.encodeStringElement(serialDescriptor, 0, voiceResult.messageTrackerId);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, lazyArr[1].getValue(), voiceResult.result);
    }

    @NotNull
    public final String component1() {
        return this.messageTrackerId;
    }

    @NotNull
    public final ResultCode component2() {
        return this.result;
    }

    @NotNull
    public final VoiceResult copy(@NotNull String messageTrackerId, @NotNull ResultCode result) {
        Intrinsics.checkNotNullParameter(messageTrackerId, "messageTrackerId");
        Intrinsics.checkNotNullParameter(result, "result");
        return new VoiceResult(messageTrackerId, result);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VoiceResult)) {
            return false;
        }
        VoiceResult voiceResult = (VoiceResult) obj;
        return Intrinsics.areEqual(this.messageTrackerId, voiceResult.messageTrackerId) && this.result == voiceResult.result;
    }

    @NotNull
    public final String getMessageTrackerId() {
        return this.messageTrackerId;
    }

    @NotNull
    public final ResultCode getResult() {
        return this.result;
    }

    public int hashCode() {
        return this.result.hashCode() + (this.messageTrackerId.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "VoiceResult(messageTrackerId=" + this.messageTrackerId + ", result=" + this.result + ")";
    }

    public VoiceResult(@NotNull String messageTrackerId, @NotNull ResultCode result) {
        Intrinsics.checkNotNullParameter(messageTrackerId, "messageTrackerId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.messageTrackerId = messageTrackerId;
        this.result = result;
    }
}
