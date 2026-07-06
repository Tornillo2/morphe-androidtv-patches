package kotlinx.serialization.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PolymorphicSerializerKt;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@InternalSerializationApi
@SourceDebugExtension({"SMAP\nAbstractPolymorphicSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractPolymorphicSerializer.kt\nkotlinx/serialization/internal/AbstractPolymorphicSerializer\n+ 2 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 3 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n+ 4 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,115:1\n476#2,2:116\n478#2,2:119\n82#3:118\n571#4,2:121\n573#4,2:124\n1#5:123\n*S KotlinDebug\n*F\n+ 1 AbstractPolymorphicSerializer.kt\nkotlinx/serialization/internal/AbstractPolymorphicSerializer\n*L\n33#1:116,2\n33#1:119,2\n35#1:118\n39#1:121,2\n39#1:124,2\n*E\n"})
public abstract class AbstractPolymorphicSerializer<T> implements KSerializer<T> {
    public final T decodeSequentially(CompositeDecoder compositeDecoder) {
        return (T) CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 1, PolymorphicSerializerKt.findPolymorphicSerializer(this, compositeDecoder, compositeDecoder.decodeStringElement(getDescriptor(), 0)), null, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final T deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        compositeDecoderBeginStructure.decodeSequentially();
        T t = null;
        while (true) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
            if (iDecodeElementIndex == -1) {
                if (t != null) {
                    compositeDecoderBeginStructure.endStructure(descriptor);
                    return t;
                }
                throw new IllegalArgumentException(("Polymorphic value has not been read for class " + ((String) objectRef.element)).toString());
            }
            if (iDecodeElementIndex == 0) {
                objectRef.element = (T) compositeDecoderBeginStructure.decodeStringElement(getDescriptor(), iDecodeElementIndex);
            } else {
                if (iDecodeElementIndex != 1) {
                    StringBuilder sb = new StringBuilder("Invalid index in polymorphic deserialization of ");
                    String str = (String) objectRef.element;
                    if (str == null) {
                        str = "unknown class";
                    }
                    sb.append(str);
                    sb.append("\n Expected 0, 1 or DECODE_DONE(-1), but found ");
                    sb.append(iDecodeElementIndex);
                    throw new SerializationException(sb.toString());
                }
                T t2 = objectRef.element;
                if (t2 == 0) {
                    throw new IllegalArgumentException("Cannot read polymorphic value before its type token");
                }
                objectRef.element = t2;
                t = (T) CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), iDecodeElementIndex, PolymorphicSerializerKt.findPolymorphicSerializer(this, compositeDecoderBeginStructure, (String) t2), null, 8, null);
            }
        }
    }

    @InternalSerializationApi
    @Nullable
    public DeserializationStrategy<T> findPolymorphicSerializerOrNull(@NotNull CompositeDecoder decoder, @Nullable String str) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return decoder.getSerializersModule().getPolymorphic((KClass) getBaseClass(), str);
    }

    @NotNull
    public abstract KClass<T> getBaseClass();

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerializationStrategy<? super T> serializationStrategyFindPolymorphicSerializer = PolymorphicSerializerKt.findPolymorphicSerializer(this, encoder, value);
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor);
        compositeEncoderBeginStructure.encodeStringElement(getDescriptor(), 0, serializationStrategyFindPolymorphicSerializer.getDescriptor().getSerialName());
        compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 1, serializationStrategyFindPolymorphicSerializer, value);
        compositeEncoderBeginStructure.endStructure(descriptor);
    }

    @InternalSerializationApi
    @Nullable
    public SerializationStrategy<T> findPolymorphicSerializerOrNull(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        return encoder.getSerializersModule().getPolymorphic(getBaseClass(), value);
    }
}
