package com.amazon.ignitionshared.pear;

import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
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
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class Deeplink$$serializer implements GeneratedSerializer<Deeplink> {

    @NotNull
    public static final Deeplink$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        Deeplink$$serializer deeplink$$serializer = new Deeplink$$serializer();
        INSTANCE = deeplink$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.ignitionshared.pear.Deeplink", deeplink$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("html5", true);
        pluginGeneratedSerialDescriptor.addElement("native-v1", false);
        pluginGeneratedSerialDescriptor.addElement("native-v2", false);
        pluginGeneratedSerialDescriptor.addElement(ResourceDrawableDecoder.ANDROID_PACKAGE_NAME, false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(stringSerializer), stringSerializer, stringSerializer, stringSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final Deeplink deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        String str = null;
        String strDecodeStringElement = null;
        String strDecodeStringElement2 = null;
        String strDecodeStringElement3 = null;
        boolean z = true;
        int i = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            if (iDecodeElementIndex == -1) {
                z = false;
            } else if (iDecodeElementIndex == 0) {
                str = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, str);
                i |= 1;
            } else if (iDecodeElementIndex == 1) {
                strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
                i |= 2;
            } else if (iDecodeElementIndex == 2) {
                strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 2);
                i |= 4;
            } else {
                if (iDecodeElementIndex != 3) {
                    throw new UnknownFieldException(iDecodeElementIndex);
                }
                strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 3);
                i |= 8;
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new Deeplink(i, str, strDecodeStringElement, strDecodeStringElement2, strDecodeStringElement3, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull Deeplink value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        Deeplink.write$Self$ignitionshared_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
