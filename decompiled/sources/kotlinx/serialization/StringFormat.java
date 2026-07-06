package kotlinx.serialization;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface StringFormat extends SerialFormat {
    <T> T decodeFromString(@NotNull DeserializationStrategy<? extends T> deserializationStrategy, @NotNull String str);

    @NotNull
    <T> String encodeToString(@NotNull SerializationStrategy<? super T> serializationStrategy, T t);
}
