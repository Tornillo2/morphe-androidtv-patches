package com.amazon.livingroom.voice.models;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class EnableCaptionsCommand extends VoiceCommand {

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers;

    @NotNull
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<EnableCaptionsCommand> serializer() {
            return EnableCaptionsCommand$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        $childSerializers = new Lazy[]{LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new EnableCaptionsCommand$$ExternalSyntheticLambda0()), null, LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new EnableCaptionsCommand$$ExternalSyntheticLambda1())};
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EnableCaptionsCommand(int i, VoiceCommandNamespace voiceCommandNamespace, String str, VoiceCommandName voiceCommandName, SerializationConstructorMarker serializationConstructorMarker) {
        super(i, voiceCommandNamespace, str, voiceCommandName, serializationConstructorMarker);
        if (7 == (i & 7)) {
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 7, EnableCaptionsCommand$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public EnableCaptionsCommand() {
        super(VoiceCommandNamespace.TRANSPORT_CONTROLS, VoiceCommandKt.TRANSPORT_CONTROLS_PAYLOAD_VERSION, VoiceCommandName.ENABLE_CAPTIONS);
    }
}
