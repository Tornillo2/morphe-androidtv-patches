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
public interface CompositeEncoder {

    /* JADX INFO: renamed from: kotlinx.serialization.encoding.CompositeEncoder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @ExperimentalSerializationApi
        public static boolean $default$shouldEncodeElementDefault(CompositeEncoder compositeEncoder, @NotNull SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return true;
        }

        public static /* synthetic */ boolean access$shouldEncodeElementDefault$jd(CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor, int i) {
            $default$shouldEncodeElementDefault(compositeEncoder, serialDescriptor, i);
            return true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @ExperimentalSerializationApi
        @Deprecated
        public static boolean shouldEncodeElementDefault(@NotNull CompositeEncoder compositeEncoder, @NotNull SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            CC.$default$shouldEncodeElementDefault(compositeEncoder, descriptor, i);
            return true;
        }
    }

    void encodeBooleanElement(@NotNull SerialDescriptor serialDescriptor, int i, boolean z);

    void encodeByteElement(@NotNull SerialDescriptor serialDescriptor, int i, byte b);

    void encodeCharElement(@NotNull SerialDescriptor serialDescriptor, int i, char c);

    void encodeDoubleElement(@NotNull SerialDescriptor serialDescriptor, int i, double d);

    void encodeFloatElement(@NotNull SerialDescriptor serialDescriptor, int i, float f);

    @NotNull
    Encoder encodeInlineElement(@NotNull SerialDescriptor serialDescriptor, int i);

    void encodeIntElement(@NotNull SerialDescriptor serialDescriptor, int i, int i2);

    void encodeLongElement(@NotNull SerialDescriptor serialDescriptor, int i, long j);

    @ExperimentalSerializationApi
    <T> void encodeNullableSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull SerializationStrategy<? super T> serializationStrategy, @Nullable T t);

    <T> void encodeSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull SerializationStrategy<? super T> serializationStrategy, T t);

    void encodeShortElement(@NotNull SerialDescriptor serialDescriptor, int i, short s);

    void encodeStringElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull String str);

    void endStructure(@NotNull SerialDescriptor serialDescriptor);

    @NotNull
    SerializersModule getSerializersModule();

    @ExperimentalSerializationApi
    boolean shouldEncodeElementDefault(@NotNull SerialDescriptor serialDescriptor, int i);
}
