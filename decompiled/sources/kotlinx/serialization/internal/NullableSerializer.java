package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class NullableSerializer<T> implements KSerializer<T> {

    @NotNull
    public final SerialDescriptor descriptor;

    @NotNull
    public final KSerializer<T> serializer;

    public NullableSerializer(@NotNull KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.serializer = serializer;
        this.descriptor = new SerialDescriptorForNullable(serializer.getDescriptor());
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @Nullable
    public T deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        if (decoder.decodeNotNullMark()) {
            return (T) decoder.decodeSerializableValue(this.serializer);
        }
        return null;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && NullableSerializer.class == obj.getClass() && Intrinsics.areEqual(this.serializer, ((NullableSerializer) obj).serializer);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    public int hashCode() {
        return this.serializer.hashCode();
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @Nullable T t) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        if (t == null) {
            encoder.encodeNull();
        } else {
            encoder.encodeNotNullMark();
            encoder.encodeSerializableValue(this.serializer, t);
        }
    }
}
