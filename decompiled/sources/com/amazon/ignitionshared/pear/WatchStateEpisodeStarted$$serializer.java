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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class WatchStateEpisodeStarted$$serializer implements GeneratedSerializer<WatchStateEpisodeStarted> {

    @NotNull
    public static final WatchStateEpisodeStarted$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        WatchStateEpisodeStarted$$serializer watchStateEpisodeStarted$$serializer = new WatchStateEpisodeStarted$$serializer();
        INSTANCE = watchStateEpisodeStarted$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.ignitionshared.pear.WatchStateEpisodeStarted", watchStateEpisodeStarted$$serializer, 7);
        pluginGeneratedSerialDescriptor.addElement("focusId", false);
        pluginGeneratedSerialDescriptor.addElement("lastEngagementTime", true);
        pluginGeneratedSerialDescriptor.addElement("watchNextType", true);
        pluginGeneratedSerialDescriptor.addElement("totalTimeSeconds", true);
        pluginGeneratedSerialDescriptor.addElement("bookmarkTimeSeconds", false);
        pluginGeneratedSerialDescriptor.addElement("lastWatchTime", false);
        pluginGeneratedSerialDescriptor.addElement("episodeNumber", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        KSerializer<?> nullable = BuiltinSerializersKt.getNullable(stringSerializer);
        KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(stringSerializer);
        IntSerializer intSerializer = IntSerializer.INSTANCE;
        return new KSerializer[]{Id$$serializer.INSTANCE, nullable, nullable2, BuiltinSerializersKt.getNullable(intSerializer), intSerializer, stringSerializer, intSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final WatchStateEpisodeStarted deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        Id id = null;
        String str = null;
        String str2 = null;
        Integer num = null;
        String strDecodeStringElement = null;
        boolean z = true;
        int i = 0;
        int iDecodeIntElement = 0;
        int iDecodeIntElement2 = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            switch (iDecodeElementIndex) {
                case -1:
                    z = false;
                    break;
                case 0:
                    id = (Id) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, Id$$serializer.INSTANCE, id);
                    i |= 1;
                    break;
                case 1:
                    str = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, str);
                    i |= 2;
                    break;
                case 2:
                    str2 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, str2);
                    i |= 4;
                    break;
                case 3:
                    num = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, num);
                    i |= 8;
                    break;
                case 4:
                    iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 4);
                    i |= 16;
                    break;
                case 5:
                    strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 5);
                    i |= 32;
                    break;
                case 6:
                    iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 6);
                    i |= 64;
                    break;
                default:
                    throw new UnknownFieldException(iDecodeElementIndex);
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new WatchStateEpisodeStarted(i, id, str, str2, num, iDecodeIntElement, strDecodeStringElement, iDecodeIntElement2, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull WatchStateEpisodeStarted value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        WatchStateEpisodeStarted.write$Self$ignitionshared_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
