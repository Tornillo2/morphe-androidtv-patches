package com.amazon.ignitionshared.pear;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.jvm.internal.Intrinsics;
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
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class HttpRequestV1$$serializer implements GeneratedSerializer<HttpRequestV1> {

    @NotNull
    public static final HttpRequestV1$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        HttpRequestV1$$serializer httpRequestV1$$serializer = new HttpRequestV1$$serializer();
        INSTANCE = httpRequestV1$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.ignitionshared.pear.HttpRequestV1", httpRequestV1$$serializer, 8);
        pluginGeneratedSerialDescriptor.addElement("body", false);
        pluginGeneratedSerialDescriptor.addElement("headers", false);
        pluginGeneratedSerialDescriptor.addElement("host", false);
        pluginGeneratedSerialDescriptor.addElement("path", false);
        pluginGeneratedSerialDescriptor.addElement("port", false);
        pluginGeneratedSerialDescriptor.addElement("protocol", false);
        pluginGeneratedSerialDescriptor.addElement("query_params", false);
        pluginGeneratedSerialDescriptor.addElement("verb", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        JsonObjectSerializer jsonObjectSerializer = JsonObjectSerializer.INSTANCE;
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{jsonObjectSerializer, jsonObjectSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, jsonObjectSerializer, stringSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final HttpRequestV1 deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        JsonObject jsonObject = null;
        JsonObject jsonObject2 = null;
        String strDecodeStringElement = null;
        String strDecodeStringElement2 = null;
        String strDecodeStringElement3 = null;
        String strDecodeStringElement4 = null;
        JsonObject jsonObject3 = null;
        String strDecodeStringElement5 = null;
        boolean z = true;
        int i = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            switch (iDecodeElementIndex) {
                case -1:
                    z = false;
                    break;
                case 0:
                    jsonObject = (JsonObject) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, JsonObjectSerializer.INSTANCE, jsonObject);
                    i |= 1;
                    break;
                case 1:
                    jsonObject2 = (JsonObject) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, JsonObjectSerializer.INSTANCE, jsonObject2);
                    i |= 2;
                    break;
                case 2:
                    strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 2);
                    i |= 4;
                    break;
                case 3:
                    strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 3);
                    i |= 8;
                    break;
                case 4:
                    strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 4);
                    i |= 16;
                    break;
                case 5:
                    strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 5);
                    i |= 32;
                    break;
                case 6:
                    jsonObject3 = (JsonObject) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, JsonObjectSerializer.INSTANCE, jsonObject3);
                    i |= 64;
                    break;
                case 7:
                    strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 7);
                    i |= 128;
                    break;
                default:
                    throw new UnknownFieldException(iDecodeElementIndex);
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new HttpRequestV1(i, jsonObject, jsonObject2, strDecodeStringElement, strDecodeStringElement2, strDecodeStringElement3, strDecodeStringElement4, jsonObject3, strDecodeStringElement5, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull HttpRequestV1 value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        HttpRequestV1.write$Self$ignitionshared_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
