package kotlinx.serialization;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface BinaryFormat extends SerialFormat {
    <T> T decodeFromByteArray(@NotNull DeserializationStrategy<? extends T> deserializationStrategy, @NotNull byte[] bArr);

    @NotNull
    <T> byte[] encodeToByteArray(@NotNull SerializationStrategy<? super T> serializationStrategy, T t);
}
