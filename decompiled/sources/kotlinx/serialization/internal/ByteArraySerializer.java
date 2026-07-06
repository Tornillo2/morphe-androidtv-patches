package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class ByteArraySerializer extends PrimitiveArraySerializer<Byte, byte[], ByteArrayBuilder> implements KSerializer<byte[]> {

    @NotNull
    public static final ByteArraySerializer INSTANCE = new ByteArraySerializer();

    public ByteArraySerializer() {
        super(BuiltinSerializersKt.serializer(ByteCompanionObject.INSTANCE));
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public byte[] empty() {
        return new byte[0];
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    @NotNull
    /* JADX INFO: renamed from: empty, reason: avoid collision after fix types in other method */
    public byte[] empty2() {
        return new byte[0];
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public ByteArrayBuilder toBuilder(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return new ByteArrayBuilder(bArr);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(@NotNull CompositeEncoder encoder, @NotNull byte[] content, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(content, "content");
        for (int i2 = 0; i2 < i; i2++) {
            encoder.encodeByteElement(this.descriptor, i2, content[i2]);
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull ByteArrayBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.append$kotlinx_serialization_core(decoder.decodeByteElement(this.descriptor, i));
    }
}
