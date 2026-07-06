package com.amazon.livingroom.voice.models;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class StopCommand$$serializer implements GeneratedSerializer<StopCommand> {

    @NotNull
    public static final StopCommand$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        StopCommand$$serializer stopCommand$$serializer = new StopCommand$$serializer();
        INSTANCE = stopCommand$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.livingroom.voice.models.StopCommand", stopCommand$$serializer, 3);
        pluginGeneratedSerialDescriptor.addElement("namespace", false);
        pluginGeneratedSerialDescriptor.addElement("namespaceVersion", false);
        pluginGeneratedSerialDescriptor.addElement("name", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        Lazy[] lazyArr = StopCommand.$childSerializers;
        return new KSerializer[]{lazyArr[0].getValue(), StringSerializer.INSTANCE, lazyArr[2].getValue()};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final StopCommand deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = StopCommand.$childSerializers;
        compositeDecoderBeginStructure.decodeSequentially();
        VoiceCommandNamespace voiceCommandNamespace = null;
        String strDecodeStringElement = null;
        VoiceCommandName voiceCommandName = null;
        boolean z = true;
        int i = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            if (iDecodeElementIndex == -1) {
                z = false;
            } else if (iDecodeElementIndex == 0) {
                voiceCommandNamespace = (VoiceCommandNamespace) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, (DeserializationStrategy) lazyArr[0].getValue(), voiceCommandNamespace);
                i |= 1;
            } else if (iDecodeElementIndex == 1) {
                strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
                i |= 2;
            } else {
                if (iDecodeElementIndex != 2) {
                    throw new UnknownFieldException(iDecodeElementIndex);
                }
                voiceCommandName = (VoiceCommandName) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, (DeserializationStrategy) lazyArr[2].getValue(), voiceCommandName);
                i |= 4;
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new StopCommand(i, voiceCommandNamespace, strDecodeStringElement, voiceCommandName, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull StopCommand value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        VoiceCommand.write$Self(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
