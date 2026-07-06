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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class SupportedSystem$$serializer implements GeneratedSerializer<SupportedSystem> {

    @NotNull
    public static final SupportedSystem$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        SupportedSystem$$serializer supportedSystem$$serializer = new SupportedSystem$$serializer();
        INSTANCE = supportedSystem$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.avod.mpb.media.drm.SupportedSystem", supportedSystem$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("name", false);
        pluginGeneratedSerialDescriptor.addElement("encryptionSchemes", false);
        pluginGeneratedSerialDescriptor.addElement("sessionTypes", false);
        pluginGeneratedSerialDescriptor.addElement("maxSupportedSessions", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        Lazy[] lazyArr = SupportedSystem.$childSerializers;
        return new KSerializer[]{lazyArr[0].getValue(), lazyArr[1].getValue(), lazyArr[2].getValue(), IntSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SupportedSystem deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = SupportedSystem.$childSerializers;
        compositeDecoderBeginStructure.decodeSequentially();
        DrmSystemName drmSystemName = null;
        List list = null;
        List list2 = null;
        boolean z = true;
        int i = 0;
        int iDecodeIntElement = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            if (iDecodeElementIndex == -1) {
                z = false;
            } else if (iDecodeElementIndex == 0) {
                drmSystemName = (DrmSystemName) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, (DeserializationStrategy) lazyArr[0].getValue(), drmSystemName);
                i |= 1;
            } else if (iDecodeElementIndex == 1) {
                list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy) lazyArr[1].getValue(), list);
                i |= 2;
            } else if (iDecodeElementIndex == 2) {
                list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, (DeserializationStrategy) lazyArr[2].getValue(), list2);
                i |= 4;
            } else {
                if (iDecodeElementIndex != 3) {
                    throw new UnknownFieldException(iDecodeElementIndex);
                }
                iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 3);
                i |= 8;
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new SupportedSystem(i, drmSystemName, list, list2, iDecodeIntElement, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull SupportedSystem value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        SupportedSystem.write$Self$core_mpb_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
