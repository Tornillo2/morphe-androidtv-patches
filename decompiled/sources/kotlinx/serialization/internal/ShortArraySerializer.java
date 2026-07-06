package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class ShortArraySerializer extends PrimitiveArraySerializer<Short, short[], ShortArrayBuilder> implements KSerializer<short[]> {

    @NotNull
    public static final ShortArraySerializer INSTANCE = new ShortArraySerializer();

    public ShortArraySerializer() {
        super(BuiltinSerializersKt.serializer(ShortCompanionObject.INSTANCE));
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public short[] empty() {
        return new short[0];
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    @NotNull
    /* JADX INFO: renamed from: empty, reason: avoid collision after fix types in other method */
    public short[] empty2() {
        return new short[0];
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public ShortArrayBuilder toBuilder(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return new ShortArrayBuilder(sArr);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(@NotNull CompositeEncoder encoder, @NotNull short[] content, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(content, "content");
        for (int i2 = 0; i2 < i; i2++) {
            encoder.encodeShortElement(this.descriptor, i2, content[i2]);
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull ShortArrayBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.append$kotlinx_serialization_core(decoder.decodeShortElement(this.descriptor, i));
    }
}
