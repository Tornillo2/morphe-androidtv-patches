package com.amazon.avod.mpb.api.core;

import java.util.List;
import java.util.Set;
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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class MediaPipelineBackendCapabilities$$serializer implements GeneratedSerializer<MediaPipelineBackendCapabilities> {

    @NotNull
    public static final MediaPipelineBackendCapabilities$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        MediaPipelineBackendCapabilities$$serializer mediaPipelineBackendCapabilities$$serializer = new MediaPipelineBackendCapabilities$$serializer();
        INSTANCE = mediaPipelineBackendCapabilities$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.avod.mpb.api.core.MediaPipelineBackendCapabilities", mediaPipelineBackendCapabilities$$serializer, 11);
        pluginGeneratedSerialDescriptor.addElement("intraChunkSeekSupport", false);
        pluginGeneratedSerialDescriptor.addElement("supportedPictureModes", false);
        pluginGeneratedSerialDescriptor.addElement("supportsVariableAspectRatio", false);
        pluginGeneratedSerialDescriptor.addElement("supportsDynamicPlayerResize", false);
        pluginGeneratedSerialDescriptor.addElement("supportsViewportAnimation", false);
        pluginGeneratedSerialDescriptor.addElement("maxInstanceCount", false);
        pluginGeneratedSerialDescriptor.addElement("supportsAudioVolume", false);
        pluginGeneratedSerialDescriptor.addElement("supportsAudioMuting", false);
        pluginGeneratedSerialDescriptor.addElement("supportsAudioMixing", false);
        pluginGeneratedSerialDescriptor.addElement("supportedTracks", false);
        pluginGeneratedSerialDescriptor.addElement("supportsTrackDARUpdates", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        Lazy[] lazyArr = MediaPipelineBackendCapabilities.$childSerializers;
        BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
        return new KSerializer[]{booleanSerializer, lazyArr[1].getValue(), booleanSerializer, booleanSerializer, booleanSerializer, IntSerializer.INSTANCE, booleanSerializer, booleanSerializer, booleanSerializer, lazyArr[9].getValue(), booleanSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final MediaPipelineBackendCapabilities deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = MediaPipelineBackendCapabilities.$childSerializers;
        compositeDecoderBeginStructure.decodeSequentially();
        Set set = null;
        List list = null;
        boolean z = true;
        int i = 0;
        boolean zDecodeBooleanElement = false;
        boolean zDecodeBooleanElement2 = false;
        boolean zDecodeBooleanElement3 = false;
        boolean zDecodeBooleanElement4 = false;
        int iDecodeIntElement = 0;
        boolean zDecodeBooleanElement5 = false;
        boolean zDecodeBooleanElement6 = false;
        boolean zDecodeBooleanElement7 = false;
        boolean zDecodeBooleanElement8 = false;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            switch (iDecodeElementIndex) {
                case -1:
                    z = false;
                    break;
                case 0:
                    zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 0);
                    i |= 1;
                    break;
                case 1:
                    list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy) lazyArr[1].getValue(), list);
                    i |= 2;
                    break;
                case 2:
                    zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 2);
                    i |= 4;
                    break;
                case 3:
                    zDecodeBooleanElement3 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 3);
                    i |= 8;
                    break;
                case 4:
                    zDecodeBooleanElement4 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 4);
                    i |= 16;
                    break;
                case 5:
                    iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 5);
                    i |= 32;
                    break;
                case 6:
                    zDecodeBooleanElement5 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 6);
                    i |= 64;
                    break;
                case 7:
                    zDecodeBooleanElement6 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 7);
                    i |= 128;
                    break;
                case 8:
                    zDecodeBooleanElement7 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 8);
                    i |= 256;
                    break;
                case 9:
                    set = (Set) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 9, (DeserializationStrategy) lazyArr[9].getValue(), set);
                    i |= 512;
                    break;
                case 10:
                    zDecodeBooleanElement8 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 10);
                    i |= 1024;
                    break;
                default:
                    throw new UnknownFieldException(iDecodeElementIndex);
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new MediaPipelineBackendCapabilities(i, zDecodeBooleanElement, list, zDecodeBooleanElement2, zDecodeBooleanElement3, zDecodeBooleanElement4, iDecodeIntElement, zDecodeBooleanElement5, zDecodeBooleanElement6, zDecodeBooleanElement7, set, zDecodeBooleanElement8, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull MediaPipelineBackendCapabilities value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        MediaPipelineBackendCapabilities.write$Self$core_mpb_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
