package com.amazon.avod.mpb.media.drm;

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
public /* synthetic */ class DrmSystemCapabilities$$serializer implements GeneratedSerializer<DrmSystemCapabilities> {

    @NotNull
    public static final DrmSystemCapabilities$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        DrmSystemCapabilities$$serializer drmSystemCapabilities$$serializer = new DrmSystemCapabilities$$serializer();
        INSTANCE = drmSystemCapabilities$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.avod.mpb.media.drm.DrmSystemCapabilities", drmSystemCapabilities$$serializer, 1);
        pluginGeneratedSerialDescriptor.addElement("supportedSystems", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{DrmSystemCapabilities.$childSerializers[0].getValue()};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final DrmSystemCapabilities deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = DrmSystemCapabilities.$childSerializers;
        compositeDecoderBeginStructure.decodeSequentially();
        List list = null;
        boolean z = true;
        int i = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            if (iDecodeElementIndex == -1) {
                z = false;
            } else {
                if (iDecodeElementIndex != 0) {
                    throw new UnknownFieldException(iDecodeElementIndex);
                }
                list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, (DeserializationStrategy) lazyArr[0].getValue(), list);
                i = 1;
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new DrmSystemCapabilities(i, list, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull DrmSystemCapabilities value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        compositeEncoderBeginStructure.encodeSerializableElement(serialDescriptor, 0, DrmSystemCapabilities.$childSerializers[0].getValue(), value.supportedSystems);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
