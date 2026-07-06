package com.amazon.livingroom.deviceproperties;

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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class DevicePlaybackCapabilities$$serializer implements GeneratedSerializer<DevicePlaybackCapabilities> {

    @NotNull
    public static final DevicePlaybackCapabilities$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        DevicePlaybackCapabilities$$serializer devicePlaybackCapabilities$$serializer = new DevicePlaybackCapabilities$$serializer();
        INSTANCE = devicePlaybackCapabilities$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.livingroom.deviceproperties.DevicePlaybackCapabilities", devicePlaybackCapabilities$$serializer, 8);
        pluginGeneratedSerialDescriptor.addElement("intraChunkSeekSupport", false);
        pluginGeneratedSerialDescriptor.addElement("supportsVariableAspectRatio", false);
        pluginGeneratedSerialDescriptor.addElement("maxInstanceCount", false);
        pluginGeneratedSerialDescriptor.addElement("supportedPictureModes", false);
        pluginGeneratedSerialDescriptor.addElement("supportsAudioVolume", false);
        pluginGeneratedSerialDescriptor.addElement("supportedTracks", true);
        pluginGeneratedSerialDescriptor.addElement("supportsDynamicPlayerResize", true);
        pluginGeneratedSerialDescriptor.addElement("supportsTrackDARUpdates", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        Lazy[] lazyArr = DevicePlaybackCapabilities.$childSerializers;
        BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
        return new KSerializer[]{booleanSerializer, booleanSerializer, IntSerializer.INSTANCE, lazyArr[3].getValue(), booleanSerializer, lazyArr[5].getValue(), booleanSerializer, booleanSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final DevicePlaybackCapabilities deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArr = DevicePlaybackCapabilities.$childSerializers;
        compositeDecoderBeginStructure.decodeSequentially();
        List list = null;
        List list2 = null;
        boolean z = true;
        int i = 0;
        boolean zDecodeBooleanElement = false;
        boolean zDecodeBooleanElement2 = false;
        int iDecodeIntElement = 0;
        boolean zDecodeBooleanElement3 = false;
        boolean zDecodeBooleanElement4 = false;
        boolean zDecodeBooleanElement5 = false;
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
                    zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 1);
                    i |= 2;
                    break;
                case 2:
                    iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 2);
                    i |= 4;
                    break;
                case 3:
                    list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, (DeserializationStrategy) lazyArr[3].getValue(), list);
                    i |= 8;
                    break;
                case 4:
                    zDecodeBooleanElement3 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 4);
                    i |= 16;
                    break;
                case 5:
                    list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 5, (DeserializationStrategy) lazyArr[5].getValue(), list2);
                    i |= 32;
                    break;
                case 6:
                    zDecodeBooleanElement4 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 6);
                    i |= 64;
                    break;
                case 7:
                    zDecodeBooleanElement5 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 7);
                    i |= 128;
                    break;
                default:
                    throw new UnknownFieldException(iDecodeElementIndex);
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new DevicePlaybackCapabilities(i, zDecodeBooleanElement, zDecodeBooleanElement2, iDecodeIntElement, list, zDecodeBooleanElement3, list2, zDecodeBooleanElement4, zDecodeBooleanElement5, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull DevicePlaybackCapabilities value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        DevicePlaybackCapabilities.write$Self$ignitionshared_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
