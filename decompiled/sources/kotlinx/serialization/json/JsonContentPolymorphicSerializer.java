package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt__SerializersKt;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class JsonContentPolymorphicSerializer<T> implements KSerializer<T> {

    @NotNull
    public final KClass<T> baseClass;

    @NotNull
    public final SerialDescriptor descriptor;

    public JsonContentPolymorphicSerializer(@NotNull KClass<T> baseClass) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        this.baseClass = baseClass;
        this.descriptor = SerialDescriptorsKt.buildSerialDescriptor$default("JsonContentPolymorphicSerializer<" + baseClass.getSimpleName() + '>', PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], null, 8, null);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public final T deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonDecoder jsonDecoderAsJsonDecoder = JsonElementSerializersKt.asJsonDecoder(decoder);
        JsonElement jsonElementDecodeJsonElement = jsonDecoderAsJsonDecoder.decodeJsonElement();
        DeserializationStrategy<T> deserializationStrategySelectDeserializer = selectDeserializer(jsonElementDecodeJsonElement);
        Intrinsics.checkNotNull(deserializationStrategySelectDeserializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.json.JsonContentPolymorphicSerializer>");
        return (T) jsonDecoderAsJsonDecoder.getJson().decodeFromJsonElement((KSerializer) deserializationStrategySelectDeserializer, jsonElementDecodeJsonElement);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @NotNull
    public abstract DeserializationStrategy<T> selectDeserializer(@NotNull JsonElement jsonElement);

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        KSerializer polymorphic = encoder.getSerializersModule().getPolymorphic(this.baseClass, value);
        if (polymorphic == null) {
            KSerializer kSerializerSerializerOrNull = SerializersKt__SerializersKt.serializerOrNull(Reflection.getOrCreateKotlinClass(value.getClass()));
            if (kSerializerSerializerOrNull == null) {
                throwSubtypeNotRegistered(Reflection.factory.getOrCreateKotlinClass(value.getClass()), this.baseClass);
                throw null;
            }
            polymorphic = kSerializerSerializerOrNull;
        }
        ((KSerializer) polymorphic).serialize(encoder, value);
    }

    public final Void throwSubtypeNotRegistered(KClass<?> kClass, KClass<?> kClass2) {
        String simpleName = kClass.getSimpleName();
        if (simpleName == null) {
            simpleName = String.valueOf(kClass);
        }
        throw new SerializationException("Class '" + simpleName + "' is not registered for polymorphic serialization " + ("in the scope of '" + kClass2.getSimpleName() + '\'') + ".\nMark the base class as 'sealed' or register the serializer explicitly.");
    }
}
