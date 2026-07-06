package kotlinx.serialization.json;

import kotlin.SubclassOptInRequired;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SealedSerializationApi;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SubclassOptInRequired(markerClass = {SealedSerializationApi.class})
public interface JsonDecoder extends Decoder, CompositeDecoder {

    /* JADX INFO: renamed from: kotlinx.serialization.json.JsonDecoder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static /* synthetic */ int access$decodeCollectionSize$jd(JsonDecoder jsonDecoder, SerialDescriptor serialDescriptor) {
            CompositeDecoder.CC.$default$decodeCollectionSize(jsonDecoder, serialDescriptor);
            return -1;
        }

        public static /* synthetic */ boolean access$decodeSequentially$jd(JsonDecoder jsonDecoder) {
            CompositeDecoder.CC.$default$decodeSequentially(jsonDecoder);
            return false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @Deprecated
        public static int decodeCollectionSize(@NotNull JsonDecoder jsonDecoder, @NotNull SerialDescriptor descriptor) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            CompositeDecoder.CC.$default$decodeCollectionSize(jsonDecoder, descriptor);
            return -1;
        }

        @ExperimentalSerializationApi
        @Deprecated
        @Nullable
        public static <T> T decodeNullableSerializableValue(@NotNull JsonDecoder jsonDecoder, @NotNull DeserializationStrategy<? extends T> deserializer) {
            Intrinsics.checkNotNullParameter(deserializer, "deserializer");
            return (T) Decoder.CC.$default$decodeNullableSerializableValue(jsonDecoder, deserializer);
        }

        @ExperimentalSerializationApi
        @Deprecated
        public static boolean decodeSequentially(@NotNull JsonDecoder jsonDecoder) {
            CompositeDecoder.CC.$default$decodeSequentially(jsonDecoder);
            return false;
        }

        @Deprecated
        public static <T> T decodeSerializableValue(@NotNull JsonDecoder jsonDecoder, @NotNull DeserializationStrategy<? extends T> deserializer) {
            Intrinsics.checkNotNullParameter(deserializer, "deserializer");
            return (T) Decoder.CC.$default$decodeSerializableValue(jsonDecoder, deserializer);
        }
    }

    @NotNull
    JsonElement decodeJsonElement();

    @NotNull
    Json getJson();
}
