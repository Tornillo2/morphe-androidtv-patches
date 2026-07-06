package kotlinx.serialization.internal;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.PublishedApi;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@ExperimentalSerializationApi
@ExperimentalUnsignedTypes
public final class ULongArraySerializer extends PrimitiveArraySerializer<ULong, ULongArray, ULongArrayBuilder> implements KSerializer<ULongArray> {

    @NotNull
    public static final ULongArraySerializer INSTANCE = new ULongArraySerializer();

    public ULongArraySerializer() {
        super(BuiltinSerializersKt.serializer(ULong.Companion));
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* synthetic */ int collectionSize(Object obj) {
        return m2175collectionSizeQwZRm1k(((ULongArray) obj).storage);
    }

    /* JADX INFO: renamed from: collectionSize-QwZRm1k, reason: not valid java name */
    public int m2175collectionSizeQwZRm1k(@NotNull long[] collectionSize) {
        Intrinsics.checkNotNullParameter(collectionSize, "$this$collectionSize");
        return collectionSize.length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public ULongArray empty() {
        return new ULongArray(new long[0]);
    }

    @NotNull
    /* JADX INFO: renamed from: empty-Y2RjT0g, reason: not valid java name */
    public long[] m2176emptyY2RjT0g() {
        return new long[0];
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull ULongArrayBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.m2173appendVKZWuLQ$kotlinx_serialization_core(decoder.decodeInlineElement(this.descriptor, i).decodeLong());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* synthetic */ Object toBuilder(Object obj) {
        return m2177toBuilderQwZRm1k(((ULongArray) obj).storage);
    }

    @NotNull
    /* JADX INFO: renamed from: toBuilder-QwZRm1k, reason: not valid java name */
    public ULongArrayBuilder m2177toBuilderQwZRm1k(@NotNull long[] toBuilder) {
        Intrinsics.checkNotNullParameter(toBuilder, "$this$toBuilder");
        return new ULongArrayBuilder(toBuilder);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public /* synthetic */ void writeContent(CompositeEncoder compositeEncoder, ULongArray uLongArray, int i) {
        m2178writeContent0q3Fkuo(compositeEncoder, uLongArray.storage, i);
    }

    /* JADX INFO: renamed from: writeContent-0q3Fkuo, reason: not valid java name */
    public void m2178writeContent0q3Fkuo(@NotNull CompositeEncoder encoder, @NotNull long[] content, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(content, "content");
        for (int i2 = 0; i2 < i; i2++) {
            encoder.encodeInlineElement(this.descriptor, i2).encodeLong(content[i2]);
        }
    }
}
