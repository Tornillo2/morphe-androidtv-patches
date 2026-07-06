package com.amazon.avod.mpb.api.query;

import java.util.Map;
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
public /* synthetic */ class CodecQuery$$serializer implements GeneratedSerializer<CodecQuery> {

    @NotNull
    public static final CodecQuery$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        CodecQuery$$serializer codecQuery$$serializer = new CodecQuery$$serializer();
        INSTANCE = codecQuery$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.avod.mpb.api.query.CodecQuery", codecQuery$$serializer, 3);
        pluginGeneratedSerialDescriptor.addElement("mimeType", false);
        pluginGeneratedSerialDescriptor.addElement("mimeSubType", false);
        pluginGeneratedSerialDescriptor.addElement("attributes", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        Lazy[] lazyArr = CodecQuery.$childSerializers;
        return new KSerializer[]{lazyArr[0].getValue(), StringSerializer.INSTANCE, lazyArr[2].getValue()};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final CodecQuery deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = CodecQuery.$childSerializers;
        compositeDecoderBeginStructure.decodeSequentially();
        MediaType mediaType = null;
        String strDecodeStringElement = null;
        Map map = null;
        boolean z = true;
        int i = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            if (iDecodeElementIndex == -1) {
                z = false;
            } else if (iDecodeElementIndex == 0) {
                mediaType = (MediaType) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, (DeserializationStrategy) lazyArr[0].getValue(), mediaType);
                i |= 1;
            } else if (iDecodeElementIndex == 1) {
                strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
                i |= 2;
            } else {
                if (iDecodeElementIndex != 2) {
                    throw new UnknownFieldException(iDecodeElementIndex);
                }
                map = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, (DeserializationStrategy) lazyArr[2].getValue(), map);
                i |= 4;
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new CodecQuery(i, mediaType, strDecodeStringElement, map, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull CodecQuery value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        CodecQuery.write$Self$core_mpb_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
