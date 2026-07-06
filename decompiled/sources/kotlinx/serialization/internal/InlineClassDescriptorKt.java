package kotlinx.serialization.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class InlineClassDescriptorKt {
    @InternalSerializationApi
    @NotNull
    public static final <T> SerialDescriptor InlinePrimitiveDescriptor(@NotNull String name, @NotNull final KSerializer<T> primitiveSerializer) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(primitiveSerializer, "primitiveSerializer");
        return new InlineClassDescriptor(name, new GeneratedSerializer<T>() { // from class: kotlinx.serialization.internal.InlineClassDescriptorKt.InlinePrimitiveDescriptor.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlinx.serialization.internal.GeneratedSerializer
            public KSerializer<?>[] childSerializers() {
                return new KSerializer[]{primitiveSerializer};
            }

            @Override // kotlinx.serialization.DeserializationStrategy
            public T deserialize(Decoder decoder) {
                Intrinsics.checkNotNullParameter(decoder, "decoder");
                throw new IllegalStateException("unsupported");
            }

            @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
            public SerialDescriptor getDescriptor() {
                throw new IllegalStateException("unsupported");
            }

            @Override // kotlinx.serialization.SerializationStrategy
            public void serialize(Encoder encoder, T t) {
                Intrinsics.checkNotNullParameter(encoder, "encoder");
                throw new IllegalStateException("unsupported");
            }

            @Override // kotlinx.serialization.internal.GeneratedSerializer
            public KSerializer[] typeParametersSerializers() {
                return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
            }
        });
    }
}
