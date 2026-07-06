package com.amazon.ignitionshared.pear;

import androidx.tvprovider.media.tv.TvContractCompat;
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
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class TitleItemWidgetDecorations$$serializer implements GeneratedSerializer<TitleItemWidgetDecorations> {

    @NotNull
    public static final TitleItemWidgetDecorations$$serializer INSTANCE;

    @NotNull
    private static final SerialDescriptor descriptor;

    static {
        TitleItemWidgetDecorations$$serializer titleItemWidgetDecorations$$serializer = new TitleItemWidgetDecorations$$serializer();
        INSTANCE = titleItemWidgetDecorations$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.ignitionshared.pear.TitleItemWidgetDecorations", titleItemWidgetDecorations$$serializer, 7);
        pluginGeneratedSerialDescriptor.addElement("deep_link", false);
        pluginGeneratedSerialDescriptor.addElement("visuals", false);
        pluginGeneratedSerialDescriptor.addElement(TvContractCompat.Channels.COLUMN_DESCRIPTION, false);
        pluginGeneratedSerialDescriptor.addElement("watch_state", true);
        pluginGeneratedSerialDescriptor.addElement("entitlement", true);
        pluginGeneratedSerialDescriptor.addElement("catalog_type", true);
        pluginGeneratedSerialDescriptor.addElement("catalog", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public final KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{Deeplink$$serializer.INSTANCE, Visuals$$serializer.INSTANCE, stringSerializer, BuiltinSerializersKt.getNullable(WatchStateSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(Catalog$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final TitleItemWidgetDecorations deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        Deeplink deeplink = null;
        Visuals visuals = null;
        String strDecodeStringElement = null;
        WatchState watchState = null;
        Boolean bool = null;
        String str = null;
        Catalog catalog = null;
        boolean z = true;
        int i = 0;
        while (z) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
            switch (iDecodeElementIndex) {
                case -1:
                    z = false;
                    break;
                case 0:
                    deeplink = (Deeplink) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, Deeplink$$serializer.INSTANCE, deeplink);
                    i |= 1;
                    break;
                case 1:
                    visuals = (Visuals) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, Visuals$$serializer.INSTANCE, visuals);
                    i |= 2;
                    break;
                case 2:
                    strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 2);
                    i |= 4;
                    break;
                case 3:
                    watchState = (WatchState) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, WatchStateSerializer.INSTANCE, watchState);
                    i |= 8;
                    break;
                case 4:
                    bool = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, BooleanSerializer.INSTANCE, bool);
                    i |= 16;
                    break;
                case 5:
                    str = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, str);
                    i |= 32;
                    break;
                case 6:
                    catalog = (Catalog) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, Catalog$$serializer.INSTANCE, catalog);
                    i |= 64;
                    break;
                default:
                    throw new UnknownFieldException(iDecodeElementIndex);
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new TitleItemWidgetDecorations(i, deeplink, visuals, strDecodeStringElement, watchState, bool, str, catalog, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull TitleItemWidgetDecorations value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        TitleItemWidgetDecorations.write$Self$ignitionshared_release(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
