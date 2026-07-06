package com.amazon.ignitionshared.pear;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class RecommendationsV1$$serializer implements GeneratedSerializer<RecommendationsV1> {

    @NotNull
    public static final RecommendationsV1$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        RecommendationsV1$$serializer recommendationsV1$$serializer = new RecommendationsV1$$serializer();
        INSTANCE = recommendationsV1$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.ignitionshared.pear.RecommendationsV1", recommendationsV1$$serializer, 3);
        pluginGeneratedSerialDescriptor.addElement("credentials", true);
        pluginGeneratedSerialDescriptor.addElement("error", false);
        pluginGeneratedSerialDescriptor.addElement("request", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(Credentials$$serializer.INSTANCE), Error$$serializer.INSTANCE, Request$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final RecommendationsV1 deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        Credentials credentials = null;
        Error error = null;
        Request request = null;
        boolean z = true;
        int i = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            if (iDecodeElementIndex == -1) {
                z = false;
            } else if (iDecodeElementIndex == 0) {
                credentials = (Credentials) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, Credentials$$serializer.INSTANCE, credentials);
                i |= 1;
            } else if (iDecodeElementIndex == 1) {
                error = (Error) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, Error$$serializer.INSTANCE, error);
                i |= 2;
            } else {
                if (iDecodeElementIndex != 2) {
                    throw new UnknownFieldException(iDecodeElementIndex);
                }
                request = (Request) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, Request$$serializer.INSTANCE, request);
                i |= 4;
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new RecommendationsV1(i, credentials, error, request, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull RecommendationsV1 value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        RecommendationsV1.write$Self$ignitionshared_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
