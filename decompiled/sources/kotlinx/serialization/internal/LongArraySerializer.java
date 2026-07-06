package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class LongArraySerializer extends PrimitiveArraySerializer<Long, long[], LongArrayBuilder> implements KSerializer<long[]> {

    @NotNull
    public static final LongArraySerializer INSTANCE = new LongArraySerializer();

    public LongArraySerializer() {
        super(BuiltinSerializersKt.serializer(LongCompanionObject.INSTANCE));
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public long[] empty() {
        return new long[0];
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull LongArrayBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.append$kotlinx_serialization_core(decoder.decodeLongElement(this.descriptor, i));
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(@NotNull CompositeEncoder encoder, @NotNull long[] content, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(content, "content");
        for (int i2 = 0; i2 < i; i2++) {
            encoder.encodeLongElement(this.descriptor, i2, content[i2]);
        }
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    @NotNull
    /* JADX INFO: renamed from: empty, reason: avoid collision after fix types in other method */
    public long[] empty2() {
        return new long[0];
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public LongArrayBuilder toBuilder(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return new LongArrayBuilder(jArr);
    }
}
