package kotlinx.serialization.internal;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import kotlin.PublishedApi;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class TripleSerializer<A, B, C> implements KSerializer<Triple<? extends A, ? extends B, ? extends C>> {

    @NotNull
    public final KSerializer<A> aSerializer;

    @NotNull
    public final KSerializer<B> bSerializer;

    @NotNull
    public final KSerializer<C> cSerializer;

    @NotNull
    public final SerialDescriptor descriptor;

    public TripleSerializer(@NotNull KSerializer<A> aSerializer, @NotNull KSerializer<B> bSerializer, @NotNull KSerializer<C> cSerializer) {
        Intrinsics.checkNotNullParameter(aSerializer, "aSerializer");
        Intrinsics.checkNotNullParameter(bSerializer, "bSerializer");
        Intrinsics.checkNotNullParameter(cSerializer, "cSerializer");
        this.aSerializer = aSerializer;
        this.bSerializer = bSerializer;
        this.cSerializer = cSerializer;
        this.descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("kotlin.Triple", new SerialDescriptor[0], new Function1() { // from class: kotlinx.serialization.internal.TripleSerializer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TripleSerializer.descriptor$lambda$0(this.f$0, (ClassSerialDescriptorBuilder) obj);
            }
        });
    }

    public static final Unit descriptor$lambda$0(TripleSerializer tripleSerializer, ClassSerialDescriptorBuilder buildClassSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildClassSerialDescriptor, "$this$buildClassSerialDescriptor");
        ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "first", tripleSerializer.aSerializer.getDescriptor(), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "second", tripleSerializer.bSerializer.getDescriptor(), null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "third", tripleSerializer.cSerializer.getDescriptor(), null, false, 12, null);
        return Unit.INSTANCE;
    }

    public final Triple<A, B, C> decodeSequentially(CompositeDecoder compositeDecoder) {
        Object objDecodeSerializableElement$default = CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoder, this.descriptor, 0, this.aSerializer, null, 8, null);
        Object objDecodeSerializableElement$default2 = CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoder, this.descriptor, 1, this.bSerializer, null, 8, null);
        Object objDecodeSerializableElement$default3 = CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoder, this.descriptor, 2, this.cSerializer, null, 8, null);
        compositeDecoder.endStructure(this.descriptor);
        return new Triple<>(objDecodeSerializableElement$default, objDecodeSerializableElement$default2, objDecodeSerializableElement$default3);
    }

    public final Triple<A, B, C> decodeStructure(CompositeDecoder compositeDecoder) {
        CompositeDecoder compositeDecoder2;
        Object objDecodeSerializableElement$default = TuplesKt.NULL;
        Object objDecodeSerializableElement$default2 = objDecodeSerializableElement$default;
        Object objDecodeSerializableElement$default3 = objDecodeSerializableElement$default2;
        while (true) {
            int iDecodeElementIndex = compositeDecoder.decodeElementIndex(this.descriptor);
            if (iDecodeElementIndex == -1) {
                compositeDecoder.endStructure(this.descriptor);
                Object obj = TuplesKt.NULL;
                if (objDecodeSerializableElement$default == obj) {
                    throw new SerializationException("Element 'first' is missing");
                }
                if (objDecodeSerializableElement$default2 == obj) {
                    throw new SerializationException("Element 'second' is missing");
                }
                if (objDecodeSerializableElement$default3 != obj) {
                    return new Triple<>(objDecodeSerializableElement$default, objDecodeSerializableElement$default2, objDecodeSerializableElement$default3);
                }
                throw new SerializationException("Element 'third' is missing");
            }
            if (iDecodeElementIndex == 0) {
                compositeDecoder2 = compositeDecoder;
                objDecodeSerializableElement$default = CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoder2, this.descriptor, 0, this.aSerializer, null, 8, null);
            } else if (iDecodeElementIndex == 1) {
                compositeDecoder2 = compositeDecoder;
                objDecodeSerializableElement$default2 = CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoder2, this.descriptor, 1, this.bSerializer, null, 8, null);
            } else {
                if (iDecodeElementIndex != 2) {
                    throw new SerializationException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unexpected index ", iDecodeElementIndex));
                }
                objDecodeSerializableElement$default3 = CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoder, this.descriptor, 2, this.cSerializer, null, 8, null);
            }
            compositeDecoder = compositeDecoder2;
        }
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public Triple<A, B, C> deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(this.descriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        return decodeStructure(compositeDecoderBeginStructure);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull Triple<? extends A, ? extends B, ? extends C> value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(this.descriptor);
        compositeEncoderBeginStructure.encodeSerializableElement(this.descriptor, 0, this.aSerializer, value.first);
        compositeEncoderBeginStructure.encodeSerializableElement(this.descriptor, 1, this.bSerializer, value.second);
        compositeEncoderBeginStructure.encodeSerializableElement(this.descriptor, 2, this.cSerializer, value.third);
        compositeEncoderBeginStructure.endStructure(this.descriptor);
    }
}
