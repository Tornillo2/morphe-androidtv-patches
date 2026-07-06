package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface Encoder {
    @NotNull
    CompositeEncoder beginCollection(@NotNull SerialDescriptor serialDescriptor, int i);

    @NotNull
    CompositeEncoder beginStructure(@NotNull SerialDescriptor serialDescriptor);

    void encodeBoolean(boolean z);

    void encodeByte(byte b);

    void encodeChar(char c);

    void encodeDouble(double d);

    void encodeEnum(@NotNull SerialDescriptor serialDescriptor, int i);

    void encodeFloat(float f);

    @NotNull
    Encoder encodeInline(@NotNull SerialDescriptor serialDescriptor);

    void encodeInt(int i);

    void encodeLong(long j);

    @ExperimentalSerializationApi
    void encodeNotNullMark();

    @ExperimentalSerializationApi
    void encodeNull();

    @ExperimentalSerializationApi
    <T> void encodeNullableSerializableValue(@NotNull SerializationStrategy<? super T> serializationStrategy, @Nullable T t);

    <T> void encodeSerializableValue(@NotNull SerializationStrategy<? super T> serializationStrategy, T t);

    void encodeShort(short s);

    void encodeString(@NotNull String str);

    @NotNull
    SerializersModule getSerializersModule();

    /* JADX INFO: renamed from: kotlinx.serialization.encoding.Encoder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @NotNull
        public static CompositeEncoder $default$beginCollection(Encoder encoder, @NotNull SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return encoder.beginStructure(descriptor);
        }

        @ExperimentalSerializationApi
        public static void $default$encodeNullableSerializableValue(Encoder encoder, @NotNull SerializationStrategy serializer, @Nullable Object obj) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            if (serializer.getDescriptor().isNullable()) {
                encoder.encodeSerializableValue(serializer, obj);
            } else if (obj == null) {
                encoder.encodeNull();
            } else {
                encoder.encodeNotNullMark();
                encoder.encodeSerializableValue(serializer, obj);
            }
        }

        public static void $default$encodeSerializableValue(Encoder encoder, @NotNull SerializationStrategy serializer, Object obj) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            serializer.serialize(encoder, obj);
        }

        @ExperimentalSerializationApi
        public static void $default$encodeNotNullMark(Encoder encoder) {
        }

        public static /* synthetic */ void access$encodeNotNullMark$jd(Encoder encoder) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @Deprecated
        @NotNull
        public static CompositeEncoder beginCollection(@NotNull Encoder encoder, @NotNull SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return encoder.beginStructure(descriptor);
        }

        @ExperimentalSerializationApi
        @Deprecated
        public static <T> void encodeNullableSerializableValue(@NotNull Encoder encoder, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            CC.$default$encodeNullableSerializableValue(encoder, serializer, t);
        }

        @Deprecated
        public static <T> void encodeSerializableValue(@NotNull Encoder encoder, @NotNull SerializationStrategy<? super T> serializer, T t) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            CC.$default$encodeSerializableValue(encoder, serializer, t);
        }

        @ExperimentalSerializationApi
        @Deprecated
        public static void encodeNotNullMark(@NotNull Encoder encoder) {
        }
    }
}
