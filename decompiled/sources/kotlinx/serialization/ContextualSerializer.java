package kotlinx.serialization;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.ContextAwareKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@ExperimentalSerializationApi
public final class ContextualSerializer<T> implements KSerializer<T> {

    @NotNull
    public final SerialDescriptor descriptor;

    @Nullable
    public final KSerializer<T> fallbackSerializer;

    @NotNull
    public final KClass<T> serializableClass;

    @NotNull
    public final List<KSerializer<?>> typeArgumentsSerializers;

    public ContextualSerializer(@NotNull KClass<T> serializableClass, @Nullable KSerializer<T> kSerializer, @NotNull KSerializer<?>[] typeArgumentsSerializers) {
        Intrinsics.checkNotNullParameter(serializableClass, "serializableClass");
        Intrinsics.checkNotNullParameter(typeArgumentsSerializers, "typeArgumentsSerializers");
        this.serializableClass = serializableClass;
        this.fallbackSerializer = kSerializer;
        this.typeArgumentsSerializers = ArraysKt___ArraysJvmKt.asList(typeArgumentsSerializers);
        this.descriptor = ContextAwareKt.withContext(SerialDescriptorsKt.buildSerialDescriptor("kotlinx.serialization.ContextualSerializer", SerialKind.CONTEXTUAL.INSTANCE, new SerialDescriptor[0], new Function1() { // from class: kotlinx.serialization.ContextualSerializer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ContextualSerializer.descriptor$lambda$0(this.f$0, (ClassSerialDescriptorBuilder) obj);
            }
        }), serializableClass);
    }

    public static final Unit descriptor$lambda$0(ContextualSerializer contextualSerializer, ClassSerialDescriptorBuilder buildSerialDescriptor) {
        SerialDescriptor descriptor;
        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
        KSerializer<T> kSerializer = contextualSerializer.fallbackSerializer;
        List<Annotation> annotations = (kSerializer == null || (descriptor = kSerializer.getDescriptor()) == null) ? null : descriptor.getAnnotations();
        if (annotations == null) {
            annotations = EmptyList.INSTANCE;
        }
        buildSerialDescriptor.setAnnotations(annotations);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public T deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return (T) decoder.decodeSerializableValue(serializer(decoder.getSerializersModule()));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        encoder.encodeSerializableValue(serializer(encoder.getSerializersModule()), value);
    }

    public final KSerializer<T> serializer(SerializersModule serializersModule) {
        KSerializer<T> contextual = serializersModule.getContextual(this.serializableClass, this.typeArgumentsSerializers);
        if (contextual != null) {
            return contextual;
        }
        KSerializer<T> kSerializer = this.fallbackSerializer;
        if (kSerializer != null) {
            return kSerializer;
        }
        Platform_commonKt.serializerNotRegistered(this.serializableClass);
        throw null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ContextualSerializer(@NotNull KClass<T> serializableClass) {
        this(serializableClass, null, PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY);
        Intrinsics.checkNotNullParameter(serializableClass, "serializableClass");
    }
}
