package kotlinx.serialization.json;

import kotlin.SubclassOptInRequired;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SealedSerializationApi;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SubclassOptInRequired(markerClass = {SealedSerializationApi.class})
public interface JsonEncoder extends Encoder, CompositeEncoder {
    void encodeJsonElement(@NotNull JsonElement jsonElement);

    @NotNull
    Json getJson();

    /* JADX INFO: renamed from: kotlinx.serialization.json.JsonEncoder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static /* synthetic */ boolean access$shouldEncodeElementDefault$jd(JsonEncoder jsonEncoder, SerialDescriptor serialDescriptor, int i) {
            CompositeEncoder.CC.$default$shouldEncodeElementDefault(jsonEncoder, serialDescriptor, i);
            return true;
        }

        public static /* synthetic */ void access$encodeNotNullMark$jd(JsonEncoder jsonEncoder) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @Deprecated
        @NotNull
        public static CompositeEncoder beginCollection(@NotNull JsonEncoder jsonEncoder, @NotNull SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return jsonEncoder.beginStructure(descriptor);
        }

        @ExperimentalSerializationApi
        @Deprecated
        public static <T> void encodeNullableSerializableValue(@NotNull JsonEncoder jsonEncoder, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            Encoder.CC.$default$encodeNullableSerializableValue(jsonEncoder, serializer, t);
        }

        @Deprecated
        public static <T> void encodeSerializableValue(@NotNull JsonEncoder jsonEncoder, @NotNull SerializationStrategy<? super T> serializer, T t) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            Encoder.CC.$default$encodeSerializableValue(jsonEncoder, serializer, t);
        }

        @ExperimentalSerializationApi
        @Deprecated
        public static boolean shouldEncodeElementDefault(@NotNull JsonEncoder jsonEncoder, @NotNull SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            CompositeEncoder.CC.$default$shouldEncodeElementDefault(jsonEncoder, descriptor, i);
            return true;
        }

        @ExperimentalSerializationApi
        @Deprecated
        public static void encodeNotNullMark(@NotNull JsonEncoder jsonEncoder) {
        }
    }
}
