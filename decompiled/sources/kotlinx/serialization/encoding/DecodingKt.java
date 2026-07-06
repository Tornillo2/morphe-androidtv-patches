package kotlinx.serialization.encoding;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class DecodingKt {
    @Nullable
    public static final <T> T decodeIfNullable(@NotNull Decoder decoder, @NotNull DeserializationStrategy<? extends T> deserializer, @NotNull Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(decoder, "<this>");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(block, "block");
        if (deserializer.getDescriptor().isNullable() || decoder.decodeNotNullMark()) {
            return block.invoke();
        }
        return null;
    }

    public static final <T> T decodeStructure(@NotNull Decoder decoder, @NotNull SerialDescriptor descriptor, @NotNull Function1<? super CompositeDecoder, ? extends T> block) {
        Intrinsics.checkNotNullParameter(decoder, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(block, "block");
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor);
        T tInvoke = block.invoke(compositeDecoderBeginStructure);
        compositeDecoderBeginStructure.endStructure(descriptor);
        return tInvoke;
    }
}
