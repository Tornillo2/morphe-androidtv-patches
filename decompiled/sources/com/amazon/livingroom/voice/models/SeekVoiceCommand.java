package com.amazon.livingroom.voice.models;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
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
public final class SeekVoiceCommand extends VoiceCommand {

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final SeekCommandPayload payload;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<SeekVoiceCommand> serializer() {
            return SeekVoiceCommand$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        $childSerializers = new Lazy[]{LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new SeekVoiceCommand$$ExternalSyntheticLambda0()), null, LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new SeekVoiceCommand$$ExternalSyntheticLambda1()), null};
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SeekVoiceCommand(int i, VoiceCommandNamespace voiceCommandNamespace, String str, VoiceCommandName voiceCommandName, SeekCommandPayload seekCommandPayload, SerializationConstructorMarker serializationConstructorMarker) {
        super(i, voiceCommandNamespace, str, voiceCommandName, serializationConstructorMarker);
        if (15 != (i & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i, 15, SeekVoiceCommand$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.payload = seekCommandPayload;
    }

    public static /* synthetic */ SeekVoiceCommand copy$default(SeekVoiceCommand seekVoiceCommand, SeekCommandPayload seekCommandPayload, int i, Object obj) {
        if ((i & 1) != 0) {
            seekCommandPayload = seekVoiceCommand.payload;
        }
        return seekVoiceCommand.copy(seekCommandPayload);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(SeekVoiceCommand seekVoiceCommand, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        VoiceCommand.write$Self(seekVoiceCommand, compositeEncoder, serialDescriptor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 3, SeekCommandPayload$$serializer.INSTANCE, seekVoiceCommand.payload);
    }

    @NotNull
    public final SeekCommandPayload component1() {
        return this.payload;
    }

    @NotNull
    public final SeekVoiceCommand copy(@NotNull SeekCommandPayload payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        return new SeekVoiceCommand(payload);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SeekVoiceCommand) && Intrinsics.areEqual(this.payload, ((SeekVoiceCommand) obj).payload);
    }

    @NotNull
    public final SeekCommandPayload getPayload() {
        return this.payload;
    }

    public int hashCode() {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(this.payload.position);
    }

    @NotNull
    public String toString() {
        return "SeekVoiceCommand(payload=" + this.payload + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SeekVoiceCommand(@NotNull SeekCommandPayload payload) {
        super(VoiceCommandNamespace.TRANSPORT_CONTROLS, VoiceCommandKt.TRANSPORT_CONTROLS_PAYLOAD_VERSION, VoiceCommandName.SEEK_TO_POSITION);
        Intrinsics.checkNotNullParameter(payload, "payload");
        this.payload = payload;
    }
}
