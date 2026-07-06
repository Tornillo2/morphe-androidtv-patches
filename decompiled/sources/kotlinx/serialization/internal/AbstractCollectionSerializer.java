package kotlinx.serialization.internal;

import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@InternalSerializationApi
public abstract class AbstractCollectionSerializer<Element, Collection, Builder> implements KSerializer<Collection> {
    public AbstractCollectionSerializer() {
    }

    public static /* synthetic */ void readElement$default(AbstractCollectionSerializer abstractCollectionSerializer, CompositeDecoder compositeDecoder, int i, Object obj, boolean z, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readElement");
        }
        if ((i2 & 8) != 0) {
            z = true;
        }
        abstractCollectionSerializer.readElement(compositeDecoder, i, obj, z);
    }

    public abstract Builder builder();

    public abstract int builderSize(Builder builder);

    public abstract void checkCapacity(Builder builder, int i);

    @NotNull
    public abstract Iterator<Element> collectionIterator(Collection collection);

    public abstract int collectionSize(Collection collection);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Collection deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return merge(decoder, null);
    }

    @InternalSerializationApi
    public final Collection merge(@NotNull Decoder decoder, @Nullable Collection collection) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Builder builder = collection != null ? toBuilder(collection) : builder();
        int iBuilderSize = builderSize(builder);
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(getDescriptor());
        compositeDecoderBeginStructure.decodeSequentially();
        while (true) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
            if (iDecodeElementIndex == -1) {
                compositeDecoderBeginStructure.endStructure(getDescriptor());
                return toResult(builder);
            }
            readElement$default(this, compositeDecoderBeginStructure, iBuilderSize + iDecodeElementIndex, builder, false, 8, null);
        }
    }

    public abstract void readAll(@NotNull CompositeDecoder compositeDecoder, Builder builder, int i, int i2);

    public abstract void readElement(@NotNull CompositeDecoder compositeDecoder, int i, Builder builder, boolean z);

    public final int readSize(CompositeDecoder compositeDecoder, Builder builder) {
        compositeDecoder.decodeCollectionSize(getDescriptor());
        checkCapacity(builder, -1);
        return -1;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public abstract void serialize(@NotNull Encoder encoder, Collection collection);

    public abstract Builder toBuilder(Collection collection);

    public abstract Collection toResult(Builder builder);

    public AbstractCollectionSerializer(DefaultConstructorMarker defaultConstructorMarker) {
    }
}
