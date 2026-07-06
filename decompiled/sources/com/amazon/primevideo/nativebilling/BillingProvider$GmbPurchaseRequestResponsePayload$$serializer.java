package com.amazon.primevideo.nativebilling;

import com.amazon.primevideo.nativebilling.BillingProvider;
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
import kotlinx.serialization.internal.BooleanSerializer;
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
public /* synthetic */ class BillingProvider$GmbPurchaseRequestResponsePayload$$serializer implements GeneratedSerializer<BillingProvider.GmbPurchaseRequestResponsePayload> {

    @NotNull
    public static final BillingProvider$GmbPurchaseRequestResponsePayload$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        BillingProvider$GmbPurchaseRequestResponsePayload$$serializer billingProvider$GmbPurchaseRequestResponsePayload$$serializer = new BillingProvider$GmbPurchaseRequestResponsePayload$$serializer();
        INSTANCE = billingProvider$GmbPurchaseRequestResponsePayload$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.primevideo.nativebilling.BillingProvider.GmbPurchaseRequestResponsePayload", billingProvider$GmbPurchaseRequestResponsePayload$$serializer, 5);
        pluginGeneratedSerialDescriptor.addElement("requestId", false);
        pluginGeneratedSerialDescriptor.addElement("success", false);
        pluginGeneratedSerialDescriptor.addElement("error", true);
        pluginGeneratedSerialDescriptor.addElement("responseCode", true);
        pluginGeneratedSerialDescriptor.addElement("debugMessage", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(stringSerializer), BooleanSerializer.INSTANCE, BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(stringSerializer)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final BillingProvider.GmbPurchaseRequestResponsePayload deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        String str = null;
        String str2 = null;
        Integer num = null;
        String str3 = null;
        boolean z = true;
        int i = 0;
        boolean zDecodeBooleanElement = false;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            if (iDecodeElementIndex == -1) {
                z = false;
            } else if (iDecodeElementIndex == 0) {
                str = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, str);
                i |= 1;
            } else if (iDecodeElementIndex == 1) {
                zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 1);
                i |= 2;
            } else if (iDecodeElementIndex == 2) {
                str2 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, str2);
                i |= 4;
            } else if (iDecodeElementIndex == 3) {
                num = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, num);
                i |= 8;
            } else {
                if (iDecodeElementIndex != 4) {
                    throw new UnknownFieldException(iDecodeElementIndex);
                }
                str3 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, str3);
                i |= 16;
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new BillingProvider.GmbPurchaseRequestResponsePayload(i, str, zDecodeBooleanElement, str2, num, str3, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull BillingProvider.GmbPurchaseRequestResponsePayload value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        BillingProvider.GmbPurchaseRequestResponsePayload.write$Self$primevideo_armv7aRelease(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
