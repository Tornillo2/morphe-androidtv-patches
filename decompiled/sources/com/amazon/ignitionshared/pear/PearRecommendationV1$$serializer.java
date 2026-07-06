package com.amazon.ignitionshared.pear;

import java.util.List;
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
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class PearRecommendationV1$$serializer implements GeneratedSerializer<PearRecommendationV1> {

    @NotNull
    public static final PearRecommendationV1$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        PearRecommendationV1$$serializer pearRecommendationV1$$serializer = new PearRecommendationV1$$serializer();
        INSTANCE = pearRecommendationV1$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.ignitionshared.pear.PearRecommendationV1", pearRecommendationV1$$serializer, 2);
        pluginGeneratedSerialDescriptor.addElement("refresh", false);
        pluginGeneratedSerialDescriptor.addElement("widgets", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{Refresh$$serializer.INSTANCE, PearRecommendationV1.$childSerializers[1].getValue()};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final PearRecommendationV1 deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = PearRecommendationV1.$childSerializers;
        compositeDecoderBeginStructure.decodeSequentially();
        Refresh refresh = null;
        List list = null;
        boolean z = true;
        int i = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            if (iDecodeElementIndex == -1) {
                z = false;
            } else if (iDecodeElementIndex == 0) {
                refresh = (Refresh) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, Refresh$$serializer.INSTANCE, refresh);
                i |= 1;
            } else {
                if (iDecodeElementIndex != 1) {
                    throw new UnknownFieldException(iDecodeElementIndex);
                }
                list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy) lazyArr[1].getValue(), list);
                i |= 2;
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new PearRecommendationV1(i, refresh, list, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull PearRecommendationV1 value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        PearRecommendationV1.write$Self$ignitionshared_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
