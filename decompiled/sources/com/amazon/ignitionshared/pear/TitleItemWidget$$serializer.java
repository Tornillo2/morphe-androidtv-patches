package com.amazon.ignitionshared.pear;

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
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class TitleItemWidget$$serializer implements GeneratedSerializer<TitleItemWidget> {

    @NotNull
    public static final TitleItemWidget$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        TitleItemWidget$$serializer titleItemWidget$$serializer = new TitleItemWidget$$serializer();
        INSTANCE = titleItemWidget$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.ignitionshared.pear.TitleItemWidget", titleItemWidget$$serializer, 2);
        pluginGeneratedSerialDescriptor.addElement("decorations", false);
        pluginGeneratedSerialDescriptor.addElement("title", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{TitleItemWidgetDecorations$$serializer.INSTANCE, StringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final TitleItemWidget deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        SerializationConstructorMarker serializationConstructorMarker = null;
        TitleItemWidgetDecorations titleItemWidgetDecorations = null;
        String strDecodeStringElement = null;
        boolean z = true;
        int i = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            if (iDecodeElementIndex == -1) {
                z = false;
            } else if (iDecodeElementIndex == 0) {
                titleItemWidgetDecorations = (TitleItemWidgetDecorations) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, TitleItemWidgetDecorations$$serializer.INSTANCE, titleItemWidgetDecorations);
                i |= 1;
            } else {
                if (iDecodeElementIndex != 1) {
                    throw new UnknownFieldException(iDecodeElementIndex);
                }
                strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 1);
                i |= 2;
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new TitleItemWidget(i, titleItemWidgetDecorations, strDecodeStringElement, serializationConstructorMarker);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull TitleItemWidget value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        TitleItemWidget.write$Self$ignitionshared_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
