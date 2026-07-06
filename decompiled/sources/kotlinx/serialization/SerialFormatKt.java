package kotlinx.serialization;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.internal.InternalHexConverter;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SerialFormatKt {
    public static final <T> T decodeFromByteArray(BinaryFormat binaryFormat, byte[] bytes) {
        Intrinsics.checkNotNullParameter(binaryFormat, "<this>");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <T> T decodeFromHexString(@NotNull BinaryFormat binaryFormat, @NotNull DeserializationStrategy<? extends T> deserializer, @NotNull String hex) {
        Intrinsics.checkNotNullParameter(binaryFormat, "<this>");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(hex, "hex");
        return (T) binaryFormat.decodeFromByteArray(deserializer, InternalHexConverter.INSTANCE.parseHexBinary(hex));
    }

    public static final <T> T decodeFromString(StringFormat stringFormat, String string) {
        Intrinsics.checkNotNullParameter(stringFormat, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <T> byte[] encodeToByteArray(BinaryFormat binaryFormat, T t) {
        Intrinsics.checkNotNullParameter(binaryFormat, "<this>");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final <T> String encodeToHexString(@NotNull BinaryFormat binaryFormat, @NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkNotNullParameter(binaryFormat, "<this>");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        return InternalHexConverter.INSTANCE.printHexBinary(binaryFormat.encodeToByteArray(serializer, t), true);
    }

    public static final <T> String encodeToString(StringFormat stringFormat, T t) {
        Intrinsics.checkNotNullParameter(stringFormat, "<this>");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <T> T decodeFromHexString(BinaryFormat binaryFormat, String hex) {
        Intrinsics.checkNotNullParameter(binaryFormat, "<this>");
        Intrinsics.checkNotNullParameter(hex, "hex");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <T> String encodeToHexString(BinaryFormat binaryFormat, T t) {
        Intrinsics.checkNotNullParameter(binaryFormat, "<this>");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }
}
