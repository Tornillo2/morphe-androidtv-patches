package com.amazon.ignitionshared.pear;

import com.amazon.ignitionshared.pear.PearAccessTokenProvider;
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
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class PearAccessTokenProvider$PearAuthenticationResponse$$serializer implements GeneratedSerializer<PearAccessTokenProvider.PearAuthenticationResponse> {

    @NotNull
    public static final PearAccessTokenProvider$PearAuthenticationResponse$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        PearAccessTokenProvider$PearAuthenticationResponse$$serializer pearAccessTokenProvider$PearAuthenticationResponse$$serializer = new PearAccessTokenProvider$PearAuthenticationResponse$$serializer();
        INSTANCE = pearAccessTokenProvider$PearAuthenticationResponse$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.ignitionshared.pear.PearAccessTokenProvider.PearAuthenticationResponse", pearAccessTokenProvider$PearAuthenticationResponse$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("access_token", false);
        pluginGeneratedSerialDescriptor.addElement("expires_in", false);
        pluginGeneratedSerialDescriptor.addElement("refresh_token", false);
        pluginGeneratedSerialDescriptor.addElement("token_type", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{stringSerializer, LongSerializer.INSTANCE, stringSerializer, stringSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final PearAccessTokenProvider.PearAuthenticationResponse deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        String strDecodeStringElement = null;
        String strDecodeStringElement2 = null;
        String strDecodeStringElement3 = null;
        long jDecodeLongElement = 0;
        boolean z = true;
        int i = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            if (iDecodeElementIndex == -1) {
                z = false;
            } else if (iDecodeElementIndex == 0) {
                strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
                i |= 1;
            } else if (iDecodeElementIndex == 1) {
                jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 1);
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
        return new PearAccessTokenProvider.PearAuthenticationResponse(i, strDecodeStringElement, jDecodeLongElement, strDecodeStringElement2, strDecodeStringElement3, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull PearAccessTokenProvider.PearAuthenticationResponse value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        PearAccessTokenProvider.PearAuthenticationResponse.write$Self$ignitionshared_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
