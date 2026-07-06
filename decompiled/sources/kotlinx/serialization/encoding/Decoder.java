package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nDecoding.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Decoding.kt\nkotlinx/serialization/encoding/Decoder\n+ 2 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,576:1\n271#2,2:577\n*S KotlinDebug\n*F\n+ 1 Decoding.kt\nkotlinx/serialization/encoding/Decoder\n*L\n264#1:577,2\n*E\n"})
public interface Decoder {

    /* JADX INFO: renamed from: kotlinx.serialization.encoding.Decoder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @ExperimentalSerializationApi
        @Nullable
        public static Object $default$decodeNullableSerializableValue(Decoder decoder, @NotNull DeserializationStrategy deserializer) {
            Intrinsics.checkNotNullParameter(deserializer, "deserializer");
            if (deserializer.getDescriptor().isNullable() || decoder.decodeNotNullMark()) {
                return decoder.decodeSerializableValue(deserializer);
            }
            return null;
        }

        public static Object $default$decodeSerializableValue(Decoder decoder, @NotNull DeserializationStrategy deserializer) {
            Intrinsics.checkNotNullParameter(deserializer, "deserializer");
            return deserializer.deserialize(decoder);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @ExperimentalSerializationApi
        @Deprecated
        @Nullable
        public static <T> T decodeNullableSerializableValue(@NotNull Decoder decoder, @NotNull DeserializationStrategy<? extends T> deserializer) {
            Intrinsics.checkNotNullParameter(deserializer, "deserializer");
            return (T) CC.$default$decodeNullableSerializableValue(decoder, deserializer);
        }

        @Deprecated
        public static <T> T decodeSerializableValue(@NotNull Decoder decoder, @NotNull DeserializationStrategy<? extends T> deserializer) {
            Intrinsics.checkNotNullParameter(deserializer, "deserializer");
            return (T) CC.$default$decodeSerializableValue(decoder, deserializer);
        }
    }

    @NotNull
    CompositeDecoder beginStructure(@NotNull SerialDescriptor serialDescriptor);

    boolean decodeBoolean();

    byte decodeByte();

    char decodeChar();

    double decodeDouble();

    int decodeEnum(@NotNull SerialDescriptor serialDescriptor);

    float decodeFloat();

    @NotNull
    Decoder decodeInline(@NotNull SerialDescriptor serialDescriptor);

    int decodeInt();

    long decodeLong();

    @ExperimentalSerializationApi
    boolean decodeNotNullMark();

    @ExperimentalSerializationApi
    @Nullable
    Void decodeNull();

    @ExperimentalSerializationApi
    @Nullable
    <T> T decodeNullableSerializableValue(@NotNull DeserializationStrategy<? extends T> deserializationStrategy);

    <T> T decodeSerializableValue(@NotNull DeserializationStrategy<? extends T> deserializationStrategy);

    short decodeShort();

    @NotNull
    String decodeString();

    @NotNull
    SerializersModule getSerializersModule();
}
